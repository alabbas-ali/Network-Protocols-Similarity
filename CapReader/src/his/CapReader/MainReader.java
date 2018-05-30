package his.CapReader;

import java.io.IOException;

import io.pkts.Pcap;
import io.pkts.framer.FramingException;

public class MainReader {
	
	public static void main(String[] args) throws IOException, FramingException {

		String FILENAME = "resources/trafic.pcap";
		Pcap pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic"));
		pcap.close();
		
		FILENAME = "resources/trafic1.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic1"));
		pcap.close();
		
		FILENAME = "resources/trafic2.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic2"));
		pcap.close();
	}
}
