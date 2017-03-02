package cycling;

public class ThemePark {
	Station a, b, c, d;
	private Object timeSync; //used to threadsafe time variable
	float timeSpent = 5; //assuming tourists spend 5 minutes at an attraction
	
	
	ThemePark(){
		timeSync = new Object();
		
		a = new Station();
		a.stationID = StationID.a;
		a.distanceToNextDestination = 3.0;
		
		b = new Station();
		b.stationID = StationID.b;
		b.distanceToNextDestination = 3.5;
		
		c = new Station();
		c.stationID = StationID.c;
		c.distanceToNextDestination = 4.0;
		
		d = new Station();
		d.stationID = StationID.d;
		d.distanceToNextDestination = 2.0;
	}

	public void move(Station x, Station y, Tourist t){
		
	}
	
	public void touristArrival(Tourist t){
		
	}
 
	public void rentABicycle(Station x, Tourist t){
		
	}
	
	public void leaveABicycle(Station x, Tourist t){
		
	}
	
	public static void main (String [] args){
		
	}
	
	
}
