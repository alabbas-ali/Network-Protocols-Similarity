package his.CapReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import io.pkts.PacketHandler;
import io.pkts.packet.Packet;
import io.pkts.packet.SDPPacket;
import io.pkts.packet.TCPPacket;
import io.pkts.packet.rtcp.RtcpPacket;
import io.pkts.packet.rtp.RtpPacket;
import io.pkts.packet.sip.SipPacket;
import io.pkts.protocol.Protocol;


public class MyPacketHandler implements PacketHandler{
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	
	private BufferedWriter rtpWriter;
	private BufferedWriter sipWriter;
	private BufferedWriter rtcpWriter;
	private BufferedWriter sdpWriter;
	
	public MyPacketHandler(String prefix) throws IOException{
		String dirName = "resources/" + prefix + "_paylod/";
		File directory = new File(dirName);
		if (! directory.exists()){
	        directory.mkdir();
		}
		
		File rtpFile = new File(dirName + prefix + "_rtp.txt");
		if(!rtpFile.exists()) rtpFile.createNewFile();
		rtpWriter = new BufferedWriter(new FileWriter(rtpFile));
		
		File sipFile = new File(dirName + prefix + "_sip.txt");
		if(!sipFile.exists()) sipFile.createNewFile();
		sipWriter = new BufferedWriter(new FileWriter(sipFile));
		
		File rtcpFile = new File(dirName + prefix + "_rtcp.txt");
		if(!rtcpFile.exists()) rtcpFile.createNewFile();
		rtcpWriter = new BufferedWriter(new FileWriter(rtcpFile));
		
		File sdpFile = new File(dirName + prefix + "_sdp.txt");
		if(!sdpFile.exists()) sdpFile.createNewFile();
		sdpWriter = new BufferedWriter(new FileWriter(sdpFile));
	}
	
	/**
	 * @param packet  a packet from our capture file
	 */
	public boolean nextPacket(Packet packet) throws IOException {
		if (packet.hasProtocol(Protocol.TCP)) {
			System.out.println("TCP protocol");
			TCPPacket tcp = (TCPPacket) packet.getPacket(Protocol.TCP);
			if(tcp.getPayload() != null) {
				
			}
		}
		
		if (packet.hasProtocol(Protocol.UDP)) {
			System.out.println("UDP protocol");
		}
		
		if (packet.hasProtocol(Protocol.RTP)) {
			System.out.println("Save RTP Paylod");
			RtpPacket rtp = (RtpPacket) packet.getPacket(Protocol.RTP);
			if(rtp.getPayload() != null) {
				rtpWriter.write(this.bytesToHex(rtp.getPayload().getArray()));
				rtpWriter.write("\n");
			}
		}
		
		if (packet.hasProtocol(Protocol.SIP)) {
			System.out.println("Save SIP Paylod");
			SipPacket sip = (SipPacket) packet.getPacket(Protocol.SIP);
			if(sip.getPayload() != null) {
				sipWriter.write(this.bytesToHex(sip.getPayload().getArray()));
				sipWriter.write("\n");
			}
		}
		
		if (packet.hasProtocol(Protocol.RTCP)) {
			System.out.println("Save RTCP Paylod");
			RtcpPacket rtcp = (RtcpPacket) packet.getPacket(Protocol.RTCP);
			if(rtcp.getPayload() != null) {
				rtcpWriter.write(this.bytesToHex(rtcp.getPayload().getArray()));
				rtcpWriter.write("\n");
			}
		}
		
		if (packet.hasProtocol(Protocol.SDP)) {
			System.out.println("Save SDP Paylod");
			SDPPacket sdp = (SDPPacket) packet.getPacket(Protocol.SDP);
			if(sdp.getPayload() != null) {
				sdpWriter.write(this.bytesToHex(sdp.getPayload().getArray()));
				sdpWriter.write("\n");
			}
		}
		
		return true;
	}
	
	private String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
}
