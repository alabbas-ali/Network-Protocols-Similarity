package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class RBFSimilarityTest {
	
	@Test
    public void testSimilarity() {
		RBFSimilarity instance = new RBFSimilarity();
		
		double result = instance.similarity("ABCD", "DRM");
        assertEquals(0.4, result, 0.1);
        
        result = instance.similarity("ABECD", "ABEF");
        assertEquals(0.6, result, 0.1);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        
        instance = new RBFSimilarity(3);
        result = instance.similarity("ABC", "ABCE");
        assertEquals(0.6, result, 0.1);
        
        instance = new RBFSimilarity(3); 
        result = instance.similarity("Test String1", "Test String2");
		assertEquals(0.8, result, 0.1);
        
        NullTests.testSimilarity(instance);
	}
	
	@Test
    public void testDistance() {
		RBFSimilarity instance = new RBFSimilarity(2);
		double result = instance.distance("ABECD", "ABEF");
        assertEquals(1.7, result, 0.1);
        
        String s0 = "ABABABAB";
        String s1 = "ABABABABC";
        String s2 = "POIULKJH";
        
        Assert.assertTrue(instance.distance(s0, s1) < instance.distance(s0, s2));
        
        assertEquals(0.0, instance.distance("ABCDF", "ABCDF"), 0.0);
        assertEquals(1.4, instance.distance("12", "34"), 1.01);
        
        NullTests.testDistance(instance);
	}
}
