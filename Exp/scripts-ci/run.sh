#!/bin/bash

TESTS="\
  pobj.expr.test.TestQ1 \
  pobj.expr.test.TestQ2 \
  pobj.expr.test.TestQ3 \
  pobj.expr.test.TestQ4 \
  pobj.expr.test.TestQ5 \
  pobj.expr.test.TestQ6 \
  pobj.expr.test.TestQ7 
  \
  pobj.expr.test.TestQ8 \
  pobj.expr.test.TestQ9 \
  pobj.expr.test.TestQ10 \
  pobj.expr.test.TestQ11 \
  pobj.expr.test.TestQ12 \
  pobj.expr.test.TestQ13 \
"

#########

CP=/home/pobj/pobj-ci.jar:/usr/share/java/junit4.jar:/usr/share/java/hamcrest-core-1.3.jar

SRC=./src

echo "Date :"
date
echo

echo "Répertoire courant :"
pwd
echo

echo "Liste des fichiers :"
ls -lR
echo

echo "Lancement des tests"
echo java -cp $CP:. pobj.ci.Runner -c -cp $CP -sp $SRC $TESTS
java -cp $CP:. pobj.ci.Runner -c -cp $CP -sp $SRC $TESTS
