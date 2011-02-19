#!/bin/sh

for i in *.xml
do
    echo "Deploy $i"
    directory=xml.`echo $i | sed -e 's/\([^_]*\)-\(.*\).xml/\1/'`
    params=`echo $i | sed -e 's/\([^_]*\)-\(.*\).xml/\2/'`
    dirToCreate=`echo $directory | sed -e 's@\.@/@g'`
    parentDir=`dirname $dirToCreate`
    echo "Creating directory $parentDir"
    mkdir -p $parentDir
    newName=`basename $dirToCreate`-$params.xml
    echo "New name: $newName"
    mv $i $parentDir/$newName
done

for i in *.json
do
    echo "Deploy $i"
    directory=json.`echo $i | sed -e 's/\([^_]*\)-\(.*\).json/\1/'`
    params=`echo $i | sed -e 's/\([^_]*\)-\(.*\).json/\2/'`
    dirToCreate=`echo $directory | sed -e 's@\.@/@g'`
    parentDir=`dirname $dirToCreate`
    echo "Creating directory $parentDir"
    mkdir -p $parentDir
    newName=`basename $dirToCreate`-$params.json
    echo "New name: $newName"
    mv $i $parentDir/$newName
done
