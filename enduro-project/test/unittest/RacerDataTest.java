package unittest;

import java.util.PriorityQueue;
import enduro.racedata.Time;
import enduro.racedata.RacerData;
import org.junit.*;

import static org.junit.Assert.*;

public class RacerDataTest {

	private RacerData racerData;

	@Before
	public void setUp() {
		racerData = new RacerData();
		racerData.addStartTime(1, new Time(12, 00, 00));
		racerData.addFinishTime(1, new Time(12, 30, 00));
		racerData.addName(new String[]{"1","test1"});
		racerData.addName(new String[]{"2","test2"});
		racerData.addStartTime(2, new Time(12, 00, 00));
		racerData.addFinishTime(2, new Time(12, 20, 00));
		racerData.addFinishTime(2, new Time(13, 00, 00));
	}

	@Test
	public void testGetName() {
		assertEquals("test1", racerData.getName(1));
	}

	@Test
	public void testGetStartTime() {
		PriorityQueue<Time> times = racerData.getStartTime(1);
		assertTrue(times.size() == 1);
		boolean exists = false;
		for (Time t : times) {
			if (t.equals(new Time(12, 0, 0))) {
				exists = true;
			}
		}
		assertTrue(exists);
	}

	@Test
	public void testGetFinishTime() {
		PriorityQueue<Time> times = racerData.getFinishTime(1);
		assertTrue(times.size() == 1);
		boolean exists = false;
		for (Time t : times) {
			if (t.equals(new Time(12, 30, 0))) {
				exists = true;
			}
		}
		assertTrue(exists);
	}

	@Test
	public void testWrongRunnerNumber() {
		assertTrue(racerData.getFinishTime(-2) == null);
	}

	@Test
	public void testOneRunnerList() {
		assertEquals(new Integer(1), racerData.numberIterator().next());
	}

	@Test
	public void testGetTotalTime() {
		assertEquals(new Time("1.00.00"), racerData.getTotalTime(2));
	}

	@Test
	public void testGetNumberOfLaps() {
		assertEquals(2, racerData.getNumberOfLaps(2));
	}
}
