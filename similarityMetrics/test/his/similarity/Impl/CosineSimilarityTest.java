package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class CosineSimilarityTest {
	
	@Test
    public void testSimilarity() {
		CosineSimilarity instance = new CosineSimilarity(1);
        
        double result = instance.similarity("ABCD", "123");
        assertEquals(0.0, result, 0.0);
        
        result = instance.similarity("ABCD", "ABEF");
        assertEquals(0.5, result, 0.0);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        
        instance = new CosineSimilarity(3);
        result = instance.similarity("AB", "ABCE");
        assertEquals(0.0, result, 0.00001);
        
        NullTests.testSimilarity(instance);
    }
	
    @Test
    public void testDistance() {
    	CosineSimilarity instance = new CosineSimilarity(2);
        double result = instance.distance("ABECD", "ABEF");
        assertEquals(0.422, result, 0.01);
        
        String s0 = "ABABABAB";
        String s1 = "ABCABCABCABC";
        String s2 = "POIULKJH";
        
        Assert.assertTrue(instance.distance(s0, s1) < instance.distance(s0, s2));
        
        assertEquals(0.0, instance.distance("ABCDF", "ABCDF"), 0.0);
        assertEquals(1.0, instance.distance("12", "34"), 0.0);
        
        NullTests.testDistance(instance);
    }
}
