package thy;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Station {
	ThemePark park;
	
	StationID id;
	Station nextStation;
	double distanceToNextDestination;
	int BICYCLES_MAX, DOCKS_MAX;
	AtomicInteger docksAvailable; // Init: DOCKS_MAX - BICYCLES_MAX (hence initially DOCKS_MAX > BICYCLES_MAX)
								  // docksAvailable ranging from 0 - DOCKS_MAX
								  // docksAvailable == DOCKS_MAX means there is no bicycle to rent
								  // docksAvailable == 0 means there is no space to drop the bicycle

	int numberOfTourists;
	ConcurrentLinkedQueue<Tourist> rentQueue;
	ConcurrentLinkedQueue<Tourist> dropQueue;

	Station(ThemePark park, StationID id, double distance, int noBicycles, int noDocks) {
		this.park = park;
		
		this.id = id;
		
		this.BICYCLES_MAX = noBicycles;
		this.DOCKS_MAX = noDocks;
		this.docksAvailable = new AtomicInteger(DOCKS_MAX - BICYCLES_MAX);
		
		this.distanceToNextDestination = distance;
		this.numberOfTourists = 0; // Total no of tourists arrives
		this.rentQueue = new ConcurrentLinkedQueue<Tourist>();
		this.dropQueue = new ConcurrentLinkedQueue<Tourist>();
	}

	synchronized boolean requestBicycle(Tourist tourist, double currentTime) {
		// To keep track no of tourist at each station
		this.numberOfTourists++;
		
		if( docksAvailable.get() < DOCKS_MAX && rentQueue.size() == 0){
			// Rent a bicycle
			rentBicycle();
			
			// One dock will be available
			// So other tourist can drop the bicycle and ARRIVE at the current station
			if(dropQueue.size() > 0) {
				Tourist other = dropQueue.remove();
				other.endQueue(currentTime); // Conclude the waiting time
				dropBicycle();
				
				// Enter the attraction immediately
				park.addEvent(new Event(this, other, EventType.ENTER, currentTime));
			}
			return true;
		} else {
			// Add to rent queue
			rentQueue.add(tourist);
			
			// Update start time to queue
			tourist.startQueue(currentTime);
			return false;
		}
	}
	
	synchronized boolean requestDock(Tourist tourist, double currentTime) {
		if( docksAvailable.get() > 0 && dropQueue.size() == 0){
			// Drop the bicycle
			dropBicycle();
			
			// One bicycle will be available
			// So other tourist can rent the bicycle, ride and DROP at the next station
			if(rentQueue.size() > 0) {
				Tourist other = rentQueue.remove();
				other.endQueue(currentTime); // Conclude the waiting time
				rentBicycle();
				
				// Ride
				double travelTime = this.distanceToNextDestination / tourist.speed;
				// DROP
				park.addEvent(new Event(this.nextStation, other, EventType.DROP, currentTime + travelTime));
			}
			return true;
		} else {
			// Add to drop queue
			dropQueue.add(tourist);
			
			// Update start time to queue
			tourist.startQueue(currentTime);
			return false;
		}
	}
	private void rentBicycle() {
		this.docksAvailable.incrementAndGet();
		
		// No. of docks at each station cannot over the DOCKS_MAX
		if(docksAvailable.get() < 0 || docksAvailable.get() > DOCKS_MAX) {
			System.out.println("ERROR docksAvailable: " + docksAvailable);
		}
		// Debug
		if(id == StationID.A) System.out.println("Station A docksAvailable: " + docksAvailable);
	}

	private void dropBicycle() {
		this.docksAvailable.decrementAndGet();
		
		// No. of docks at each station cannot over the DOCKS_MAX
		if(docksAvailable.get() < 0 || docksAvailable.get() > DOCKS_MAX) {
			System.out.println("ERROR docksAvailable: " + docksAvailable);
		}
		// Debug
		if(id == StationID.A) System.out.println("Station A docksAvailable: " + docksAvailable);
	}
	
	public String getRentQueue() {
		Iterator<Tourist> iter = rentQueue.iterator();
		String q = "[";
		while(iter.hasNext()) {
			q += iter.next().id + ",";
		}
		return q + " ] ";
	}
	
	public String getDropQueue() {
		Iterator<Tourist> iter = dropQueue.iterator();
		String q = "[";
		while(iter.hasNext()) {
			q += iter.next().id + ",";
		}
		return q + " ] ";
	}

}
