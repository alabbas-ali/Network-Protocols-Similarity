package his.similarity.metrics;

import java.util.Map;

public interface Similarity {
	
	double distance(String s1, String s2);
	
	double similarity(String s1, String s2);
	
	double similarity(Map<String, Integer> profile1 , Map<String, Integer> profile2);
	
	double distance(Map<String, Integer> profile1 , Map<String, Integer> profile2);
}
