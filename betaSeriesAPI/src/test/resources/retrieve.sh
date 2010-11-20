#!/bin/sh

for i in `cat list`
do
    parameters=`echo $i | cut -d '|' -f 2`
    first="true"
    sortedParam=`echo $parameters | sed -e 's/&/ /g' | tr " " "\n"\
        | sort | tr "\n" " "`
    params=`echo $sortedParam | sed -e 's/=/_/g' | sed -e 's/ /_/g'`
    action=`echo $i | cut -d '|' -f 1`
    actionfile=`echo $action | sed -e 's@/@\.@g'`
    urlfile=http://api.betaseries.com/$action
    curl -d $parameters $urlfile.xml > $actionfile-$params.xml
    curl -d $parameters $urlfile.json > $actionfile-$params.json
done
