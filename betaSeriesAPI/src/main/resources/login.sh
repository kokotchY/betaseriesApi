#!/bin/sh

loginFile=$1
login=`cat credentials/$loginFile | head -n1`
plainPassword=`cat credentials/$loginFile | head -2 | tail -1`
password=`python md5.py $plainPassword`
key=`cat ../../../apiKey.txt`

curl -d "key=$key&login=$login&password=$password" http://api.betaseries.com/members/auth.json
