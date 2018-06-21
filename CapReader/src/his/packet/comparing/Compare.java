package his.packet.comparing;

import his.similarity.Impl.CosineSimilarity;
import his.similarity.Impl.JaccardSimilarity;
import his.similarity.Impl.NGramSimilarity;
import his.similarity.Impl.NeedlemanWunchSimilarity;
import his.similarity.Impl.RBFSimilarity;
import his.similarity.Impl.SmithWatermanSimilarity;

public class Compare {
	
	CosineSimilarity cosine;
	JaccardSimilarity jaccard;
	RBFSimilarity rbf ;
	NGramSimilarity ngram;
	NeedlemanWunchSimilarity need;
	SmithWatermanSimilarity smith;
	
	Compare(int k){
		cosine = new CosineSimilarity(k);
		jaccard = new JaccardSimilarity(k);
		rbf = new RBFSimilarity(k);
		ngram = new NGramSimilarity(k);
		need = new NeedlemanWunchSimilarity();
		smith = new SmithWatermanSimilarity();
	}
	
	
}
