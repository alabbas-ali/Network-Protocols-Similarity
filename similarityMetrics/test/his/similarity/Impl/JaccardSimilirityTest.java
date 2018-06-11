package his.similarity.Impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

public class JaccardSimilirityTest {
	
    @Test
    public void testSimilarity() {
        JaccardSimilarity instance = new JaccardSimilarity(2);
        
        double result = instance.similarity("ABCD", "123");
        assertEquals(0.0, result, 0.0);
        
        result = instance.similarity("ABCDE", "ABCDF");
        assertEquals(0.6, result, 0.0);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        NullTests.testSimilarity(instance);
    }

    @Test
    public void testDistance() {
        JaccardSimilarity instance = new JaccardSimilarity();
        double result = instance.distance("ABCDE", "ABCDF");
        assertEquals(0.4, result, 0.0);
        
        String s0 = "ABABABAB";
        String s1 = "ABCABCABCABC";
        String s2 = "POIULKJH";
        
        Assert.assertTrue(instance.distance(s0, s1) < instance.distance(s0, s2));
        
        assertEquals(0.0, instance.distance("ABCDF", "ABCDF"), 0.0);
        assertEquals(1.0, instance.distance("12", "34"), 0.0);
        
        NullTests.testDistance(instance);
    }
}
