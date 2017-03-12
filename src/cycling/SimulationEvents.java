package cycling;

import java.util.PriorityQueue;

public class SimulationEvents {
	public static PriorityQueue<Events> eventQueue;
	
	SimulationEvents(){
		QueueComparator comparator = new QueueComparator();
		eventQueue = new PriorityQueue<Events>(100,comparator);
	}
	
	

			
}

