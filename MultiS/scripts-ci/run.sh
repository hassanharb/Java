#!/bin/bash

TESTS_TME4="\
  pobj.tme4.test.HashMultiSetTest \
  pobj.tme4.test.IteratorTest \
  pobj.tme4.test.CollectionTest \
"

TESTS_TME5="\
  pobj.tme5.test.HashMultiSetTest \
  pobj.tme5.test.HashMultiSetTest2 \
  pobj.tme5.test.MultiSetParserTest \
"

TESTS="$TESTS_TME4 $TESTS_TME5"


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
