package thy;
import java.util.List;

public class MathUtils {
	public static double mean(List<Double> m) {
	    double sum = 0;
	    for (int i = 0; i < m.size(); i++) {
	        sum += m.get(i);
	    }
	    return sum / m.size();
	}
}
