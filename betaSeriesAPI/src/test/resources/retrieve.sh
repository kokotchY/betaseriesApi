#!/bin/sh

for i in `cat list`
do
    parameters=`echo $i | cut -d '|' -f 2`
    action=`echo $i | cut -d '|' -f 1`
    actionfile=`echo $action | sed -e 's@/@\.@g'`
    urlfile=http://api.betaseries.com/$action
    curl -d $parameters $urlfile.xml > $actionfile.xml
    curl -d $parameters $urlfile.json > $actionfile.json
done
