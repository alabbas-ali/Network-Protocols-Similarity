package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import his.CapReader.CapReader;
import io.pkts.framer.FramingException;

public class Main {

	public static String[] streams = { "trafic1_paylod", "trafic2_paylod", "trafic3_paylod", "trafic4_paylod",
			"trafic5_paylod" };

	public static void main(String[] args) throws IOException, FramingException {
		
		System.out.println("::: Read pcap files and extract protocol packets :::");
		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };
		reader.readFiles(files);
		
		System.out.println("::: Initilize Claster Cintromes, Learning from Stream proparty :::");
		List<Cluster> clusterList = getClasters();

		System.out.println("::: Read Random Steram and Try to classify them using similarities :::");
		RandomStreamReader randomStreams = new RandomStreamReader(streams);
		String randombacket;
		while ((randombacket = randomStreams.hasNext()) != null) {
			double[][] similarityResults = new double[clusterList.size()][7];
			
			
			
		}
	}

	public static List<Cluster> getClasters() {
		List<Cluster> clusterList = new ArrayList<Cluster>();
		
		try {
			SerialStreamReader serialStreams = new SerialStreamReader(streams);

			Cluster rtpCluster = new Cluster("rtp");
			String rtpbacket = "";
			while ((rtpbacket = serialStreams.hasNextRtp()) != null) {
				rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 2));
				rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 3));
				rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 4));
			}
			clusterList.add(rtpCluster);

			Cluster sipCluster = new Cluster("sip");
			String sipbacket = "";
			while ((sipbacket = serialStreams.hasNextSip()) != null) {
				sipCluster.addFeatures(Cluster.getProfile(sipbacket, 2));
				sipCluster.addFeatures(Cluster.getProfile(sipbacket, 3));
				sipCluster.addFeatures(Cluster.getProfile(sipbacket, 4));
			}
			clusterList.add(sipCluster);

			Cluster rtcpCluster = new Cluster("sip");
			String rtcpbacket = "";
			while ((rtcpbacket = serialStreams.hasNextRtcp()) != null) {
				Map<String, Integer> secandFeature = Cluster.getProfile(rtcpbacket, 2);
				rtcpCluster.addFeatures(secandFeature);
				rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 3));
				rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 4));
			}
			clusterList.add(rtcpCluster);

			Cluster sdpCluster = new Cluster("sdp");
			String sdpbacket = "";
			while ((sdpbacket = serialStreams.hasNextSdp()) != null) {
				sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 2));
				sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 3));
				sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 4));
			}
			clusterList.add(sdpCluster);

			Cluster httpCluster = new Cluster("http");
			String httpbacket = "";
			while ((httpbacket = serialStreams.hasNextHttp()) != null) {
				Map<String, Integer> secandFeature = Cluster.getProfile(httpbacket, 2);
				httpCluster.addFeatures(secandFeature);
				httpCluster.addFeatures(Cluster.getProfile(httpbacket, 3));
				httpCluster.addFeatures(Cluster.getProfile(httpbacket, 4));
			}
			clusterList.add(httpCluster);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clusterList;
	}

}
