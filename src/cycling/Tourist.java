package cycling;

import java.util.ArrayList;

public class Tourist {
	int touristID;
	StationID currentStationID;
	StationID initialStationID;
	boolean hasRentedABicycle;
	double bicycleSpeed;
	double waitingTime;
	
	ArrayList<StationID> stationsVisited;
	
	Tourist(){
		stationsVisited = new ArrayList<StationID>();
		bicycleSpeed = 20;
		hasRentedABicycle = false;
	}
}
