package his.pcap.reader;

import java.io.IOException;

import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapHandle.TimestampPrecision;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;

import io.pkts.Pcap;
import io.pkts.framer.FramingException;

public class CapReader {

	public void readFiles(String folder, String[] files_names) throws IOException, FramingException {

		for (int i = 0; i < files_names.length; i++) {
			String file = folder + files_names[i];
			Pcap pcap = Pcap.openStream(file);
			pcap.loop(new MyPacketHandler(folder, "trafic" + (i + 1)));
			pcap.close();
			
			
		}
	}
	
	public static void main(String[] args) throws IOException, FramingException, PcapNativeException {

		String[] streams = { "resources/clustring/trafic1_paylod", "resources/clustring/trafic2_paylod",
				"resources/clustring/trafic3_paylod", "resources/clustring/trafic4_paylod",
				"resources/clustring/trafic5_paylod" };
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };

		System.out.println("::: Read pcap files and extract protocol packets :::");

		PcapHandle handle;

		try {
			handle = Pcaps.openOffline(files[0], TimestampPrecision.NANO);
		} catch (PcapNativeException e) {
			handle = Pcaps.openOffline(files[0]);
		}
	}
	
}
