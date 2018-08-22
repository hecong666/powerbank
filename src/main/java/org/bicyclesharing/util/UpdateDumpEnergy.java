package org.bicyclesharing.util;

import java.util.Date;

public class UpdateDumpEnergy {
	public static double update(Date startDate,Date lastDate){
		double i = lastDate.getTime() - startDate.getTime();
		double temp = 1000 * 3600;
		double m = i / temp;
	return m;
		
	}
	
}	
