package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import his.CapReader.CapReader;
import io.pkts.framer.FramingException;

public class Main {

	public static void main(String[] args) throws IOException, FramingException {
		
		CapReader reader = new CapReader();
		String[] files = {"trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap"};
		reader.readFiles(files);
		
		List<Cluster> clusterList = new ArrayList<Cluster>();
		String[] streams = {"trafic1_paylod", "trafic2_paylod", "trafic3_paylod", "trafic4_paylod", "trafic5_paylod"};
		
		//while( hasNext() ) {
			
		//}
		
	}

}

