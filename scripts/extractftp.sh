
re='^[0-9]+$'

# Start runing the 150 experments
for j in {1..3}
do
	for i in {1..50}
	do
		output = $(tshark -r $j/trafic$i.pcap -Y ftp-data -T fields -e tcp.stream)
		while read -r line; do
			if ! [[ $line =~ $re ]] ; then
			   flow = $line;
			fi
		done <<< "$output"
		
		if [$flow]!=[] ; then
			tshark -nr $j/trafic$i.pcap  -q -z follow,tcp,raw,$flow > $j/trafic{$i}_paylod/ftp.txt
		if
	)
)

 