package cycling;

import java.util.Random;

public class DistributionGenerator {
	
	private static final double MIN = 10.340;
	private static final double MODE = 19.617738;
	private static final double MAX = 29.890;
	/*
	 * To get visitors' speed which follows triangular distribution
	 * Unit is (km/h)
	 */
	static public double generateSpeed() {
		double F = (MODE - MIN) / (MAX - MIN);
	    double rand = Math.random();
	    if (rand <= F) {
	        return MIN + Math.sqrt(rand * (MAX - MIN) * (MODE - MIN));
	    } else {
	    	return MAX - Math.sqrt((1 - rand) * (MAX - MIN) * (MAX - MODE));
	    }
	}
	
	private static final double MEAN_A = 29.90766667;
	private static final double STD_A = 5.11718515;
	
	private static final double MEAN_B = 29.982;
	private static final double STD_B = 5.293361452;
	
	private static final double MEAN_C = 30.03;
	private static final double STD_C = 4.967527273;
	
	private static final double MEAN_D = 30.03283333;
	private static final double STD_D = 5.01579861;
	/*
	 * To get time spent in each station
	 * Unit is (minute)
	 */
	static public double generateTimeSpentAtStation(StationID id) {
		switch(id){
		case a: 
			return normalDistribution(MEAN_A, STD_A);
		case b: 
			return normalDistribution(MEAN_B, STD_B);
		case c: 
			return normalDistribution(MEAN_C, STD_C);
		case d: 
			return normalDistribution(MEAN_D, STD_D);
		}
		return 0;
	}
	
	/*
	 *  To generate normal distribution
	 */
	static public double normalDistribution(double mean, double std) {
		Random rand = new Random();
	    return mean + std * rand.nextGaussian();
	}
	
	private static double INTERARRIVAL_A = 8.603771429;
	private static double INTERARRIVAL_B = 8.416833333;
	private static double INTERARRIVAL_C = 8.951420118;
	private static double INTERARRIVAL_D = 7.937604167;
	/*
	 * To get next arrival for a station
	 * Unit is (minute)
	 */
	static public double generateNextArrivalAtStation(StationID id) {
		switch(id){
		case a: 
			return exponentialDistribution(INTERARRIVAL_A);
		case b: 
			return exponentialDistribution(INTERARRIVAL_B);
		case c: 
			return exponentialDistribution(INTERARRIVAL_C);
		case d: 
			return exponentialDistribution(INTERARRIVAL_D);
		}
		return 0;
	}
	/*
	 * To generate exponential distribution
	 */
	public static double exponentialDistribution(double mean) {
		return - mean * Math.log(1 - Math.random());
	}
	
	static public void main(String[] args) {
		for(int i = 0; i<5000; i++){
			System.out.println(generateNextArrivalAtStation(StationID.a));
		}
	}
}
