package his.similarity.metrics;

public interface Similarity {
	
	double distance(String s1, String s2);
	
	double similarity(String s1, String s2);
}
