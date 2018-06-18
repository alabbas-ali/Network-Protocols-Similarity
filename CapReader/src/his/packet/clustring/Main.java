package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import his.CapReader.CapReader;
import io.pkts.framer.FramingException;

public class Main {

	public static void main(String[] args) throws IOException, FramingException {

		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };
		reader.readFiles(files);

		String[] streams = { "trafic1_paylod", "trafic2_paylod", "trafic3_paylod", "trafic4_paylod", "trafic5_paylod" };

		List<Cluster> clusterList = new ArrayList<Cluster>();
		
		SerialStreamReader serialStreams = new SerialStreamReader(streams);
		
		Cluster httpCluster = new Cluster("http");
		String httpbacket = "";
		while ((httpbacket = serialStreams.hasNext()) != null) {
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 2));
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 3));
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 4));
		}
		
		clusterList.add(httpCluster);
		
		Cluster httpCluster = new Cluster("http");
		
		RandomStreamReader randomStreams = new RandomStreamReader(streams);

		String randombacket;
		while ((randombacket = randomStreams.hasNext()) != null) {
			
		}

	}

}
