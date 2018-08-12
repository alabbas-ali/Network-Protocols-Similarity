//package his.pcap.reader;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.jnetpcap.Pcap;
//import org.jnetpcap.packet.PcapPacket;
//import org.jnetpcap.packet.PcapPacketHandler;
//
//public class PcapReader {
//
//	public void readFiles(String folder, String[] files) throws IOException {
//
//		for (int i = 0; i < files.length; i++) {
//			File file = new File(folder + files[i]);
//			if (file != null && file.exists() && file.isFile()) {
//				// System.err.println("Can not open file:" + files[i]);
//				StringBuilder errBuf = new StringBuilder();
//				Pcap pcap = Pcap.openOffline(file.getAbsolutePath(), errBuf);
//
//				final PcapPacketHandler<PseudoPacketHandler> handler = new PcapPacketHandler<PseudoPacketHandler>() {
//					// Handle nextPacket callback.
//					public void nextPacket(PcapPacket packet, PseudoPacketHandler handler) {
//						handler.handle(packet);
//					}
//				};
//
//				PseudoPacketHandler dumpObject = new PseudoPacketDumper(folder, "trafic" + (i + 1));
//
//				try {
//					pcap.loop(Pcap.LOOP_INFINITE, handler, dumpObject);
//
//				} catch (NullPointerException e) {
//					System.out.println("Error in capturing loop" + e.getMessage());
//				} finally {
//					dumpObject.close();
//					pcap.close();
//				}
//			}
//		}
//
//	}
//	
//	
//	
//
//}
