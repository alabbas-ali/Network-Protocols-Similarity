package his.similarity.Impl;

import java.util.Map;

import his.similarity.metrics.Similarity;

public class EuclideanSimilarity extends KShingling implements Similarity{
	
	public EuclideanSimilarity(final int k) {
		super(k);
	}

	public EuclideanSimilarity() {
		super();
	}
	
	public final double distance(final String s1, final String s2) {
		return 1.0 - similarity(s1, s2);
	}

	public final double similarity(final String s1, final String s2) {
		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}

		if (s1.equals(s2)) {
			return 1;
		}

		if (s1.length() < getK() || s2.length() < getK()) {
			return 0;
		}

		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);
		
		
		/*
		 * gamma : float, default None
        	If None, defaults to 1.0 / n_features
		 */
		double gamma = 1.0;
		
		if (profile1.size() > profile2.size()) {
			gamma = 1.0 / profile1.size();
		}else {
			gamma = 1.0 / profile2.size();
		}

		return Math.exp(
				- gamma * ((norm(profile1) * norm(profile1))
				+ (norm(profile2) * norm(profile2))
				- (2 * dotProduct(profile1, profile2)))
			);
	}
	
//	@Override
//	public double distance(String s1, String s2) {
//		if (s1 == null || s2 == null) {
//			throw new NullPointerException("Strings must not be null");
//		}
//
//		if (s1.equals(s2)) {
//			return 1;
//		}
//
//		Map<String, Integer> profile1 = tokenize(s1);
//		Map<String, Integer> profile2 = tokenize(s2);
//		
//		Set<String> union = new HashSet<String>();
//		union.addAll(profile1.keySet());
//		union.addAll(profile2.keySet());
//		
//		float totalDistance = 0.0f;
//		for (final String token : union) {
//            int countInString1 = 0;
//            int countInString2 = 0;
//            if (profile1.get(token) != null) {
//                countInString1 += profile1.get(token);
//             }
//             
//             if (profile2.get(token) != null) {
//                 countInString2 += profile2.get(token);
//             }
//             
//             totalDistance += ((countInString1 - countInString2)^2);
//		}
//		
//		return (float) Math.sqrt(totalDistance);
//	}
//	
//	@Override
//	public double similarity(final String string1, final String string2) {
//		if (string1 == null || string2 == null) {
//			throw new NullPointerException("Strings must not be null");
//		}
//
//		if (string1.equals(string2)) {
//			return 1;
//		}
//
//		Map<String, Integer> profile1 = tokenize(string1);
//		Map<String, Integer> profile2 = tokenize(string2);
//		int size1 = profile1.values().stream().mapToInt(i->i).sum();
//		int size2 = profile1.values().stream().mapToInt(i->i).sum();
//		double totalPossible = (double) Math.sqrt((size1 * size1) + (size2 * size2));
//        final double totalDistance = unNormalisedSimilarity(profile1, profile2);
//        return (totalPossible - totalDistance) / totalPossible;
//    }
//
//	private double unNormalisedSimilarity(Map<String, Integer> profile1, Map<String, Integer> profile2) {
//		
//		Set<String> union = new HashSet<String>();
//		union.addAll(profile1.keySet());
//		union.addAll(profile2.keySet());
//		
//		float totalDistance = 0.0f;
//		for (final String token : union) {
//            int countInString1 = 0;
//            int countInString2 = 0;
//            if (profile1.get(token) != null) {
//               countInString1 += profile1.get(token);
//            }
//            
//            if (profile2.get(token) != null) {
//                countInString2 += profile2.get(token);
//            }
//            
//            totalDistance += ((countInString1 - countInString2) * (countInString1 - countInString2));
//		}
//		
//		totalDistance = (float) Math.sqrt(totalDistance);
//        return totalDistance;
//	}

}
