package his.similarity.Impl;

import his.similarity.metrics.Similarity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public final class NullTests {

	public static void testDistance(Similarity instance) {
		assertEquals(0.0, instance.distance("", ""), 0.1);
		assertEquals(1.0, instance.distance("", "foo"), 0.1);
		assertEquals(1.0, instance.distance("foo", ""), 0.1);

		try {
			instance.distance(null, null);
			fail("A NullPointerException was not thrown.");
		} catch (NullPointerException ignored) {
		}

		try {
			instance.distance(null, "");
			fail("A NullPointerException was not thrown.");
		} catch (NullPointerException ignored) {
		}

		try {
			instance.distance("", null);
			fail("A NullPointerException was not thrown.");
		} catch (NullPointerException ignored) {
		}
	}
	
	public static void testSimilarity(Similarity instance) {
        assertEquals(1.0, instance.similarity("", ""), 0.1);
        assertEquals(0.0, instance.similarity("", "foo"), 0.1);
        assertEquals(0.0, instance.similarity("foo", ""), 0.1);

        try {
            instance.similarity(null, null);
            fail("A NullPointerException was not thrown.");
        } catch (NullPointerException ignored) {
        }

        try {
            instance.similarity(null, "");
            fail("A NullPointerException was not thrown.");
        } catch (NullPointerException ignored) {
        }

        try {
            instance.similarity("", null);
            fail("A NullPointerException was not thrown.");
        } catch (NullPointerException ignored) {
        }
    }
}
