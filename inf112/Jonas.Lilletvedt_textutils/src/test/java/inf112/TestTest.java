package inf112;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestTest {
	TextAligner aligner = new TextAligner() {

		public String center(String text, int width) {
			int extra = (width - text.length()) / 2;
			return " ".repeat(extra) + text + " ".repeat(extra);
		}

		public String flushRight(String text, int width) {
			// TODO Auto-generated method stub
			return null;
		}

		public String flushLeft(String text, int width) {
			// TODO Auto-generated method stub
			return null;
		}

		public String justify(String text, int width) {
			// TODO Auto-generated method stub	
			return null;
		}
	};

	@Test
	void failingTest() {
		// This test will always fail

		// fail("Not yet implemented");
	}

	@Test
	void testCenter() {
		// A typical test: assertEquals(expectedValue, actualValue, optionalMessage)
		// – assertEquals will complain unless expectedValue.equals(actualValue)
		// – assertNotEquals will complain if expectedValue.equals(actualValue)
		assertEquals("  A  ", aligner.center("A", 5), "A should be centred");
		assertEquals(" foo ", aligner.center("foo", 5));
	}

	@Test
	void testCenterBlanks() {
		// We can also check any boolean expression – though it's better to
		// use assertEquals when possible.
		assertTrue(aligner.center("", 10).isBlank());
		assertTrue(aligner.center("foo", 50).length() >= 50 - 1);
	}

	@Test
	void testSameDemo() {
		// assert(Not)Same tests whether the expectedValue and actualValue are the same
		// object
		// (Mostly for special cases; usually assertEquals would be the correct choice.)
		assertEquals("foo", new String("foo"));
		assertNotSame("foo", new String("foo"));
		assertSame("foo", new String("foo").intern());
	}

	@Test
	void testThrowsDemo() {
		// We can also test that an exception is thrown in particular cases.
		// (This doesn't always make sense, since we might sometimes want to
		// extend our library with implementations that *don't* throw exceptions –
		// for example, a negative width might have some special meaning, or we
		// could just say that aligner.center(null, w) just returns null rather
		// than throwing NullPointerException.
		assertThrows(IllegalArgumentException.class, () -> aligner.center("foo", -2));
		assertThrows(NullPointerException.class, () -> aligner.center(null, 10));

	}
}
