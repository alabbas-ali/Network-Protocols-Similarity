package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class NeedlemanWunchSimilarityTest {
	
	@Test
    public void testSimilarity() {
		NeedlemanWunchSimilarity instance = new NeedlemanWunchSimilarity();
		
		double result = instance.similarity("ABCDF", "12346");
        assertEquals(0.5, result, 0.1);
        
        result = instance.similarity("ABECD", "ABEF");
        assertEquals(0.6, result, 0.1);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        
        NullTests.testSimilarity(instance);
	}
	
	@Test
    public void testDistance() {
		NeedlemanWunchSimilarity instance = new NeedlemanWunchSimilarity();
		double result = instance.distance("ABECD", "ABEF");
        assertEquals(0.3, result, 0.1);
        
        String s0 = "ABABABAB";
        String s1 = "ABABABABC";
        String s2 = "POIULKJH";
        
        Assert.assertTrue(instance.distance(s0, s1) < instance.distance(s0, s2));
        
        assertEquals(0.0, instance.distance("ABCDF", "ABCDF"), 0.0);
        assertEquals(0.0, instance.distance("12", "34"), 1.0);
        
        NullTests.testDistance(instance);
	}
}
