package his.similarity.Impl;

import his.similarity.metrics.Similarity;

public class NGramSimilarity implements Similarity {
	
	private static final int DEFAULT_N = 2;
    private final int n;
   
    public NGramSimilarity(final int n) {
        this.n = n;
    }

    public NGramSimilarity() {
        this.n = DEFAULT_N;
    }
    
	@Override
	public double distance(String s1, String s2) {
		if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}

		if (s1.equals(s2)) {
			return 0;
		}
		
		final char special = '\n';
        final int sl = s1.length();
        final int tl = s2.length();

        if (sl == 0 || tl == 0) {
            return 1;
        }

        int cost = 0;
        if (sl < n || tl < n) {
            for (int i = 0, ni = Math.min(sl, tl); i < ni; i++) {
                if (s1.charAt(i) == s2.charAt(i)) {
                    cost++;
                }
            }
            return (float) cost / Math.max(sl, tl);
        }

        char[] sa = new char[sl + n - 1];
        float[] p; //'previous' cost array, horizontally
        float[] d; // cost array, horizontally
        float[] d2; //placeholder to assist in swapping p and d

        //construct sa with prefix
        for (int i = 0; i < sa.length; i++) {
            if (i < n - 1) {
                sa[i] = special; //add prefix
            } else {
                sa[i] = s1.charAt(i - n + 1);
            }
        }
        p = new float[sl + 1];
        d = new float[sl + 1];

        // indexes into strings s and t
        int i; // iterates through source
        int j; // iterates through target

        char[] t_j = new char[n]; // jth n-gram of t

        for (i = 0; i <= sl; i++) {
            p[i] = i;
        }

        for (j = 1; j <= tl; j++) {
            //construct t_j n-gram
            if (j < n) {
                for (int ti = 0; ti < n - j; ti++) {
                    t_j[ti] = special; //add prefix
                }
                for (int ti = n - j; ti < n; ti++) {
                    t_j[ti] = s2.charAt(ti - (n - j));
                }
            } else {
                t_j = s2.substring(j - n, j).toCharArray();
            }
            d[0] = j;
            for (i = 1; i <= sl; i++) {
                cost = 0;
                int tn = n;
                //compare sa to t_j
                for (int ni = 0; ni < n; ni++) {
                    if (sa[i - 1 + ni] != t_j[ni]) {
                        cost++;
                    } else if (sa[i - 1 + ni] == special) {
                        //discount matches on prefix
                        tn--;
                    }
                }
                float ec = (float) cost / tn;
                // minimum of cell to the left+1, to the top+1,
                // diagonally left and up +cost
                d[i] = Math.min(
                        Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + ec);
            }
            // copy current distance counts to 'previous row' distance counts
            d2 = p;
            p = d;
            d = d2;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return p[sl] / Math.max(tl, sl);
	}

	@Override
	public double similarity(String s1, String s2) {
		
		return 1 - this.distance(s1, s2);
	}

}
