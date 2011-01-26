package enduro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import enduro.racedata.Time;
import enduro.racedata.RacerData;

public abstract class Sorter {

	protected RacerData racerData;
	protected Time startTime;
	protected Time finishTime;

	public Sorter() {
		racerData = new RacerData();
	}

	/**
	 * Reads a file with start times and populates the data structure..
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readStartFile(String fileName) throws Exception {
		ArrayList<String[]> startingTimes = readFile(fileName);
		for (int i = 0; i < startingTimes.size(); i++) {
			int startNbr = Integer.parseInt(startingTimes.get(i)[0]);
			racerData.addStartTime(startNbr, new Time(startingTimes.get(i)[1]));
		}
	}

	/**
	 * Reads a file with finish times and populates the data structure.
	 * 
	 * @param fileName
	 *            The name of the file.
	 */
	public void readFinishFile(String fileName) throws Exception {
		ArrayList<String[]> finishTimes = readFile(fileName);
		for (int i = 0; i < finishTimes.size(); i++) {
			int startNbr = Integer.parseInt(finishTimes.get(i)[0]);
			racerData.addFinishTime(startNbr, new Time(finishTimes.get(i)[1]));
		}
	}

	/**
	 * Reads a file with a start number and a time and returns and ArrayList
	 * containing String arrays containing the start number and the time.
	 * 
	 * @param fileName
	 *            The name of the file.
	 * @return ArrayList<String[]> containing start numbers and time.
	 * @throws Exception
	 */
	private ArrayList<String[]> readFile(String fileName) throws Exception {
		ArrayList<String[]> list = new ArrayList<String[]>();
	
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		while (in.ready()) {
			list.add(in.readLine().split("; "));
		}
		return list;
	}

	public void readNameFile(String fileName) throws Exception {
		ArrayList<String[]> names = readFile(fileName);
		for (int i = 0; i < names.size(); i++) {
			int startNbr = Integer.parseInt(names.get(i)[0]);
			racerData.addName(startNbr, names.get(i)[1]);
		}
	}
	
	public void createResultFile(String fileName) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				fileName)));
		out.println(titleRow());
		Iterator<Integer> itr = racerData.getRunnerIterator();
		while(itr.hasNext()){
			int i = itr.next();
			String name = racerData.getName(i);
			String start;
			StringBuilder trail = new StringBuilder();
			startTime = racerData.getStartTime(i).poll();
			finishTime = racerData.getFinishTime(i).poll();
			try {
				start = startTime.toString();
				if (racerData.getStartTime(i).size() > 0) {
					trail.append("; Flera starttider?");
					while (racerData.getStartTime(i).size() > 0) {
						trail.append(' ');
						trail.append(racerData.getStartTime(i).poll());
					}
				}
			} catch (NullPointerException e) {
				start = "Start?";
			}
			out.println(i + "; " + name + "; " + totalTime(trail, i) + "; " + start + "; "
					+ finishTime(trail, i) + trail.toString());
		}
		out.close();
	}
	
	protected abstract String titleRow();
	protected abstract String totalTime(StringBuilder trail, int i);
	protected abstract String finishTime(StringBuilder trail, int i);

}