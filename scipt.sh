#!/bin/bash
# A simple script

declare -A randoms

for i in {1..50} 
do
    randoms[random_verb$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_thing$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_name$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_study$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_weekday$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_action$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_verb2$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_name2$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_weekday2$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_religion$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_thing2$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_daytime$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_weekday3$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_time$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_location$i]=$(shuf -i 0-1000 -n 1 | md5sum)
done

for j in {1..2} 
do
	for j in {1..50} 
	do
		echo ${randoms[random_verb$j]}
		echo ${randoms[random_thing$j]}
	done
done





	