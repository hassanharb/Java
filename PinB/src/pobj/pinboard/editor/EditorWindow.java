 package pobj.pinboard.editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.commands.Command;
import pobj.pinboard.editor.commands.CommandDelet;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;
import pobj.pinboard.document.ClipGroup;

public class EditorWindow implements EditorInterface, ClipboardListener {

	
	private Board board;
	private Tool tool = null;
	private Selection selection;
	private MenuItem past;
	private MenuItem groupe;
	private MenuItem ungroupe;
	private MenuItem undo;
	private MenuItem redo;
	private Color [] palette = {Color.BLACK, Color.BLUE, Color.RED, Color.AQUA, Color.YELLOW, Color.ORANGE, Color.GREEN, Color.GRAY, Color.PINK, Color.BROWN, Color.VIOLET};
	private CommandStack stack;
	private Canvas canvas;
	private File fichier = null;
	
	
	public EditorWindow (Stage stage) {
		this.stack = new CommandStack();
		VBox vbox = new VBox();
		stage.setTitle("PinBoard-<Untiteled>");
		//canvas 
		this.canvas = new Canvas(800, 590);
		
		
		//s'enregister auprés de Clipboard
		Clipboard.getInstance().addListener(this);
		
		stage.setOnCloseRequest((event) -> {
			Clipboard.getInstance().removeListener(this);
		});
		//menubar
		MenuBar munubar = new MenuBar();
			
			//menu file
		Menu file = new Menu("File");
		MenuItem _new = new MenuItem("New");
		_new.setOnAction(new EventHandler<ActionEvent>(){
			public void handle (ActionEvent ae) {new EditorWindow(new Stage());}
		});
		
		MenuItem close = new MenuItem("Close");
			close.setOnAction( (ae) ->  {Clipboard.getInstance().removeListener(this);stage.close();}
		);
			
		MenuItem save = new MenuItem("save");
		save.setOnAction((ea) -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("save distination");
			if (this.fichier == null) {
				this.fichier = fc.showSaveDialog(stage);
				stage.setTitle("PinBaord-<"+this.fichier.getName()+">");
			}
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(this.fichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.board);
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		});
		
		
		MenuItem saveInNew = new MenuItem("save new");
		saveInNew.setOnAction((ea) -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("save distination");
			File nouveauFichier = fc.showSaveDialog(stage);		
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(nouveauFichier);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(this.board);
				oos.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		});
		
		
		
		
		MenuItem open = new MenuItem("open");
		open.setOnAction((ea) -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("open");
			this.fichier = fc.showOpenDialog(stage);
			stage.setTitle("PinBaord-<"+this.fichier.getName()+">");
			FileInputStream fis;
			try {
				fis = new FileInputStream(this.fichier);
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.board =  (Board) ois.readObject();
				ois.close();
				fis.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			for (Clip c : this.board.getContents()) {
				if (c instanceof ClipImage) {
					((ClipImage) c).setImage(new Image("file://"+((ClipImage) c).getFile().getAbsolutePath()));
				}
			}
			this.board.draw(canvas.getGraphicsContext2D());
		});
		
		
		
		
		
		file.getItems().addAll(_new, close, save, saveInNew ,open);
		
			//menu edit
		Menu edit = new Menu("Edit");
		MenuItem copy = new MenuItem("copy");
		copy.setOnAction((ae)-> {
			this.copy();
		});
		this.past = new MenuItem("past");
		past.setOnAction((ae) -> {
			this.past();
		});
		this.past.setDisable(Clipboard.getInstance().isEmpty());
		
		MenuItem delete = new MenuItem("delete");
		delete.setOnAction((ae) -> {
			this.delete();
		});
		
		this.groupe = new MenuItem ("groupe");
		this.groupe.setOnAction((ae) -> {
//			if (! this.selection.getContents().isEmpty()){
//				ClipGroup cg = new ClipGroup();
//				for (Clip c : this.selection.getContents()){
//					cg.addClip(c);
//					this.board.removeClip(c);
//				}
//				this.board.addClip(cg);
//			}
			Command cmd = new CommandGroup(this, this.selection.getContents());
			this.selection.clear();
			this.change_groupe_ungroupe();
			cmd.execute();
			this.stack.addCommand(cmd);
			this.board.draw(canvas.getGraphicsContext2D());
		});
		
		this.ungroupe = new MenuItem("ungroupe");
		this.ungroupe.setOnAction((ae) -> {
//			for (Clip c : this.selection.getContents()){
//				if (c instanceof ClipGroup){
//					for (Clip c1 : ((ClipGroup)c).getClips()){
//						this.board.addClip(c1);
//					}
//					this.board.removeClip(c);
//				}
//			}
			Command cmd = new CommandUngroup (this, this.selection.getContents());
			this.selection.clear();
			this.change_groupe_ungroupe();
			cmd.execute();
			this.getUndoStack().addCommand(cmd);
			this.board.draw(canvas.getGraphicsContext2D());
		});
		
