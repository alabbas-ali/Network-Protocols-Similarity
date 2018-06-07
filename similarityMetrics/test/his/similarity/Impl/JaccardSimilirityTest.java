package his.similarity.Impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JaccardSimilirityTest {
	
    @Test
    public void testSimilarity() {
        JaccardSimilarity instance = new JaccardSimilarity(1);
        
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
        JaccardSimilarity instance = new JaccardSimilarity(2);
        double result = instance.distance("ABCDE", "ABCDF");
        assertEquals(0.4, result, 0.0);
        NullTests.testDistance(instance);
    }
}
