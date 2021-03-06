package unittest.racer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import enduro.racer.Racer;
import enduro.racer.Time;

public class RacerTest {

	@Test public void testBasicConstruct() {
		try {
			@SuppressWarnings("unused")
			Racer r = new Racer(new String("1; Anders Asson").split("; "));
		} catch(Exception E) {}
	}
	
	@Test public void getValuesTest() {
		Racer r = new Racer(new String("1; Anders Asson").split("; "));
		assertTrue(r.startNbr == 1);
	}
	
	@Test public void getInformation() {
		Racer r = new Racer(new String("1; Anders Asson").split("; "));
		assertTrue(r.racerInformation.get(0).compareTo("1")==0);
		assertTrue(r.racerInformation.get(1).compareTo("Anders Asson")==0);
	}
	
	@Test public void getExtendedInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		assertTrue(r.racerInformation.size() == 4);
		
		assertTrue(r.racerInformation.get(0).compareTo("1")==0);
		assertTrue(r.racerInformation.get(1).compareTo("Anders Asson")==0);
		assertTrue(r.racerInformation.get(2).compareTo("FMCK Astad")==0);
		assertTrue(r.racerInformation.get(3).compareTo("ATM")==0);
	}
	
	@Test public void addTimeInformation() {
		try {
			Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
			r.addFinishTime(new Time("00.00.12"), 1);
			assertTrue(r.startTimes.get(1).size()==0);
			assertTrue(r.finishTimes.get(1).size()==1);
			r.addStartTime(new Time("00.00.00"), 1);
			assertTrue(r.startTimes.get(1).size()==1);
			assertTrue(r.finishTimes.get(1).size()==1);
		} catch(Exception E) {}
		
	}
	
	@Test public void getTimeInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		r.addFinishTime(new Time("00.00.12"), 1);
		r.addStartTime(new Time("00.00.00"), 1);
		assertTrue(r.finishTimes.get(1).first().compareTo(new Time("00.00.12"))==0);
		assertTrue(r.startTimes.get(1).first().compareTo(new Time("00.00.00"))==0);
	}
	
	@Test public void getSortedTimeInformation() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		r.addFinishTime(new Time("00.12.12"), 1);
		r.addFinishTime(new Time("00.00.12"), 1);
		r.addFinishTime(new Time("00.06.12"), 1);
		assertTrue(r.finishTimes.get(1).size()==3);
		
		assertTrue(r.finishTimes.get(1).first().compareTo(new Time("00.00.12"))==0);
		assertTrue(r.finishTimes.get(1).last().compareTo(new Time("00.12.12"))==0);
	}
	
	@Test
	public void testGetStartNbr() {
		Racer r = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		assertEquals(1, r.getStartNbr());
	}
	
	@Test
	public void testCompareTo() {
		Racer r1 = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		Racer r2 = new Racer(new String("2; Bertil Bsson; FMCK Astad; ATM").split("; "));
		assertEquals(0, r1.compareTo(new Racer(new String[]{"1", "Anders Asson"})));
		assertEquals(1, r2.compareTo(new Racer(new String[]{"1", "Anders Asson"})));
	}
	
	@Test
	public void testEquals() {
		Racer r1 = new Racer(new String("1; Anders Asson; FMCK Astad; ATM").split("; "));
		Racer r2 = new Racer(new String("2; Bertil Bsson; FMCK Astad; ATM").split("; "));
		assertEquals(true, r1.equals(new Racer(new String[]{"1", "Anders Asson"})));
		assertEquals(false, r2.equals(new Racer(new String[]{"1", "Anders Asson"})));
	}
}
