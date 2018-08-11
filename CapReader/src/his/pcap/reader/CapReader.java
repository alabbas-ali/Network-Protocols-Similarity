package his.pcap.reader;

import java.io.IOException;

import io.pkts.Pcap;

public class CapReader {

	public void readFiles(String folder, String[] files) throws IOException {
		for (int i = 0; i < files.length; i++) {
			String file = folder + files[i];
			Pcap pcap = Pcap.openStream(file);
			pcap.loop(new MyPacketHandler(folder, "trafic" + (i + 1)));
			pcap.close();
		}
	}
	
}
