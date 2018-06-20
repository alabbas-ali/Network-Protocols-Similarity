package his.packet.comparing;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import his.packet.stream.SerialStreamReader;
import io.pkts.framer.FramingException;
import his.similarity.Impl.*;

public class Main {

	public static void main(String[] args) throws IOException, FramingException {
		//System.out.println("::: Read pcap files and extract protocol packets :::");
		
		//CapReader reader = new CapReader();
		//String[] files = { "trafic1.pcap", "trafic2.pcap", "trafic3.pcap" };
		//reader.readFiles("resources/comparing/", files);
		
		String[] stream1_folder = { "resources/comparing/trafic1_paylod" };
		String[] stream2_folder = { "resources/comparing/trafic2_paylod" };
		String[] stream3_folder = { "resources/comparing/trafic3_paylod" };
		Map<String, String> stream1 = readStream(stream1_folder);
		
		System.out.println(stream1.get("http"));
		
		Map<String, String> stream2 = readStream(stream2_folder);
		Map<String, String> stream3 = readStream(stream3_folder);
		
		System.out.println("::: Comparing the same stream :::");
		printTable(2, stream1, stream1);
		
		System.out.println("::: Comparing the 50% same stream :::");
		printTable(2, stream1, stream2);
		
		System.out.println("::: Comparing the tow different streams :::");
		printTable(2, stream1, stream3);
		
	}

	public static void printTable(
			int k,
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		System.out.println();
		System.out.println("Protocoles   |  Cosine  |  Jaccard  |  RBF  |  NGram  | Needleman_Wunch | Smith_Waterman");
		System.out.println("----------------------------------------------------------------------------------------");
		printRow(k, "http", "http", stream1, stream2);
		printRow(k, "sip", "sip", stream1, stream2);
		printRow(k, "rtp", "rtp", stream1, stream2);
		printRow(k, "http", "sip", stream1, stream2);
		printRow(k, "http", "rtp", stream1, stream2);
		printRow(k, "rtp", "sip", stream1, stream2);
	}
	
	public static void printRow(
			int k,
			String protocol1,
			String protocol2,
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		
		CosineSimilarity cosine = new CosineSimilarity(k);
		JaccardSimilarity jaccard = new JaccardSimilarity(k);
		RBFSimilarity rbf = new RBFSimilarity(k);
		NGramSimilarity ngram = new NGramSimilarity(k);
		NeedlemanWunchSimilarity need = new NeedlemanWunchSimilarity();
		SmithWatermanSimilarity smith = new SmithWatermanSimilarity();

		System.out.println(" " + protocol1 + "/" + protocol2 + "    | "
				+ cosine.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "| "
				+ jaccard.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "| "
				+ rbf.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "| "
				+ ngram.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "| "
				+ need.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "| "
				+ smith.similarity(stream1.get(protocol1), stream1.get(protocol2)) + "|");
	}
	
	public static Map<String, String> readStream(String[] folders) {
		
		Map<String, String> stream = new HashMap<String, String>();
		
		try {
			
			SerialStreamReader serialStreams = new SerialStreamReader(folders);
			
			String rtpbacket = "";
			while ((rtpbacket = serialStreams.hasNextRtp()) != null) {
				if (stream.containsKey("rtp")) {
					stream.put("rtp", stream.get("rtp") + rtpbacket);
				} else {
					stream.put("rtp", rtpbacket);
				}
			}
			
			String sipbacket = "";
			while ((sipbacket = serialStreams.hasNextSip()) != null) {
				if (stream.containsKey("sip")) {
					stream.put("sip", stream.get("sip") + sipbacket);
				} else {
					stream.put("sip", sipbacket);
				}
			}
			
			String rtcpbacket = "";
			while ((rtcpbacket = serialStreams.hasNextRtcp()) != null) {
				if (stream.containsKey("rtcp")) {
					stream.put("rtcp", stream.get("rtcp") + rtcpbacket);
				} else {
					stream.put("rtcp", rtcpbacket);
				}
			}
			
			String sdpbacket = "";
			while ((sdpbacket = serialStreams.hasNextSdp()) != null) {
				if (stream.containsKey("sdp")) {
					stream.put("sdp", stream.get("sdp") + sdpbacket);
				} else {
					stream.put("sdp", sdpbacket);
				}
			}
			
			String httpbacket = "";
			while ((httpbacket = serialStreams.hasNextHttp()) != null) {
				if (stream.containsKey("http")) {
					stream.put("http", stream.get("http") + httpbacket);
				} else {
					stream.put("http", httpbacket);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
	}
}
