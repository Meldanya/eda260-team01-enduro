package enduro.racedata;

/**
 * Utility class for representing time.
 */
public class Time implements Comparable<Time> {

	private int hour;
	private int min;
	private int sec;

	/**
	 * Creates a new Time object.
	 * 
	 * @param hour
	 *            The hour of a day. Should be in the interval 0-23.
	 * @param min
	 *            The minute of an hour. Should be in the interval 0-59.
	 * @param sec
	 *            The second of a minute. Should be in the interval 0-59.
	 */
	public Time(int hour, int min, int sec) {
		this.hour = hour;
		this.min = min;
		this.sec = sec;
	}

	/**
	 * Creates a new Time object from a String.
	 * 
	 * @param s
	 *            A string on the format hh.mm.ss
	 */

	public Time(String s) {
		String[] times = s.split("\\.");
		this.hour = Integer.parseInt(times[0]);
		this.min = Integer.parseInt(times[1]);
		this.sec = Integer.parseInt(times[2]);
	}

	/**
	 * Returns a string representation of this Time object.
	 * 
	 * @return A string on the format 'hh.mm.ss'.
	 */
	public String toString() {
		return String.format("%02d.%02d.%02d", hour, min, sec);
	}

	/**
	 * Generates the difference between this time and a later one.
	 * 
	 * @param t
	 *            a time > this
	 * @return Difference between times
	 */
	public Time getTotalTime(Time t) {
		long startSecs = this.sec + (this.min * 60) + (this.hour * 3600);
		long endSecs = t.sec + (t.min * 60) + (t.hour * 3600);
		long result = endSecs - startSecs;
		int hours = (int) (result / 3600);
		result %= 3600;
		int minutes = (int) (result / 60);
		result %= 60;
		return new Time(hours, minutes, (int) result);
	}

	public int compareTo(Time t) {
		long startSecs = this.sec + (this.min * 60) + (this.hour * 3600);
		long endSecs = t.sec + (t.min * 60) + (t.hour * 3600);
		return new Long(startSecs).compareTo(new Long(endSecs));
	}

	public boolean equals(Object o) {
		if(o instanceof Time){
		return compareTo((Time)o)==0;
		} else return false;
	}
}
