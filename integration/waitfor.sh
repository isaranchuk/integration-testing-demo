#!/bin/bash
#$1 - host, $2 - port
counter=10

for i in `seq 1 $counter`
do
    sleep 4
    if echo -n > /dev/tcp/$1/$2
    then
	break
    fi
    echo "waiting for $1:$2 to respond"
done
echo "Connected!"
