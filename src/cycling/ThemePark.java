/*
 * The main class of the simulation project. All the methods are called here.
 * 
 * Theme park contains the stations, an event queue and a constructor.
 * */

package cycling;

public class ThemePark {
	Station a, b, c, d;
	private Object timeSync; // used to threadsafe time variable
	private Object eventQueueObject;
	private SimulationEvents sims;
	float timeSpent = 30; // assuming tourists spend 5 minutes at an attraction

	/*
	 * Theme park constructor to initialize stations, distances and next
	 * stations
	 */
	ThemePark() {
		timeSync = new Object();
		eventQueueObject = new Object();
		sims = new SimulationEvents();

		a = new Station();
		a.stationID = StationID.a;
		a.nextStationID = StationID.b;
		a.distanceToNextDestination = 3.0;

		b = new Station();
		b.stationID = StationID.b;
		b.nextStationID = StationID.c;
		b.distanceToNextDestination = 3.5;

		c = new Station();
		c.stationID = StationID.c;
		c.nextStationID = StationID.d;
		c.distanceToNextDestination = 4.0;

		d = new Station();
		d.stationID = StationID.d;
		d.nextStationID = StationID.a;
		d.distanceToNextDestination = 2.0;
	}

	/*
	 * When a new tourist departs from Station x and goes to station y
	 */
	public void touristDeparture(Station x, Station y, Tourist t, double time) {
		System.out.println("Tourist has left");
		if (!t.stationsVisited.contains(y.stationID)) {
			rentABicycle(x, t, time);
		} else {
			Events newEvent = new Events(EventTypes.touristDeparture, t, time, x.nextStationID);
			synchronized (eventQueueObject) {
				SimulationEvents.eventQueue.add(newEvent);
			}
		}

	}

	/*
	 * When a new tourist arrives - station s is where they arrive.
	 */
	public void touristArrival(Tourist t, Station s, double time) {
		System.out.println("Tourist has arrived...");
		if (t.hasRentedABicycle) {
			leaveABicycle(s, t, time);
		}

		if (!t.stationsVisited.contains(s.stationID)) {
			t.stationsVisited.add(s.stationID);
			time = time + 30; // assuming spending 30 (mins) at the attraction
			if (!t.stationsVisited.contains(s.nextStationID)) {
				Events newEvent = new Events(EventTypes.touristArrival, t, time, s.nextStationID);
				System.out.println("New Event");
				System.out.println(newEvent);
				synchronized (eventQueueObject) {
					SimulationEvents.eventQueue.add(newEvent);
				}
			}
		}

	}

	/*
	 * Method called to rent a bicycle
	 * 
	 * 1) The tourist arrives at a station 2) Checks if bicycle is available 3)
	 * If available, rents it and rides to the next station 4) Else, gets added
	 * to FIFO queue and waits until a bicycle is available
	 * 
	 */
	public void rentABicycle(Station x, Tourist t, double time) {
		x.rentBicycle(t, time);
	}

	/*
	 * Method called to return a bicycle
	 * 
	 * 1) The tourist arrives at a station 2) Checks if dock is available 3) If
	 * available, leaves bicycle and explores the attraction 4) Else, gets added
	 * to FIFO queue and waits until a dock is available
	 * 
	 */
	public void leaveABicycle(Station x, Tourist t, double time) {
		x.dropBicycle(t, time);
	}

	public static void main(String[] args) {
		ThemePark tp = new ThemePark();// Theme Park created
		Tourist visitor = new Tourist();
		double time = 0;
		tp.touristArrival(visitor, tp.a, time);
		System.out.println(time);
		tp.touristDeparture(tp.a, tp.b, visitor, time);
	}

}
