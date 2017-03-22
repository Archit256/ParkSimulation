package thy;
import java.util.PriorityQueue;

public class Simulation {
	private static PriorityQueue<Event> eventQueue;
	private double currentTime;
	
	Simulation (){
		eventQueue = new PriorityQueue<Event>(5000);
	}
	
	public void run(double startTime, double endTime) {
		currentTime = startTime;
		// System.out.println("Station\tTourist ID\tEvent\t\tTime\tRentQ\tDropQ");
		while (eventQueue.size() > 0 && currentTime <= endTime) {
			Event event = eventQueue.remove();
			
			// display(event);
			
			currentTime = event.time;
			event.process(this);
		}
		displaySummary();
	}
	public void display(Event event) {
		System.out.printf("%s\t%s\t%s\t\t%s %s %s\n", event.station.id, event.tourist.id, event.type, event.time, event.station.getRentQueue(), event.station.getDropQueue());
	}
	public void displaySummary() {
		
	}
	public double getCurrentTime() {
		return currentTime;
	}
	public void addEvent(Event e) {
		eventQueue.add(e);
	}
}
