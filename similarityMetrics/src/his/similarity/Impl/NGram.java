package his.similarity.Impl;

import java.util.ArrayList;
import java.util.List;

import his.similarity.metrics.Similarity;

/**
 * Does some thing in old style.
 *
 * @deprecated use {@link #new NGramSimilarity()} instead.  
 */
@Deprecated()
public class NGram implements Similarity {

	private List<result> results;
	private static final int DEFAULT_N = 2;
	private final int n;

	public NGram(int n) {
		this.n = n;
	}
	
	public NGram() {
		results = new ArrayList<result>();
		this.n = DEFAULT_N;
	}
	
	@Override
	public double distance(String s1, String s2) {
		
		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}

		if (s1.equals(s2)) {
			return 1;
		}
		
		return this.getSimilarity(s1, s2, this.n);
	}

	@Override
	public double similarity(String s1, String s2) {
		return this.getSimilarity(s1, s2, this.n);
	}

	private double getSimilarity(String wordOne, String wordTwo, int n) {
		List<result> res1 = processString(wordOne, n);
		// displayResult(res1);
		List<result> res2 = processString(wordTwo, n);
		// displayResult(res2);
		int c = common(res1, res2);
		int u = union(res1, res2);
		double sim = (double) c / (double) u;
		return sim;
	}

	private int common(List<result> One, List<result> Two) {
		int res = 0;
		for (int i = 0; i < One.size(); i++) {
			for (int j = 0; j < Two.size(); j++) {
				if (One.get(i).theWord.equalsIgnoreCase(Two.get(j).theWord))
					res++;
			}
		}
		return res;
	}

	private int union(List<result> One, List<result> Two) {
		List<result> t = One;
		for (int i = 0; i < Two.size(); i++) {
			//int pos = -1;
			boolean found = false;
			for (int j = 0; j < t.size() && !found; j++) {
				if (Two.get(i).theWord.equalsIgnoreCase(t.get(j).theWord)) {
					found = true;
				}
				//pos = j;
			}

			if (!found) {
				result r = Two.get(i);
				t.add(r);
			}
		}
		return t.size();
	}

	private List<result> processString(String c, int n) {
		List<result> t = new ArrayList<result>();

		String spacer = "";
		for (int i = 0; i < n - 1; i++) {
			spacer = spacer + "%";
		}
		c = spacer + c + spacer;

		for (int i = 0; i < c.length(); i++) {
			if (i <= (c.length() - n)) {
				if (contains(c.substring(i, n + i)) > 0) {
					t.get(i).setTheCount(results.get(i).getTheCount() + 1);
				} else {
					t.add(new result(c.substring(i, n + i), 1));
				}
			}
		}
		return t;
	}
	
	private int contains(String c) {
		for (int i = 0; i < results.size(); i++) {
			if (results.get(i).theWord.equalsIgnoreCase(c))
				return i;
		}
		return 0;
	}
	
	@SuppressWarnings("unused")
	private void displayResult(List<result> d) {
		for (int i = 0; i < d.size(); i++) {
			System.out.println(d.get(i).theWord + " occurred " + d.get(i).theCount + " times");
		}
	}

	private class result {
		private String theWord;
		private int theCount;

		public result(String w, int c) {
			theWord = w;
			theCount = c;
		}

		public void setTheCount(int c) {
			theCount = c;
		}

		@SuppressWarnings("unused")
		public String getTheWord() {
			return theWord;
		}

		public int getTheCount() {
			return theCount;
		}
	}

}