//package his.pcap.reader;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import org.jnetpcap.packet.PcapPacket;
//import org.jnetpcap.protocol.tcpip.Tcp;
//import org.jnetpcap.protocol.voip.Sip;
//
//import his.pcap.reader.http.HttpPacket;
//import his.pcap.readerframer.HttpFramer;
//
//
//public class PseudoPacketDumper implements PseudoPacketHandler {
//	
//	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
//
//	private String dirName;
//	private BufferedWriter rtpWriter;
//	private BufferedWriter sipWriter;
//	private BufferedWriter rtcpWriter;
//	private BufferedWriter sdpWriter;
//	private BufferedWriter httpWriter;
//	private HttpFramer httpFramer;
//	
//	PseudoPacketDumper(String folder, String prefix) throws IOException {
//		this.dirName = folder + prefix + "_paylod/";
//		httpFramer = new HttpFramer();
//		this.init();
//	}
//	
//	
//	private void init() throws IOException {
//		File directory = new File(dirName);
//		if (!directory.exists()) {
//			directory.mkdir();
//		}
//
//		File rtpFile = new File(dirName + "rtp.txt");
//		if (!rtpFile.exists())
//			rtpFile.createNewFile();
//		rtpWriter = new BufferedWriter(new FileWriter(rtpFile));
//
//		File sipFile = new File(dirName + "sip.txt");
//		if (!sipFile.exists())
//			sipFile.createNewFile();
//		sipWriter = new BufferedWriter(new FileWriter(sipFile));
//
//		File rtcpFile = new File(dirName + "rtcp.txt");
//		if (!rtcpFile.exists())
//			rtcpFile.createNewFile();
//		rtcpWriter = new BufferedWriter(new FileWriter(rtcpFile));
//
//		File sdpFile = new File(dirName + "sdp.txt");
//		if (!sdpFile.exists())
//			sdpFile.createNewFile();
//		sdpWriter = new BufferedWriter(new FileWriter(sdpFile));
//
//		File httpFile = new File(dirName + "http.txt");
//		if (!httpFile.exists())
//			httpFile.createNewFile();
//		httpWriter = new BufferedWriter(new FileWriter(httpFile));
//	}
//	
//	@Override
//	public void handle(PcapPacket packet) {
//		//Copy the data.
//        byte[] packetData = packet.getByteArray(0, packet.size());
//        
//        Tcp tcp = new Tcp();
//        Sip sip = new Sip();
//        
//        System.out.println("this is Packet Handler ");
//        System.out.println(new String(packetData));
//        //packet.hasHeader(arg0)
//	}
//
//	@Override
//	public void close() {
//		
//	}
//	
//	
//	private void save(PcapPacket packet, BufferedWriter writer, String protocal) throws IOException {
//		
//		switch (protocal) {
//			case "http":
//				HttpPacket http = (HttpPacket) packet;
//				if (http.getHttpPayload() != null) {
//					if (http.isCompressed()) {
//						try {		
//							System.out.println("Decoding ...." );
//							System.out.println(new String(http.getHttpPayload(), "UTF-8"));
//							System.out.println("Decoding output is : \n " + new String(http.contentdecoding()));
//							writer.write(bytesToHex(http.contentdecoding()));
//							writer.write("\n");
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}else {
//						System.out.println("Save HTML content like is : \n " + new String(http.contentdecoding()));
//						writer.write(bytesToHex(http.getHttpPayload()));
//						writer.write("\n");
//						writer.flush();
//					}
//				}
//				break;
//
//			default:
//				//if(protocal == "rtp")
//				//	System.out.println(this.bytesToHex(packet.getPayload().getArray()));
//				// writer.write(this.bytesToHex(packet.getPayload().getArray()));
//				writer.write("\n");
//				writer.flush();
//				break;
//		}
//	}
//	
//	
//	private String bytesToHex(byte[] bytes) {
//		char[] hexChars = new char[bytes.length * 2];
//		for (int j = 0; j < bytes.length; j++) {
//			int v = bytes[j] & 0xFF;
//			hexChars[j * 2] = hexArray[v >>> 4];
//			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//		}
//		return new String(hexChars);
//	}
//}
//
