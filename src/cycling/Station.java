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
	
	Station()
	{
		this.bicyclesAvailable = new AtomicInteger(80);
		this.docksAvailable= new AtomicInteger(80);
		this.numberOfTourists = new AtomicInteger(0);
		this.rentingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
		this.droppingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
	}
	
	public void touristArrival(Tourist t, double time){
		this.numberOfTourists.addAndGet(1);
		if (this.docksAvailable.get()==0 || this.droppingABicycleQueue.size()>0)
		{
			this.droppingABicycleQueue.add(t);
		}
		else
		{
			dropBicycle(t,time);
		}
	}
	
	public void touristDeparture(Tourist t, double time){
		if (this.bicyclesAvailable.get()==0 || this.rentingABicycleQueue.size()>0)
		{
			this.rentingABicycleQueue.add(t);
		}
		else
		{
			this.rentBicycle(t, time);
		}
	}
	
	
	public void bicycleRented(){
		this.docksAvailable.addAndGet(1);
		this.bicyclesAvailable.decrementAndGet();
		this.numberOfTourists.decrementAndGet();
	}
	
	public void bicycleDropped(){
		this.docksAvailable.decrementAndGet();
		this.bicyclesAvailable.addAndGet(1);
	}
	
	public void rentBicycle(Tourist t, double time) {
		t.hasRentedABicycle = true;
		this.bicycleRented(); //bicycle has been rented
	}
	
	public void dropBicycle(Tourist t, double time) {
		t.hasRentedABicycle = false;
		this.bicycleDropped(); //bicycle has been dropped
	}
	

}

