#!/bin/bash
function itdir(){
    rm -f *.class
    for element in `ls`
    do  
        if [ -d $element ]
        then
            cd $element
            itdir $element
            cd ..
        fi  
    done
}

echo "cleaning"
itdir "."
echo "compiling"
javac Test.java
echo "executing"
java Test $1
cat $1
echo "cleaning"
itdir "."
