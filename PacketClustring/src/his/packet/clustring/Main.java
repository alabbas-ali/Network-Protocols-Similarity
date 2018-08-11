package his.packet.clustring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import his.packet.stream.RandomStreamReader;
import his.packet.stream.SerialStreamReader;
import his.pcap.reader.CapReader;
import his.similarity.metrics.Similarities;
import io.pkts.framer.FramingException;

public class Main {


	public static void main(String[] args) throws IOException, FramingException {
		
		System.out.println("::: Read pcap files and extract protocol packets :::");
		CapReader reader = new CapReader();
		String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap", "trafic4.pcap", "trafic5.pcap" };
		reader.readFiles("resources/clustring/", files);
		
		String[] streamFoldes = { 
				"resources/clustring/trafic1_paylod", "resources/clustring/trafic2_paylod", "resources/clustring/trafic3_paylod", 
				"resources/clustring/trafic4_paylod","resources/clustring/trafic5_paylod" 
		};
		String[] streamFiles = { "/rtp.txt", "/sip.txt", "/rtcp.txt", "/sdp.txt", "/http.txt" };
		
		System.out.println("::: Initilize Claster Cintromes, Learning from Stream proparty :::");
		List<Category> categories = getCategories(3, streamFoldes, streamFiles);
		
		System.out.println("::: Read Random Steram and Try to classify them using similarities :::");
		RandomStreamReader randomStreams = new RandomStreamReader(streamFoldes, streamFiles);
		
		Categorizer f = new Categorizer(Similarities.COSINE ,categories, 3);
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

	public static List<Category> getCategories(int k, String[] streamFoldes, String[] streamFiles) {
		List<Category> categories = new ArrayList<Category>();
		
		try {
			SerialStreamReader serialStreams = new SerialStreamReader(streamFoldes, streamFiles);

			Category rtpCat = new Category("rtp");
			String rtpbacket = "";
			while ((rtpbacket = serialStreams.hasNextRtp()) != null) {
				rtpCat.addFeatures(Categorizer.getProfile(rtpbacket, k));
			}
			categories.add(rtpCat);

			Category sipCat = new Category("sip");
			String sipbacket = "";
			while ((sipbacket = serialStreams.hasNextSip()) != null) {
				sipCat.addFeatures(Categorizer.getProfile(sipbacket, k));
			}
			categories.add(sipCat);

			Category rtcpCat = new Category("rtcp");
			String rtcpbacket = "";
			while ((rtcpbacket = serialStreams.hasNextRtcp()) != null) {
				rtcpCat.addFeatures(Categorizer.getProfile(rtcpbacket, k));
			}
			categories.add(rtcpCat);

			Category sdpCat = new Category("sdp");
			String sdpbacket = "";
			while ((sdpbacket = serialStreams.hasNextSdp()) != null) {
				sdpCat.addFeatures(Categorizer.getProfile(sdpbacket, k));
			}
			categories.add(sdpCat);

			Category httpCluster = new Category("http");
			String httpbacket = "";
			while ((httpbacket = serialStreams.hasNextHttp()) != null) {
				httpCluster.addFeatures(Categorizer.getProfile(httpbacket, k));
			}
			categories.add(httpCluster);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return categories;
	}

}
