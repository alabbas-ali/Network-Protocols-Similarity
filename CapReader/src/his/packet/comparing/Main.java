package his.packet.comparing;

import java.io.IOException;

import his.CapReader.CapReader;
import io.pkts.framer.FramingException;

public class Main {
	
	public static String[] streams = { 
			"resources/comparing/trafic1_paylod", 
			"resources/comparing/trafic2_paylod", 
			"resources/comparing/trafic3_paylod"
	};
	
	public static void main(String[] args) throws IOException, FramingException {
		System.out.println("::: Read pcap files and extract protocol packets :::");
		
		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap"};
		reader.readFiles("resources/clustring/", files);
		
		
	}
}
