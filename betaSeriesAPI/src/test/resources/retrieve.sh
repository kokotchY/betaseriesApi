#!/bin/sh

for i in `cat list`
do
    oldIfs=$IFS
    parameters=`echo $i | cut -d '|' -f 2`
    IFS="&"
    sortedParam=""
    first="true"
    for j in $parameters
    do
        if [ $first = "true" ]
        then
            sortedParam="$j"
        else
            sortedParam="${sortedParam}_$j"
        fi
    done
    IFS=$oldIfs
    action=`echo $i | cut -d '|' -f 1`
    actionfile=`echo $action | sed -e 's@/@\.@g'`
    urlfile=http://api.betaseries.com/$action
    curl -d $parameters $urlfile.xml > $actionfile-$sortedParam.xml
    curl -d $parameters $urlfile.json > $actionfile-$sortedParam.json
done