//		this.change_groupe_ungroupe();
		


		this.undo = new MenuItem("undo");
		this.undo.setOnAction((ae) -> {
			this.undo();
		});
		
		this.redo = new MenuItem("redo");
		this.redo.setOnAction((ae) -> {
			this.stack.redo();
			this.change_undo_redo();
			this.board.draw(canvas.getGraphicsContext2D());
		});
		
		this.change_undo_redo();
		
//		MenuItem exe = new MenuItem("exe");
//		exe.setOnAction((ae)-> {
//			this.stack.affche();
//			for (Clip c : this.board.getContents()) {
//				System.out.println(c);
//			}
//			System.out.println("_______________________________________________________________________________________________");
//		});
		
		edit.getItems().addAll(copy, past, delete, groupe, ungroupe, undo, redo);
		
		
		
		
			//menu tools
		Menu tools = new Menu("Tools");
		
		
		munubar.getMenus().addAll(file, edit, tools);
		//fin menubar
		
		
		//Label
		Label label = new Label();
		String [] labeltext = new String[2];
		labeltext[0] = "Empty";
		labeltext[1] = Color.BLACK.toString();

		
		//toolbar_1
		ToolBar toolbar = new ToolBar();
		Button box = new Button("Box");
		box.setOnAction((ae) -> {
			tool = new ToolRect();
			labeltext[0] = tool.getName(this);
			label.setText(labeltext[0]+" "+labeltext[1]);
		});
		Button ellipse = new Button ("Ellipse");
		ellipse.setOnAction((ae) -> {
			tool = new ToolEllipse();
			labeltext[0] = tool.getName(this);
			label.setText(labeltext[0]+" "+labeltext[1]);
		});
		Button image = new Button ("Image");
		image.setOnAction( ae -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("selectionner image");
			File selected = fc.showOpenDialog(stage);
			tool = new ToolImage(selected);
			labeltext[0] = tool.getName(this);
			label.setText(labeltext[0]+" "+labeltext[1]);
		});
		Button selection = new Button ("selectionner");
		selection.setOnAction(ae -> {
			tool = new ToolSelection();
			labeltext[0] = tool.getName(this);
			label.setText(labeltext[0]+" "+labeltext[1]);
		});
		toolbar.getItems().addAll(box, ellipse, image, selection);
		
		//separateur	
		Separator separateur = new Separator();
		
		
		//toolbar_2
		ToolBar toolbar_2 = new ToolBar();
		Button [] couleurs = new Button [this.palette.length];
		int i = 0;
		for (Color c : this.palette) {
			couleurs[i] = new Button();
			String codeRGB = c.toString().substring(2, c.toString().length());
			couleurs[i].setStyle("-fx-color : #"+codeRGB+" ; -fx-width : 10px ; -fx-height : 10px ;");
			couleurs[i].setOnAction((e)->{
				this.board.setColor(c);
				labeltext[1] = c.toString();
				label.setText(labeltext[0]+" "+labeltext[1]);
			});
			i++;
		}
		toolbar_2.getItems().addAll(couleurs);
		
		board = new Board();
		this.selection = new Selection();
		
		
			canvas.setOnMousePressed((me) -> {
				if (tool != null) {
					tool.press(this, me);
					tool.drawFeedback(this, canvas.getGraphicsContext2D());
				}
			});
			canvas.setOnMouseDragged((me) -> {
				if (tool != null) {
					board.draw(canvas.getGraphicsContext2D());
					tool.drag(this, me);
					tool.drawFeedback(this, canvas.getGraphicsContext2D());
				}
			});
			canvas.setOnMouseReleased((me) -> {
				if (tool != null) {
					tool.release(this, me);
					board.draw(canvas.getGraphicsContext2D());
					this.change_undo_redo();
					this.change_groupe_ungroupe();
					tool.drawFeedback(this, canvas.getGraphicsContext2D());
				}
			});	
			
		/**
		 * quelque commande de base
		 */
