package his.CapReader;

import java.io.IOException;

import io.pkts.Pcap;
import io.pkts.framer.FramingException;

public class CapReader {

	public void readFiles(String[] files_names) throws IOException, FramingException {

		for (int i = 0; i < files_names.length; i++) {
			String file = "resources/" + files_names[i];
			Pcap pcap = Pcap.openStream(file);
			pcap.loop(new MyPacketHandler("trafic" + (i + 1)));
			pcap.close();
		}
	}
}
