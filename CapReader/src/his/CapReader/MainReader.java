package his.CapReader;

import java.io.IOException;

import io.pkts.Pcap;
import io.pkts.framer.FramingException;

public class MainReader {
	
	public static void main(String[] args) throws IOException, FramingException {

		String FILENAME = "resources/trafic1.pcap";
		Pcap pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic1"));
		pcap.close();
		
		FILENAME = "resources/trafic2.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic2"));
		pcap.close();
		
		FILENAME = "resources/trafic3.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic3"));
		pcap.close();
		
		FILENAME = "resources/trafic4.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic4"));
		pcap.close();
		
		FILENAME = "resources/trafic5.pcap";
		pcap = Pcap.openStream(FILENAME);
		pcap.loop(new MyPacketHandler("trafic5"));
		pcap.close();
		
	}
}
