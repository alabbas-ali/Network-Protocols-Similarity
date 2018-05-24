package his.CapReader;

import java.io.IOException;

import io.pkts.Pcap;
import io.pkts.framer.FramingException;

public class MainReader {
	
	public static void main(String[] args) throws IOException, FramingException {

		final String FILENAME = "resources/trafic.pcap";
		
		final Pcap pcap = Pcap.openStream(FILENAME);
		
		pcap.loop(new MyPacketHandler("paylod"));
		
		pcap.close();
	}
}
