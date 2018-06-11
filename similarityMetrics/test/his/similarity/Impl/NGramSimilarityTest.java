package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;


public class NGramSimilarityTest {
	
	@Test
    public void testSimilarity() {
		NGramSimilarity instance = new NGramSimilarity(3);
        
        double result = instance.similarity("ABCD", "123");
        //System.out.println("NGram Similarity result of ABCD and 123 is " + result);
        assertEquals(0.0, result, 0.0);
        
        result = instance.similarity("ABCD", "AB");
        //System.out.println("NGram Similarity result of ABCD and AB is " + result);
        assertEquals(0.5, result, 0.0);
        
        result = instance.similarity("ABCDF", "ABCDF");
        //System.out.println("NGram Similarity result of ABCDF and ABCDF is " + result);
        assertEquals(1.0, result, 0.0);
        NullTests.testSimilarity(instance);
    }

    @Test
    public void testDistance() {
    	NGramSimilarity instance = new NGramSimilarity();
    	String s0 = "ABABABAB";
        String s1 = "ABCABCABCABC";
        String s2 = "POIULKJH";
        
        Assert.assertTrue(instance.distance(s0, s1) < instance.distance(s0, s2));
        
        assertEquals(0.0, instance.distance("ABCDF", "ABCDF"), 0.0);
        assertEquals(1.0, instance.distance("12", "34"), 0.0);
        
        NullTests.testDistance(instance);
    }
}
