# Scripts

This project is an attempt to automate a 50 network experiment or running different protocols with like (html, ftp and sip) for 3 different contents which have a similarity of 50% for 2 of them. 

## Requirments

1. Two Machines with linux OS
2. One Machine is considered as Server with:
⋅⋅ * Apache having 3 webside runing on the urls like:
⋅⋅⋅⋅ 1. MachineIP/website1/?id= [1..50]
⋅⋅⋅⋅ 2. MachineIP/website2/?id= [1..50]
⋅⋅⋅⋅ 3. MachineIP/website3/?id= [1..50]
⋅⋅ * FTP server with 3 shared Folders like:
⋅⋅⋅⋅ 1. ftp://user:password@MachineIP/Temp/Folder1/File[1..50].docx
⋅⋅⋅⋅ 2. ftp://user:password@MachineIP/Temp/Folder2/File[1..50].docx
⋅⋅⋅⋅ 3. ftp://user:password@MachineIP/Temp/Folder3/File[1..50].docx
⋅⋅ * Deploy and run server.py on it ( Used to simulate SIP server).

## Run The Experment

After having the Server Machine Up and running with the required http web side contents and ftp files content.

Run the client.sh script on the Second Machine like:

```
sh client.sh EthernetPort ServerMachineIP RemotUserName RemotUserPassword
```

or 

```
chmod 777 client.sh
./client.sh EthernetPort ServerMachineIP RemotUserName RemotUserPassword
```

The script will automatically download the required dependencies.

It will take some time to run. 

## Output

the client.sh script output will be 3 folders 1/ 2/ 3/ . each contains 50 pcap files for the captured traffic.
