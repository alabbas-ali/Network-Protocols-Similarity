package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import his.packet.stream.RandomStreamReader;
import his.packet.stream.SerialStreamReader;
import his.pcap.reader.CapReader;
import io.pkts.framer.FramingException;

public class Main {

	public static String[] streams = { 
			"resources/clustring/trafic1_paylod", 
			"resources/clustring/trafic2_paylod", 
			"resources/clustring/trafic3_paylod", 
			"resources/clustring/trafic4_paylod",
			"resources/clustring/trafic5_paylod" 
	};

	public static void main(String[] args) throws IOException, FramingException {
		
		System.out.println("::: Read pcap files and extract protocol packets :::");
		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };
		reader.readFiles("resources/clustring/", files);
		
		System.out.println("::: Initilize Claster Cintromes, Learning from Stream proparty :::");
		List<Category> categories = getCategories();

		System.out.println("::: Read Random Steram and Try to classify them using similarities :::");
		RandomStreamReader randomStreams = new RandomStreamReader(streams);
		
		FingerPrint f = new FingerPrint(categories, 2);
		String randombacket;
		while ((randombacket = randomStreams.hasNext()) != null) {
			switch (f.categorize(randombacket)) {
			case "rtp":

				break;
			case "sip":

				break;
			case "rtcp":

				break;
			case "sdp":
				
			case "http":
				
				break;
			default:
				break;
			} 
		}
	}

	public static List<Category> getCategories() {
		List<Category> categories = new ArrayList<Category>();
		
		try {
			SerialStreamReader serialStreams = new SerialStreamReader(streams);

			Category rtpCat = new Category("rtp");
			String rtpbacket = "";
			while ((rtpbacket = serialStreams.hasNextRtp()) != null) {
				rtpCat.addFeatures(FingerPrint.getProfile(rtpbacket, 2));
				rtpCat.addFeatures(FingerPrint.getProfile(rtpbacket, 3));
				rtpCat.addFeatures(FingerPrint.getProfile(rtpbacket, 4));
			}
			categories.add(rtpCat);

			Category sipCat = new Category("sip");
			String sipbacket = "";
			while ((sipbacket = serialStreams.hasNextSip()) != null) {
				sipCat.addFeatures(FingerPrint.getProfile(sipbacket, 2));
				sipCat.addFeatures(FingerPrint.getProfile(sipbacket, 3));
				sipCat.addFeatures(FingerPrint.getProfile(sipbacket, 4));
			}
			categories.add(sipCat);

			Category rtcpCat = new Category("rtcp");
			String rtcpbacket = "";
			while ((rtcpbacket = serialStreams.hasNextRtcp()) != null) {
				rtcpCat.addFeatures(FingerPrint.getProfile(rtcpbacket, 2));
				rtcpCat.addFeatures(FingerPrint.getProfile(rtcpbacket, 3));
				rtcpCat.addFeatures(FingerPrint.getProfile(rtcpbacket, 4));
			}
			categories.add(rtcpCat);

			Category sdpCat = new Category("sdp");
			String sdpbacket = "";
			while ((sdpbacket = serialStreams.hasNextSdp()) != null) {
				sdpCat.addFeatures(FingerPrint.getProfile(sdpbacket, 2));
				sdpCat.addFeatures(FingerPrint.getProfile(sdpbacket, 3));
				sdpCat.addFeatures(FingerPrint.getProfile(sdpbacket, 4));
			}
			categories.add(sdpCat);

			Category httpCluster = new Category("http");
			String httpbacket = "";
			while ((httpbacket = serialStreams.hasNextHttp()) != null) {
				httpCluster.addFeatures(FingerPrint.getProfile(httpbacket, 2));
				httpCluster.addFeatures(FingerPrint.getProfile(httpbacket, 3));
				httpCluster.addFeatures(FingerPrint.getProfile(httpbacket, 4));
			}
			categories.add(httpCluster);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categories;
	}

}
