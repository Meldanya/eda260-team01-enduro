package enduro;

import java.util.Iterator;
import java.util.PriorityQueue;

import enduro.racedata.Time;

public class LapseSorter extends Sorter {

	int lapses = 0;
	
	@Override
	protected String titleRow(){
		StringBuilder out = new StringBuilder();
		out.append("StartNr; Namn; #Varv; TotalTid; ");
		Iterator<Integer> itr = racerData.numberIterator();
		while(itr.hasNext()) {
			PriorityQueue<Time> times = racerData.getFinishTime(itr.next());
			
			if(times.size() > lapses)
				lapses = times.size();
		}
		for(int i = 1; i <= lapses; i++) {
			out.append("Varv");
			out.append(i);
			out.append("; ");
		}
		out.append("Start; ");
		for(int i = 1; i < lapses; i++) {
			out.append("Varvning");
			out.append(i);
			out.append("; ");
		}
		out.append("Mål");
		return out.toString();
	}
	
	@Override
	protected String totalTime(int i){
		PriorityQueue<Time> times = racerData.getFinishTime(i);
		for(Time t : times){
			finishTime = t;
		}
		int laps = times.size();
		StringBuilder out = new StringBuilder();
		out.append(laps);
		out.append("; ");
		
		out.append(startTime.getTotalTime(finishTime));
		out.append("; ");
		
		Time lastTime = startTime;
		for(Time t : times){
			out.append(lastTime.getTotalTime(t));
			lastTime = t;
			out.append("; ");
		}
		if(times.size() < lapses) {
			for(int j = times.size(); j < lapses;j++) {
				out.append("; ");
			}
		}
		out.delete(out.length()-2, out.length());
		return out.toString();
	}

	@Override
	protected Time getFinishTime(int i) throws NullPointerException {
		return racerData.getFinishTime(i).iterator().next();
	}
	
	@Override
	protected String finishTime(int i) {
		PriorityQueue<Time> times = racerData.getFinishTime(i);
		StringBuilder out = new StringBuilder();
		for(Time t : times){
			out.append(t.toString());
			out.append("; ");
		}
		out.delete(out.length()-2, out.length());
		
		if(times.size() < lapses) {
			out.append(";");
		}
		
		return out.toString();
	}
}
