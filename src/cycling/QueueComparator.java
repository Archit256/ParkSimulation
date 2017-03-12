package cycling;
import java.util.Comparator;

public class QueueComparator implements Comparator<Events>{
	
    @Override
	    public int compare(Events x, Events y)
	    {

    		if(x == null || y == null )
    			return -999;
    		
	        if (x.time < y.time)
	        {
	            return -1;
	        }
	        if (x.time > y.time)
	        {
	            return 1;
	        }
	        return 0;
	    }
}

