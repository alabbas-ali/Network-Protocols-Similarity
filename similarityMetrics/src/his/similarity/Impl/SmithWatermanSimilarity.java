package his.similarity.Impl;

import his.similarity.functions.AffineGap;
import his.similarity.functions.Gap;
import his.similarity.functions.MatchMismatch;
import his.similarity.functions.Substitution;
import his.similarity.metrics.Similarity;
import his.similarity.functions.Math;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Map;

public class SmithWatermanSimilarity implements Similarity {

	private final int windowSize;
	private final Gap gap;
	private Substitution substitution;

	public SmithWatermanSimilarity() {
		this(new AffineGap(-5.0f, -1.0f), new MatchMismatch(5.0f, -3.0f), Integer.MAX_VALUE);
	}

	public SmithWatermanSimilarity(Gap gap, Substitution substitution, int windowSize) {
		this.gap = gap;
		this.substitution = substitution;
		this.windowSize = windowSize;
	}

	@Override
	public double distance(String s1, String s2) {

		return (1 - similarity(s1, s2));
	}

	@Override
	public double similarity(String s1, String s2) {

		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}
		
		if (s1.isEmpty() && s2.isEmpty()) {
			return 1;
		}

		if (s1.equals(s2)) {
			return 1;
		}

		float maxDistance = min(s1.length(), s2.length()) * max(substitution.max(), gap.min());

		return smithWaterman(s1, s2) != 0 ? smithWaterman(s1, s2) / maxDistance : 0;
	}

	private float smithWaterman(String a, String b) {
		
		if (a.isEmpty() || b.isEmpty()) {
			return 0;
		}
		
		final int n = a.length();
		final int m = b.length();

		final float[][] d = new float[n][m];

		// Initialize corner
		float max = d[0][0] = max(0, substitution.compare(a, 0, b, 0));

		// Initialize edge
		for (int i = 0; i < n; i++) {
			
			// Find most optimal deletion
			float maxGapCost = 0;
			for (int k = max(1, i - windowSize); k < i; k++) {
				maxGapCost = max(maxGapCost, d[i - k][0] + gap.value(i - k, i));
			}
			d[i][0] = Math.max(0, maxGapCost, substitution.compare(a, i, b, 0));
			max = max(max, d[i][0]);
		}

		// Initialize edge
		for (int j = 1; j < m; j++) {
			// Find most optimal insertion
			float maxGapCost = 0;
			for (int k = max(1, j - windowSize); k < j; k++) {
				maxGapCost = max(maxGapCost, d[0][j - k] + gap.value(j - k, j));
			}
			d[0][j] = Math.max(0, maxGapCost, substitution.compare(a, 0, b, j));
			max = max(max, d[0][j]);
		}

		// Build matrix
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				float maxGapCost = 0;
				// Find most optimal deletion
				for (int k = max(1, i - windowSize); k < i; k++) {
					maxGapCost = max(maxGapCost, d[i - k][j] + gap.value(i - k, i));
				}
				// Find most optimal insertion
				for (int k = max(1, j - windowSize); k < j; k++) {
					maxGapCost = max(maxGapCost, d[i][j - k] + gap.value(j - k, j));
				}
				// Find most optimal of insertion, deletion and substitution
				d[i][j] = Math.max(0, maxGapCost, d[i - 1][j - 1] + substitution.compare(a, i, b, j));
				max = max(max, d[i][j]);
			}
		}

		return max;
	}

	public double similarity(Map<String, Integer> profile1, Map<String, Integer> profile2) {
		
		return 0;
	}

	public double distance(Map<String, Integer> profile1, Map<String, Integer> profile2) {
		
		return 0;
	}

}
