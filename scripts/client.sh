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
	apt install tshark
}

hash curl 2>/dev/null || {
	printf >&2 "Installing curl.  please waite.";
	apt install curl
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
	randoms[random_verbk$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_namek$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_weekdayk$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_religion$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_thingk$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_daytime$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_weekdays$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_time$i]=$(shuf -i 0-1000 -n 1 | md5sum)
	randoms[random_location$i]=$(shuf -i 0-1000 -n 1 | md5sum)
done

ip=$(ifconfig | grep -A 1 $1 | tail -1 | cut -d ' ' -f 10);

printf "\nPrivate Ethernet IP is $ip";

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
		
		# send SIP messages conversation J with conversation_parameters[I]; 
		while IFS='' read -r line || [[ -n "$line" ]]; do
			cp message.txt temp.txt;
			sed -i s/FROM_IP/$ip/g temp.txt;
			sed -i s/TO_IP/$2/g temp.txt;
			
			messsg=${line//RANDOM_VERB/${randoms[random_verb$i]}};
			messsg=${messsg//RANDOM_THING/${randoms[random_thing$i]}};
			messsg=${messsg//RANDOM_NAME/${randoms[random_name$i]}};
			messsg=${messsg//RANDOM_STUDY/${randoms[random_study$i]}};
			messsg=${messsg//RANDOM_WEEKDAY/${randoms[random_weekday$i]}};
			messsg=${messsg//RANDOM_ACTION/${randoms[random_action$i]}};
			messsg=${messsg//RANDOM_VERBK/${randoms[random_verbk$i]}};
			messsg=${messsg//RANDOM_NAMEK/${randoms[random_namek$i]}};
			messsg=${messsg//RANDOM_WEEKDAYK/${randoms[random_weekdayk$i]}};
			messsg=${messsg//RANDOM_RELIGION/${randoms[random_religion$i]}};
			messsg=${messsg//RANDOM_THINGK/${randoms[random_thingk$i]}};
			messsg=${messsg//RANDOM_DAYTIME/${randoms[random_daytime$i]}};
			messsg=${messsg//RANDOM_WEEKDAYS/${randoms[random_weekdays$i]}};
			messsg=${messsg//RANDOM_TIME/${randoms[random_time$i]}};
			messsg=${messsg//RANDOM_LOCATION/${randoms[random_location$i]}};
			printf "\nSend Message : $messsg \n";
			
			sed -i s/MESSAGE_HERE/$messsg/g temp.txt;
			
			cunt=$(echo -n $messsg | wc -m);
			count=$((324+$cunt))
			
			printf "\nMessage Length is: $count \n";
			sed -i s/LENGTH_M/$count/g temp.txt;
			
			python siprig.py -f temp.txt -d $2 -p 5060 -P 55220 -v;
		done < "conv$j.txt"
		
		
		#echo ${randoms[random_verb$j]};
		#echo ${randoms[random_thing$j]};
		
		sleep 10;
	done
done





	
