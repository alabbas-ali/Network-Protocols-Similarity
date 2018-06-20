package his.similarity.Impl;

import java.util.Map;

import his.similarity.functions.KShingling;

public class MahalanobisDistance extends KShingling{
	
	//private RealMatrix invcov = null;
	
	public MahalanobisDistance(final int k) {
		super(k);
	}

	public MahalanobisDistance() {
		super();
	}
    
	public final double distance(String s1, String s2) {
     
    	if (s1 == null || s2 == null) {
			throw new NullPointerException("Strings must not be null");
		}

		if (s1.equals(s2)) {
			return 0;
		}
        
		Map<String, Integer> profile1 = getProfile(s1);
		Map<String, Integer> profile2 = getProfile(s2);
		
		int length;
		
		if (profile1.size() > profile2.size()) {
			length = profile1.size();
		} else {
			length = profile2.size();
		}
		
        // Set the covariance value
        //RealMatrix temp = sc.getCovarianceMatrix();
        
        // But this matrix is always used inverted. Do it now.
        //invcov = new LUDecomposition(temp).getSolver().getInverse();
        
        // Create a new array with the difference between the two arrays
        double [] diff = new double[length];
        
       // for (int i = 0; i < length; i++) {
       //     diff[i] = profile1.get(key) - profile2.get(key);
      //  }
        
        // Left-hand side of the equation: vector * invcov^-1
     //   double [] left = invcov.preMultiply(diff);
        
        // Compute the dot product of both vectors
     //   double res = 0.0;
    //    for (int i = 0; i < diff.length; i++) {
     //       res += left[i] * diff[i];
    //    }
        
     //   return Math.sqrt(res);
        return 0;
    }
    
}
