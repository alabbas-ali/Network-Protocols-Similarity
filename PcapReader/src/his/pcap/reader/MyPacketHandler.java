package his.pcap.reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import his.pcap.reader.http.HttpPacket;
import his.pcap.reader.sip.SIPPacket;
import his.pcap.readerframer.HttpFramer;
import his.pcap.readerframer.SipFramer;
import io.pkts.PacketHandler;
import io.pkts.packet.Packet;
import io.pkts.packet.TCPPacket;
import io.pkts.protocol.Protocol;

public class MyPacketHandler implements PacketHandler {

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	private String dirName;
	private BufferedWriter rtpWriter;
	private BufferedWriter sipWriter;
	private BufferedWriter rtcpWriter;
	private BufferedWriter sdpWriter;
	private BufferedWriter httpWriter;
	private HttpFramer httpFramer;
	private SipFramer sipFramer;

	public MyPacketHandler(String folder, String prefix) throws IOException {
		this.dirName = folder + prefix + "_paylod/";
		httpFramer = new HttpFramer();
		sipFramer = new SipFramer();
		this.init();
	}

	private void init() throws IOException {
		File directory = new File(dirName);
		if (!directory.exists()) {
			directory.mkdir();
		}

		File rtpFile = new File(dirName + "rtp.txt");
		if (!rtpFile.exists())
			rtpFile.createNewFile();
		rtpWriter = new BufferedWriter(new FileWriter(rtpFile));

		File sipFile = new File(dirName + "sip.txt");
		if (!sipFile.exists())
			sipFile.createNewFile();
		sipWriter = new BufferedWriter(new FileWriter(sipFile));

		File rtcpFile = new File(dirName + "rtcp.txt");
		if (!rtcpFile.exists())
			rtcpFile.createNewFile();
		rtcpWriter = new BufferedWriter(new FileWriter(rtcpFile));

		File sdpFile = new File(dirName + "sdp.txt");
		if (!sdpFile.exists())
			sdpFile.createNewFile();
		sdpWriter = new BufferedWriter(new FileWriter(sdpFile));

		File httpFile = new File(dirName + "http.txt");
		if (!httpFile.exists())
			httpFile.createNewFile();
		httpWriter = new BufferedWriter(new FileWriter(httpFile));
	}

	/**
	 * @param packet a packet from our capture file
	 */
	public boolean nextPacket(Packet packet) throws IOException {

		if (packet.hasProtocol(Protocol.TCP)) {
			TCPPacket tcp = (TCPPacket) packet.getPacket(Protocol.TCP);
			if (
				tcp.getPayload() != null && 
				httpFramer.accept(tcp.getPayload())
			) {
				// System.out.println("this is HTTP packet");
				this.save(httpFramer.frame(tcp, tcp.getPayload()), httpWriter, "http");
			}
			
			if (
				tcp.getPayload() != null &&
				sipFramer.accept(tcp.getPayload())
			) {
				SIPPacket s = sipFramer.frame(tcp, tcp.getPayload());
				this.save(s, sipWriter, "mysip");
			}
		}

		if (packet.hasProtocol(Protocol.RTP)) {
			//System.out.println("Save RTP Payload");
			this.save(packet.getPacket(Protocol.RTP), rtpWriter, "rtp");
		}

		if (
			packet.hasProtocol(Protocol.SIP)
			&& !packet.getPacket(Protocol.SIP).getPayload().isEmpty()
		) {
			System.out.println("Save SIP Payload : " +  packet.getPacket(Protocol.SIP).getPayload());
			this.save(packet.getPacket(Protocol.SIP), sipWriter, "sip");
		}

		if (packet.hasProtocol(Protocol.RTCP)) {
			// System.out.println("Save RTCP Payload");
			this.save(packet.getPacket(Protocol.RTCP), rtcpWriter, "rtcp");
		}

		if (packet.hasProtocol(Protocol.SDP)) {
			System.out.print("Save SDP Payload : ");
			//System.out.println(new String(packet.getPacket(Protocol.SDP).getPayload().getArray()));
			this.save(packet.getPacket(Protocol.SDP), sdpWriter, "sdp");
		}

		return true;
	}

	private void save(Packet packet, BufferedWriter writer, String protocal) throws IOException {
		
		switch (protocal) {
			case "http":
				HttpPacket http = (HttpPacket) packet;
				if (http.getHttpPayload() != null) {
					if (http.isCompressed()) {
						try {		
							System.out.println("Decoding ...." );
							System.out.println(new String(http.getHttpPayload(), "UTF-8"));
							System.out.println("Decoding output is : \n " + new String(http.contentdecoding()));
							writer.write(this.bytesToHex(http.contentdecoding()));
							writer.write("\n");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else {
						//System.out.println("Save HTML content like is : \n " + new String(http.getHttpPayload()));
						writer.write(this.bytesToHex(http.getHttpPayload()));
						writer.write("\n");
						writer.flush();
					}
				}
				break;
			case "mysip":
				SIPPacket sip = (SIPPacket) packet;
				if (sip.getSipPayload() != null) {
					//System.out.println("Save SIP content like is : \n " + new String(sip.getSipPayload()));
					writer.write(this.bytesToHex(sip.getSipPayload()));
					writer.write("\n");
					writer.flush();
				}
				break;
			default:
				//if(protocal == "rtp")
				//	System.out.println(this.bytesToHex(packet.getPayload().getArray()));
				writer.write(packet.getPayload().dumpAsHex());
				writer.write("\n");
				writer.flush();
				break;
		}
	}
	
	
	private String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
