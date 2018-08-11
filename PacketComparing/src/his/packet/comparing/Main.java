package his.packet.comparing;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import his.packet.stream.SerialStreamReader;
import his.pcap.reader.CapReader;
import his.similarity.metrics.Similarities;
import his.similarity.metrics.Similarity;

public class Main {
	
	public static Similarity cosine = Similarity.getInstance(Similarities.COSINE, 2);
	public static Similarity jaccard = Similarity.getInstance(Similarities.JACCARD, 2);
	public static Similarity rbf = Similarity.getInstance(Similarities.RBF, 2);
	public static Similarity ngram = Similarity.getInstance(Similarities.NGRAM, 2);
	public static Similarity need = Similarity.getInstance(Similarities.NEEDLEMAN, 2);
	public static Similarity smith = Similarity.getInstance(Similarities.SMITHWATERMAN, 2);

	public static void main(String[] args) throws IOException {
		
		String[] files = new String[1];
//		for (int i = 0; i < 50 ; i++) {
//			files[i] = "traffic" + (i + 1) + ".pcap";
//		}
		files[0] = "traffic66.pcap";
		CapReader reader = new CapReader();
		reader.readFiles("resources/1/", files);
		
		String[] stream1_folder = { "resources/comparing/trafic1_paylod" };
		String[] stream2_folder = { "resources/comparing/trafic2_paylod" };
		String[] stream3_folder = { "resources/comparing/trafic3_paylod" };
		Map<String, String> stream1 = readStream(stream1_folder);
		Map<String, String> stream2 = readStream(stream2_folder);
		Map<String, String> stream3 = readStream(stream3_folder);
		
		System.out.println("::: Comparing the same stream :::");
		printTable(stream1, stream1);
		
		System.out.println("::: Comparing the 50% same stream :::");
		printTable(stream1, stream2);
		
		System.out.println("::: Comparing the tow different streams :::");
		printTable(stream1, stream3);
		
	}

	public static void printTable(
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		System.out.println();
		System.out.println("Protocoles    |       Cosine       |      Jaccard       |        RBF         |        NGram       |   Needleman_Wunch  |   Smith_Waterman  |");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		printRow("http", "http", stream1, stream2);
		printRow("sip", "sip", stream1, stream2);
		printRow("rtp", "rtp", stream1, stream2);
		printRow("sdp", "sdp", stream1, stream2);
		printRow("http", "sip", stream1, stream2);
		printRow("http", "rtp", stream1, stream2);
		printRow("http", "sdp", stream1, stream2);
		printRow("rtp", "sip", stream1, stream2);
		printRow("sip", "sdp", stream1, stream2);
		printRow("rtp", "sdp", stream1, stream2);
	}
	
	public static void printRow(
			String protocol1,
			String protocol2,
			Map<String, String> stream1,
			Map<String, String> stream2
	) {
		NumberFormat formatter = new DecimalFormat("#0.0000");
		System.out.format("%15s", protocol1 + "/" + protocol2 + " |");
		System.out.format("%21s", formatter.format(cosine.similarity(stream1.get(protocol1), stream2.get(protocol2))) + " |");
		System.out.format("%21s", formatter.format(jaccard.similarity(stream1.get(protocol1), stream2.get(protocol2))) + " |");
		System.out.format("%21s", formatter.format(rbf.similarity(stream1.get(protocol1), stream2.get(protocol2))) + " |");
		System.out.format("%21s", formatter.format(ngram.similarity(stream1.get(protocol1), stream2.get(protocol2))) + " |");
		System.out.format("%21s", formatter.format(need.similarity(stream1.get(protocol1), stream2.get(protocol2))) + " |");
		//System.out.format("%21s", formatter.format(smith.similarity(stream1.get(protocol1), stream1.get(protocol2))) + " |");
		System.out.println();
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
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
