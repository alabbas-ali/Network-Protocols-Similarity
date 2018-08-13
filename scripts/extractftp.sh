#!/bin/bash
# A simple script

re='^[0-9]+$';
flow='';

# Start runing the 150 experments
for j in {1..3}
do
	for i in {1..50}
	do
	
		output=$(tshark -r $j/traffic$i.pcap -Y ftp-data -T fields -e tcp.stream);
		
		#printf "\nPrivate Ethernet IP is $output";
		
		while read -r line; do 
			if ! [[ $line =~ $re ]] ; then
				printf "\nEmpty or not numeric line : $line \n"; 
			else
				flow=$line;
			fi
		done <<< "$output"
		
		if [ -z "$flow" ]
		then
			printf "\nFlow is empty !!! \n";
		else
			printf "\nFlow is $flow \n"; 
			tshark -nr $j/traffic$i.pcap  -q -z follow,tcp,raw,$flow > $j/trafic${i}_paylod/ftp.txt;
			
			sed -i -e 1,8d $j/trafic${i}_paylod/ftp.txt;
			
			sed -i '$ d' $j/trafic${i}_paylod/ftp.txt;
		fi
	done
done

 