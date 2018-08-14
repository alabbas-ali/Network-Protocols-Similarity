# Pcap Reader

this is a project to read the pcap files captured throw Wireshark or tshark and extract the payload of RTP, RTCP, SDP, HTTP and SIP protocols

this project depend on io.pkts https://github.com/aboutsip/pkts

## Usage

```
PcapReader reader = new PcapReader();
reader.readFiles(String foldes, String[] files);
```

example:

```
String foldes = "resources/comparing/";
String[] files = {"trafic1.pcap", "trafic2.pcap", "trafic3.pcap"};
PcapReader reader = new PcapReader();
reader.readFiles(String[] foldes, String[] files);
```

## Output

the out put will be a lest of text files containing the HEX representation of the protocols payload, each bucket payload in a new line.
this files will be saved in folders corresponding to files count like

- folder/trafic1_paylod/
- folder/trafic2_paylod/
- folder/trafic3_paylod/

for the example above, it will be:

- resources/comparing/trafic1_paylod/
- resources/comparing/trafic2_paylod/
- resources/comparing/trafic3_paylod/
