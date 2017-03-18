/* 
 * SimulationEvents class keeps track of the events that occur in the entire simulation.
 * Its a priority queue.
 * */

package cycling;

import java.util.PriorityQueue;

public class SimulationEvents {
	public static PriorityQueue<Events> eventQueue;

	SimulationEvents() {
		QueueComparator comparator = new QueueComparator();
		eventQueue = new PriorityQueue<Events>(100, comparator);
	}

}
