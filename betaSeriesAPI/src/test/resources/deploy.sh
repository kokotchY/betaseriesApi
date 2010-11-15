#!/bin/sh

for i in *.xml
do
    echo "Deploy $i"
    directory=`echo $i | sed -e 's/\(.*\)-\(.*\).xml/\1/'`
    params=`echo $i | sed -e 's/\(.*\)-\(.*\).xml/\2/'`
    dirToCreate=`echo $directory | sed -e 's@\.@/@g'`
    parentDir=`dirname $dirToCreate`
    echo "Creating directory $parentDir"
    mkdir -p $parentDir
    newName=`basename $dirToCreate`-$params.xml
    echo "New name: $newName"
    mv $i $parentDir/$newName
done
