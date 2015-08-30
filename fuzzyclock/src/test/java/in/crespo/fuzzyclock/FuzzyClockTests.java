package in.crespo.fuzzyclock;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Created by fernando on 31/07/15.
 */
public class FuzzyClockTests extends TestCase {

    public FuzzyClockTests(String testName)
    {
        super(testName);
    }
    
    public static Test suite()
    {
        return new TestSuite(FuzzyClockTests.class);
    }
    
	@Override
	protected void setUp() throws Exception {
		Method[] ms = FuzzyClock.class.getClass().getDeclaredMethods();
		for (Method m : ms) {
			if (m.isAnnotationPresent(VisibleForTest.class)) {
				m.setAccessible(true);
				System.out.print(m.invoke(FuzzyClock.class, 0));
			}
		}

		super.setUp();
	}
	
	public void testRetornaHoraPorExtenso() {
		assertEquals("meia noite", FuzzyClock.parseHourTest(0));
		assertEquals("meia noite", FuzzyClock.parseHourTest(24));
		assertEquals("uma", FuzzyClock.parseHourTest(1));
		assertEquals("uma", FuzzyClock.parseHourTest(13));
		// FIXME: Precisa verificar as outras horas
	}

	public void testRetorna_A_ParaHorasNoSingular() {
		List<Integer> list = Arrays.asList(0, 1, 13, 24);

		for (Integer i : list) {
			assertEquals("a", FuzzyClock.parseJunctionTest(i));
		}
	}

	public void testRetorna_As_ParaHorasNoPlural() {
		List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

		for (Integer i : list) {
			assertEquals("as", FuzzyClock.parseJunctionTest(i));
		}
	}

	public void testRetorna_O_ParaMeioDia() {
		assertEquals("o", FuzzyClock.parseJunctionTest(12));
	}

	public void testRetorna_E_Cinco_ParaMinutosDe3A7() {
		List<Integer> list = Arrays.asList(3, 4, 5, 6, 7);

		for (Integer i : list) {
			assertEquals("{0} e cinco", FuzzyClock.parseMinuteTest(i));
		}
	}
}
