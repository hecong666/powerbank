package org.bicyclesharing.util;

import java.util.Date;

public class UpdateDumpEnergy {
	public static int update(Date startDate,Date lastDate,int energy){
		double i = lastDate.getTime() - startDate.getTime();
		double temp = 1000 * 3600;
		double m = i / temp;
	
		int x = (int) Math.round(m * 10);
		System.out.println(m);
		return x;
	}
	
}	
