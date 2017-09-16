package algorithms.greedy;

import java.util.Arrays;

/**
 * Algorithm to solve the scheduling problem. Given
 * different events with start time and end time, figure
 * out a strategy to schedule as many events as possible
 * 
 * @author An Nguyen
 *
 */
public class Scheduling {
	
	/**
	 * Determine the amount of events that can be scheduled
	 * given the events' start time and end time. This algorithm uses
	 * the next available event that ends as early as possible
	 * 
	 * @param start_time the starting time of the events
	 * @param end_time the ending times of the events
	 * @return the number of events that can be scheduled
	 */
	public int schedule(int[] start_time, int[] end_time) {
		int N = start_time.length;
		
		class Event implements Comparable<Event> {
			private int start, end;
			
			public Event(int start, int end) {
				this.start = start;
				this.end = end;
			}

			@Override
			public int compareTo(Event that) {
				if (this.end - that.end != 0)
					return this.end - that.end;
				return this.start - that.start;
			}
			
		}
		
		Event[] events = new Event[N];
		
		for (int i = 0; i < N; i++)
			events[i] = new Event(start_time[i], end_time[i]);
		
		Arrays.sort(events);
	
		
		int num = 0;
		int time = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (events[i].start > time) {
				time = events[i].end;
				num++;
			}
		}
		
		return num;
		
	}
	
}
