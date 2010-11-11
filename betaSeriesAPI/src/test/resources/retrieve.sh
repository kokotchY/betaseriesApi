#!/bin/sh

for i in `cat list`
do
    curl -d `echo $i | cut -d '|' -f 2` http://api.betaseries.com/`echo $i \
        | cut -d '|' -f 1`.xml > `echo $i | cut -d '|' -f 1 | sed -e 's@/@\.@g'`.xml;
    curl -d `echo $i | cut -d '|' -f 2` http://api.betaseries.com/`echo $i \
    | cut -d '|' -f 1`.json > `echo $i | cut -d '|' -f 1 | sed -e 's@/@\.@g'`.json
done
