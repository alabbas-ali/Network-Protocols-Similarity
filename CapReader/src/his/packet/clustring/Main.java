package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import his.CapReader.CapReader;
import io.pkts.framer.FramingException;

public class Main {

	public static String[] streams = { "trafic1_paylod", "trafic2_paylod", "trafic3_paylod", "trafic4_paylod", "trafic5_paylod" };
	
	public static void main(String[] args) throws IOException, FramingException {

		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };
		reader.readFiles(files);

		List<Cluster> clusterList = getClasters();
		
		RandomStreamReader randomStreams = new RandomStreamReader(streams);
		String randombacket;
		while ((randombacket = randomStreams.hasNext()) != null) {
			
		}
	}
	
	public static List<Cluster> getClasters() throws IOException {
		List<Cluster> clusterList = new ArrayList<Cluster>();
		
		SerialStreamReader serialStreams = new SerialStreamReader(streams);
		
		Cluster rtpCluster = new Cluster("rtp");
		String rtpbacket = "";
		while ((rtpbacket = serialStreams.hasNextRtp()) != null) {
			rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 2));
			rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 3));
			rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 4));
			rtpCluster.addFeatures(Cluster.getProfile(rtpbacket, 5));
		}
		clusterList.add(rtpCluster);
		
		Cluster sipCluster = new Cluster("sip");
		String sipbacket = "";
		while ((sipbacket = serialStreams.hasNextSip()) != null) {
			sipCluster.addFeatures(Cluster.getProfile(sipbacket, 2));
			sipCluster.addFeatures(Cluster.getProfile(sipbacket, 3));
			sipCluster.addFeatures(Cluster.getProfile(sipbacket, 4));
			sipCluster.addFeatures(Cluster.getProfile(sipbacket, 5));
		}
		clusterList.add(sipCluster);
		
		
		Cluster rtcpCluster = new Cluster("sip");
		String rtcpbacket = "";
		while ((rtcpbacket = serialStreams.hasNextRtcp()) != null) {
			rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 2));
			rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 3));
			rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 4));
			rtcpCluster.addFeatures(Cluster.getProfile(rtcpbacket, 5));
		}
		clusterList.add(rtcpCluster);
		
		
		Cluster sdpCluster = new Cluster("sdp");
		String sdpbacket = "";
		while ((sdpbacket = serialStreams.hasNextSdp()) != null) {
			sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 2));
			sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 3));
			sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 4));
			sdpCluster.addFeatures(Cluster.getProfile(sdpbacket, 5));
		}
		clusterList.add(sdpCluster);
		
		Cluster httpCluster = new Cluster("http");
		String httpbacket = "";
		while ((httpbacket = serialStreams.hasNextHttp()) != null) {
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 2));
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 3));
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 4));
			httpCluster.addFeatures(Cluster.getProfile(httpbacket, 5));
		}
		clusterList.add(httpCluster);
		
		return clusterList;
	}

}
