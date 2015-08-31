package in.crespo.fuzzyclock;

import java.lang.reflect.InvocationTargetException;
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
    
	protected Method getMethod(String methodName) {
		Method[] ms = FuzzyClock.class.getDeclaredMethods();
		for (Method m : ms) {
			
			if (m.getName().equals(methodName)) {
				m.setAccessible(true);
				return m;
			}
		}
		return null;
	}
	
	public void testRetornaHoraPorExtenso() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = getMethod("parseHour");

		assertEquals("meia noite", m.invoke(FuzzyClock.class, 0));
		assertEquals("meia noite", m.invoke(FuzzyClock.class, 24));
		assertEquals("uma", m.invoke(FuzzyClock.class, 1));
		assertEquals("uma", m.invoke(FuzzyClock.class, 13));
		// FIXME: Precisa verificar as outras horas
	}

	public void testRetorna_A_ParaHorasNoSingular() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = getMethod("parseJunction");

		List<Integer> list = Arrays.asList(0, 1, 13, 24);

		for (Integer i : list) {
			assertEquals("a", m.invoke(FuzzyClock.class, i));
		}
	}

	public void testRetorna_As_ParaHorasNoPlural() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = getMethod("parseJunction");

		List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);

		for (Integer i : list) {
			assertEquals("as", m.invoke(FuzzyClock.class, i));
		}
	}

	public void testRetorna_O_ParaMeioDia() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = getMethod("parseJunction");

		assertEquals("o", m.invoke(FuzzyClock.class, 12));
	}

	public void testRetorna_E_Cinco_ParaMinutosDe3A7() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method m = getMethod("parseMinute");

		List<Integer> list = Arrays.asList(3, 4, 5, 6, 7);

		for (Integer i : list) {
			assertEquals("{0} e cinco", m.invoke(FuzzyClock.class, i));
		}
	}
}
