package his.similarity.metrics;

import java.util.Map;

import his.similarity.Impl.CosineSimilarity;
import his.similarity.Impl.JaccardSimilarity;
import his.similarity.Impl.NGramSimilarity;
import his.similarity.Impl.NeedlemanWunchSimilarity;
import his.similarity.Impl.RBFSimilarity;
import his.similarity.Impl.SmithWatermanSimilarity;

public interface Similarity {
	
	double distance(String s1, String s2);
	
	double similarity(String s1, String s2);
	
	double similarity(Map<String, Integer> profile1 , Map<String, Integer> profile2);
	
	double distance(Map<String, Integer> profile1 , Map<String, Integer> profile2);
	
	public static Similarity getInstance(Similarities type, int k) {
		Similarity sim;
		switch (type) {
		case COSINE:
			sim = new CosineSimilarity(k);
			break;
		case JACCARD:
			sim = new JaccardSimilarity(k);
			break;
		case NGRAM:
			sim = new NGramSimilarity(k);
			break;
		case RBF:
			sim = new RBFSimilarity(k);
			break;
		case NEEDLEMAN:
			sim = new NeedlemanWunchSimilarity();
			break;
		case SMITHWATERMAN:
			sim = new SmithWatermanSimilarity();
			break;
		default:
			sim = null;
			break;
		}

		return sim;
	}
}
