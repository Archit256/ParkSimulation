package cycling;

public class Events {
	EventTypes eventType;
	Tourist t;
	double time;
	StationID sID;
	
	public Events(EventTypes eventType, Tourist t, double time){
		this.eventType = eventType;
		this.t= t;
		this.time = time;
	}
	public Events(EventTypes eventType, Tourist t, double time, StationID sID){
		this.eventType = eventType;
		this.t= t;
		this.time = time;
		this.sID = sID;
	}
}
