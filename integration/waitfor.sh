#!/bin/sh
#$1 - host, $2 - port
counter=10

for i in `seq 1 $counter`
do
    sleep 3
    if nc -z "$1" "$2" > /dev/null 2>&1
    then
	break
    fi
    echo "waiting for $1:$2 to respond"
done
echo "Connected!"

