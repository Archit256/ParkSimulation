package cycling;

public class ThemePark {
	Station a, b, c, d;
	private Object timeSync; //used to threadsafe time variable
	private Object eventQueueObject;
	float timeSpent = 30; //assuming tourists spend 5 minutes at an attraction
	
	
	
	ThemePark(){
		timeSync = new Object();
		eventQueueObject = new Object();
		
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

	public void move(Station x, Station y, Tourist t, double time){
	//	Events newEvent = new Events(EventTypes.touristDeparture,t,time,x);
		
		
	}
	
	/*
	 * When a new tourist arrives - station s is where they arrive.
	 */
	public void touristArrival(Tourist t, Station s, double time){
		if(t.hasRentedABicycle)
		{
			s.touristArrival(t, time); // arrival process to leave a bike kicked off
		}
		
		if(!t.stationsVisited.contains(s.stationID))
			{
				t.stationsVisited.add(s.stationID);
				time = time+30; //assuming spending 30 (mins) at the attraction
				Events newEvent = new Events(EventTypes.touristDeparture,t,time,s.stationID);
					synchronized(eventQueueObject)
					{
						SimulationEvents.eventQueue.add(newEvent);
					}
			}
	}
	
	public void touristDeparture(Tourist t, Station s, double time){
		if(!t.stationsVisited.contains(s.nextStationID)) // Still new stations to visit
		{
			if(!t.hasRentedABicycle){
				s.touristDeparture(t, time); // rent a bicycle
			}
			
		}
		
	}
 
	public void rentABicycle(Station x, Tourist t, double time){
		
	}
	
	public void leaveABicycle(Station x, Tourist t, double time){
		
	}
	
	public static void main (String [] args){
		/*
		Tourist t = new Tourist()
		Events newEvent = new Events(EventTypes.touristDeparture,t,time,s.stationID);
		Events newEvent = new Events(EventTypes.touristDeparture,t,time,s.stationID);
		Events newEvent = new Events(EventTypes.touristDeparture,t,time,s.stationID);*/
	}
	
	
}
