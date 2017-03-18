package cycling;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Station {
	StationID stationID;
	StationID nextStationID;
	AtomicInteger bicyclesAvailable;
	AtomicInteger docksAvailable;
	AtomicInteger numberOfTourists;
	double distanceToNextDestination;
	ConcurrentLinkedQueue<Tourist> rentingABicycleQueue;
	ConcurrentLinkedQueue<Tourist> droppingABicycleQueue;

	static int maxDocks = 80;

	/*
	 * Station constructor
	 * 
	 * Initialize bicycle count, dock count, number of tourists and the queues
	 */
	Station() {
		this.bicyclesAvailable = new AtomicInteger(80);
		this.docksAvailable = new AtomicInteger(80);
		this.numberOfTourists = new AtomicInteger(0);
		this.rentingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
		this.droppingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
	}

	/*
	 * Method to update tourist arrival
	 */
	public void touristArrival(Tourist t, double time) {
		this.numberOfTourists.addAndGet(1);
		if (this.docksAvailable.get() == 0 || this.droppingABicycleQueue.size() > 0) {
			this.droppingABicycleQueue.add(t);
		} else {
			dropBicycle(t, time);
		}
	}

	/*
	 * Method to update tourist departure
	 */
	public void touristDeparture(Tourist t, double time) {
		if (this.bicyclesAvailable.get() == 0 || this.rentingABicycleQueue.size() > 0) {
			this.rentingABicycleQueue.add(t);
		} else {
			this.rentBicycle(t, time);
		}
	}

	/*
	 * Method to rent bicycle
	 */
	public void rentBicycle(Tourist t, double time) {
		t.hasRentedABicycle = true;
		this.bicycleRented(); // bicycle has been rented
	}

	/*
	 * Method to drop a bicycle
	 */
	public void dropBicycle(Tourist t, double time) {
		t.hasRentedABicycle = false;
		this.bicycleDropped(); // bicycle has been dropped
	}

	/*
	 * Method to update attributes of station if bicycle is rented
	 */
	public void bicycleRented() {
		this.docksAvailable.addAndGet(1);
		this.bicyclesAvailable.decrementAndGet();
		this.numberOfTourists.decrementAndGet();
	}

	/*
	 * Method to update attributes of station if bicycle is dropped
	 */
	public void bicycleDropped() {
		this.docksAvailable.decrementAndGet();
		this.bicyclesAvailable.addAndGet(1);
		this.numberOfTourists.incrementAndGet();
	}

}
