package cycling;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Station {
	StationID stationID;
	AtomicInteger bicyclesAvailable;
	AtomicInteger docksAvailable;
	AtomicInteger numberOfTourists;
	double distanceToNextDestination;
	ConcurrentLinkedQueue<Tourist> rentingABicycleQueue;
	ConcurrentLinkedQueue<Tourist> droppingABicycleQueue;
	
	Station()
	{
		this.bicyclesAvailable = new AtomicInteger(80);
		this.docksAvailable= new AtomicInteger(80);
		this.numberOfTourists = new AtomicInteger(0);
		this.rentingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
		this.droppingABicycleQueue = new ConcurrentLinkedQueue<Tourist>();
	}
	
	
	public static void main(String[] args)
	{
		Station a = new Station();
		a.bicyclesAvailable.decrementAndGet();
		System.out.println("bicycles "+ a.bicyclesAvailable.get());
		
	}
}