//			Clip clip1 = new ClipEllipse(100, 100, 200, 200, Color.BLACK);
//			Clip clip2 = new ClipRect(300, 300, 400, 400, Color.BLACK);
//			Command cmd = new CommandAdd(this, clip1);
//			Command cmd1 = new CommandAdd(this, clip2);
//			List<Clip> l = new ArrayList<Clip>();
//			l.add(clip1);
//			l.add(clip2);
//			Command cmd2 = new CommandGroup(this, l);
//			cmd.execute();
//			cmd1.execute();
//			cmd1.undo();
//			cmd2.execute();
			//cmd1.execute();
//			cmd2.undo();
//			cmd.undo();
			
			/**
			 * quelque commande de base
			 */
		
			
		board.draw(canvas.getGraphicsContext2D());
		vbox.getChildren().addAll(munubar, toolbar, canvas, separateur,toolbar_2, label);
		stage.setScene(new Scene (vbox));
		stage.getScene().setOnKeyReleased((ke)->{
			switch (ke.getCode()) {
			case DELETE:
				this.delete();
				break;
			case C :
				if (ke.isControlDown()) {
					this.copy();
				}
				break;
			case V :
				if (ke.isControlDown()) {
					this.past();
				}
				break;
				
			case Z :
				if (ke.isControlDown()) {
					this.undo();
				}
				break;
			default:
				break;
			}
		});
		stage.show();
	}

	@Override
	public Board getBoard() {
		return this.board;
	}
	
	@Override
	public Selection getSelection (){
		return this.selection;
	}

	@Override
	public void clipboardChanged() {
		this.past.setDisable(Clipboard.getInstance().isEmpty());
	}

	@Override
	public CommandStack getUndoStack() {
		return this.stack;
	}
	
	private void change_undo_redo () {
		this.undo.setDisable(this.stack.isUndoEmpty());
		this.redo.setDisable(this.stack.isRedoEmpty());
	}
	
	private void change_groupe_ungroupe() {
		this.groupe.setDisable(this.selection.getContents().isEmpty());
		boolean isDesable = true;
		for (Clip c : this.selection.getContents()) {
			if (c instanceof ClipGroup) {
				isDesable = false;
			}
		}
		this.ungroupe.setDisable(isDesable);
	}
	
	private void delete () {
		Command cmd = new CommandDelet (this, this.selection.getContents());
		this.stack.addCommand(cmd);
		cmd.execute();
		this.selection.getContents().clear();
		board.draw(canvas.getGraphicsContext2D());
	}
	
	private void copy () {
		Clipboard.getInstance().clear();
		Clipboard.getInstance().copyToClipboard(this.selection.getContents());
	}
	
	private void past () {
		this.board.addClip(Clipboard.getInstance().copyFromClipboard());
		board.draw(canvas.getGraphicsContext2D());
	}
	
	private void undo () {
		this.getUndoStack().undo();
		this.change_undo_redo();
		this.board.draw(canvas.getGraphicsContext2D());
	}
}
