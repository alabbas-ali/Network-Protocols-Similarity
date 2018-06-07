package his.similarity.Impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NGramSimilarityTest {
	
	@Test
    public void testSimilarity() {
		NGramSimilarity instance = new NGramSimilarity(1);
        
        double result = instance.similarity("ABCD", "123");
        assertEquals(0.0, result, 0.0);
        
        result = instance.similarity("ABCD", "AB");
        assertEquals(0.5, result, 0.0);
        
        result = instance.similarity("ABCDF", "ABCDF");
        assertEquals(1.0, result, 0.0);
        NullTests.testSimilarity(instance);
    }

    @Test
    public void testDistance() {
    	NGramSimilarity instance = new NGramSimilarity(2);
        double result = instance.distance("ABCDE", "ABCDF");
        assertEquals(0.4, result, 0.0);
        NullTests.testDistance(instance);
    }
}
