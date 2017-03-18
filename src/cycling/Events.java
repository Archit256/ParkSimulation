package cycling;

public class Events {
	EventTypes eventType;
	Tourist t;
	double time;
	StationID sID;

	public Events(EventTypes eventType, Tourist t, double time, StationID sID) {
		System.out.println(eventType);
		System.out.println(t);
		System.out.println(time);
		System.out.println(sID);
		this.eventType = eventType;
		this.t = t;
		this.time = time;
		this.sID = sID;
	}
}
