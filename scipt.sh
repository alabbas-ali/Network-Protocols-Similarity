#!/bin/bash
# A simple script

if [[ ( -z "$1" ) || ( -z "$2" ) || ( -z "$3" ) || ( -z "$4" ) ]] 
then
    printf "No argument supplied \n";
	printf "Please Use the script like :\n";
	printf " scipt.sh ethernet_port server_IP ftp_user ftp_password \n";
	exit 0;
fi

hash tshark 2>/dev/null || {
	printf >&2 "Installing tshark.  please waite.";
	sudo apt install tshark
}

hash curl 2>/dev/null || {
	printf >&2 "Installing curl.  please waite.";
	sudo apt install curl
}

declare -A randoms

# random_init() the strings for SIP conversation
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


# Start runing the 150 experments
for j in {1..3}
do
	# remove the directory $j if exist and create new one
	[ ! -d $j ] || rm -rf $j;
	mkdir $j;
	
	for i in {1..50}
	do
		# Start capture of ethernet_port 
		printf "\nStart experiment $j captures traffic $i. \n";
		tshark -i $1 -w $j/traffic$i.pcap -a duration:12 & 
		sleep 2;
		# do HTTP curl request to web page J with parameter I;
		printf "\ncurl to http://$2/webpage$j?id=$i \n";
		curl -i -H "Accept: application/html" \
				-H "Content-Type: application/html" \
				-X GET http://$2/webpage$j?id=$i >> http_output.txt
		rm -rf http_output.txt;
		# do FTP request to folderJ file I; 
		mkdir ftptmp;
		wget -c ftp://$3:$4@$2/folder$j/file$i.docx \
				-p /ftptmp/
		rm -rf ftptmp;
		#echo ${randoms[random_verb$j]};
		#echo ${randoms[random_thing$j]};
		
		sleep 10;
	done
done





	
