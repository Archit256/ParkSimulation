package thy;


import java.util.Random;

public class InputGenerator {
	private static int[] SEEDS = new int[]{4137096, 1392153, 5372198, 8332692, 5881198, 7690252, 1375543, 4800122, 4589425, 8066659,
											6028132, 9768298, 9768298, 2709045, 1704539, 8912680, 1048157, 4539050, 7922078, 3715090,
											9002215, 3220666, 1466796, 8909162, 5738412, 5632399, 9694794, 5273665, 6812176, 6040334};
	/*
	 * There is 30 pre-defined seeds.
	 * i is the index of SEEDS
	 */
	private static Random random;
	public static void initSeed(int i){
		random = new Random(SEEDS[i]);
	}
	
	private static final double MIN = 10.340;
	private static final double MODE = 19.617738;
	private static final double MAX = 29.890;
	/*
	 * To get visitors' speed which follows triangular distribution
	 * Unit is (km/min) (as already divided by 60)
	 */
	static public double generateSpeed() {
		double F = (MODE - MIN) / (MAX - MIN);
	    double rand = random.nextDouble();
	    if (rand <= F) {
	        return (MIN + Math.sqrt(rand * (MAX - MIN) * (MODE - MIN))) / 60;
	    } else {
	    	return (MAX - Math.sqrt((1 - rand) * (MAX - MIN) * (MAX - MODE))) / 60;
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
	static public double generateTimeSpent(StationID id) {
		switch(id){
		case A: 
			return normalDistribution(MEAN_A, STD_A);
		case B: 
			return normalDistribution(MEAN_B, STD_B);
		case C: 
			return normalDistribution(MEAN_C, STD_C);
		case D: 
			return normalDistribution(MEAN_D, STD_D);
		}
		return 0;
	}
	
	/*
	 *  To generate normal distribution
	 */
	static public double normalDistribution(double mean, double std) {
	    return mean + std * random.nextGaussian();
	}
	
	private static double INTERARRIVAL_A = 8.603771429;
	private static double INTERARRIVAL_B = 8.416833333;
	private static double INTERARRIVAL_C = 8.951420118;
	private static double INTERARRIVAL_D = 7.937604167;
	/*
	 * To get next arrival for a station
	 * Unit is (minute)
	 */
	static public double generateNextArrival(StationID id) {
		switch(id){
		case A: 
			return exponentialDistribution(INTERARRIVAL_A);
		case B: 
			return exponentialDistribution(INTERARRIVAL_B);
		case C: 
			return exponentialDistribution(INTERARRIVAL_C);
		case D: 
			return exponentialDistribution(INTERARRIVAL_D);
		}
		return 0;
	}
	/*
	 * To generate exponential distribution
	 */
	public static double exponentialDistribution(double mean) {
		return - mean * Math.log(1 - random.nextDouble());
	}
	
	static public void main(String[] args) {
		initSeed(0);
		for(int i = 0; i<5000; i++){
			System.out.println(generateTimeSpent(StationID.A));
		}
	}
}
