package thy;


import java.util.ArrayList;
import java.util.List;

public class Tourist {
	int id;
	double speed;
	
	List<Double> waitingTime;
	double averageWaitingTime = 0;
	double startTimeInQueue;
	
	ArrayList<StationID> stationsVisited;
	
	Tourist(int touristId){
		this.id = touristId;
		this.stationsVisited = new ArrayList<StationID>();
		this.speed = InputGenerator.generateSpeed();
		this.waitingTime = new ArrayList<Double>();
	}
	
	public void startQueue(double time) {
		startTimeInQueue = time;
	}
	
	public void endQueue(double time) {
		waitingTime.add(time - startTimeInQueue);
	}

	public void calculateAverageWaitingTime() {
		if (waitingTime.size() > 0) {
			double sum = 0;
			for (int i = 0; i < waitingTime.size(); i++) {
				sum += waitingTime.get(i);
			}
			averageWaitingTime = sum / 6; // There is total 6 possible queue
		}
	}
}
