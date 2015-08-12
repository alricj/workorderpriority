package com.aspect.wop.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.math3.random.RandomDataGenerator;

import com.aspect.wop.pojo.WorkOrder;
import com.aspect.wop.queue.Queue;
/**
 * provides the helper methods for the application
 * @author Alric
 *
 */
public final class WorkOrderUtil  {

	private static final Logger LOGGER = Logger.getLogger(WorkOrderUtil.class.getName());
	
	public static String US_DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
	public static String DELETE_MSG_SUCCESS="The following Record is removed : ";
	public static String DELETE_MSG_FAILURE="The following Record is removed : ";
	public static long MIN_ID=1l;
	public static long MAX_ID=9223372036854775807l; // 64bytes
	public static long A_1000_MILISEC_IN_A_SEC=1000;
	private static String ENDPOINT_PROPERTIES_FILE = "endpoint.properties";
	
	public static Queue staticQUE=new Queue();
	public static WorkOrder WO = null;
	private static Map<String, String> endPointProp = null;
	
	private WorkOrderUtil(){}
	
	/**
	 * Here I have created an enum that assigns a number ranking
	 * to the classes of ID, with 1 being the higest priority
	 * 2 the next higest, etc, etc
	 *
	 */
	public static enum CLASSESOFIDS {
		NORMAL(4, "NORMAL"), PRIORITY(3, "PRIORITY"), MANAGEMENT_OVERRIDE(1, "MANAGEMENT_OVERRIDE"), VIP(2,"VIP");
		private  int priorityrank;
		private  String theclass;
		CLASSESOFIDS(int priorityrank, String theclass){
			this.priorityrank=priorityrank;
			this.theclass = theclass;
		}
		public  int getPriorityRank(){
			return this.priorityrank;
		}
		public  String getTheclass(){
			return this.theclass;
		}
		
	}
	
	/**
	 * generates a random number between max and min inclusive, this is used
	 * to generate the integer or rather the long IDs
	 * @param min
	 * @param max
	 * @return
	 */
	public static long getRandLong(final long min, final long max ) {
		RandomDataGenerator randomData = new RandomDataGenerator( );
	    long number = randomData.nextLong(min,max);
	    return number;
	}

	/**
	 * used to get the current time in milliseconds
	 * @return
	 */
	public static long getCurrentTimeInMiliSecs(){
		long timeInMiliSec = Calendar.getInstance().getTimeInMillis();
		return timeInMiliSec;
	}
	/**
	 * used to get the time in seconds if needed
	 * @return
	 */
	public static long getCurrentTimeInSecs(){
		Calendar now = Calendar.getInstance();
		long timeInMiliSec = now.get(Calendar.MILLISECOND);
		System.out.println("Current Millisecond is : " + timeInMiliSec);
        
		return timeInMiliSec/1000;
	}
	
	public static String getTodaysStringDate() {
	    Date date = Calendar.getInstance().getTime();
	    SimpleDateFormat sdf = new SimpleDateFormat(US_DATE_FORMAT);
	    return sdf.format(date);
	}
	
	/**
	 * this formula determines the priority IDs rank
	 * @param agedTime
	 * @return
	 */
	public static long getPriorityRank(final long agedTime){
		return (long) Math.max(3, agedTime*Math.log(agedTime));
	}
	
	/**
	 * this formula determines the VIP IDs rank
	 * @param agedTime
	 * @return
	 */
	public static  long getVIPRank(final long agedTime){
		return  (long) Math.max(3, (2*agedTime)*Math.log(agedTime));
	}
	
	
	/**
	 * determines the 4 id classes,
	 * IDs divisible by 3 are priority
	 * IDs divisible by 5 are vip
	 * IDs divisible by 3 and 5 are MANAGEMENT_OVERRIDE
	 * IDs that are not evenly divisible by both 3 or 5 are normal
	 * @param id
	 * @return
	 */
	public static String[] determineIntClass(final long id){
        String[] theclass=new String[2];
		int retClassOdId=0;
		if (id % 15 == 0) 
		{
		  retClassOdId=CLASSESOFIDS.MANAGEMENT_OVERRIDE.getPriorityRank();
		  theclass[0]=String.valueOf(retClassOdId);
		  theclass[1] = CLASSESOFIDS.MANAGEMENT_OVERRIDE.getTheclass();
		}
		else if (id % 3  == 0 )
		{
		   retClassOdId=CLASSESOFIDS.PRIORITY.getPriorityRank();
		   theclass[0]=String.valueOf(retClassOdId);
		   theclass[1] = CLASSESOFIDS.PRIORITY.getTheclass();
		}
		else if (id % 5== 0)
		{
		   retClassOdId=CLASSESOFIDS.VIP.getPriorityRank();
		   theclass[0]=String.valueOf(retClassOdId);
		   theclass[1] = CLASSESOFIDS.VIP.getTheclass();

		}else {
			retClassOdId=CLASSESOFIDS.NORMAL.getPriorityRank();
			theclass[0]=String.valueOf(retClassOdId);
			theclass[1] = CLASSESOFIDS.NORMAL.getTheclass();
		}
		return theclass;	
	}
	
	
    /**
     * read a given  properties file from the class classpath
     * if the file
     * @param propertyFileName
     * @throws IOException
     */
    public static String readPropertyFileFromClasspath(String propertyFileName, String endpointkey) throws IOException {
    	//if (StringUtils.isEmpty(propertyFileName)) propertyFileName = ENDPOINT_PROPERTIES_FILE;
    	Properties properties = new Properties();
    	String endpoint="";

    		if(endPointProp==null||endPointProp.isEmpty()){
	    	    properties.load(WorkOrderUtil.class.getResourceAsStream(propertyFileName));
		    	if(properties!=null&&!properties.isEmpty()){
		    		endPointProp=new HashMap<String, String>();
		    		
		    	}
		    	for(String key : properties.stringPropertyNames()) {
		    		  String value = properties.getProperty(key);
		    		  endPointProp.put(key, value);
		    		  //System.out.println(key + " => " + value);
		    	}
    		}
    		endpoint = endPointProp.get(endpointkey);
   
    	return endpoint;
    }
    
	
	/**
	 * 
	 * @param createDate
	 * @return
	 * @throws ParseException
	 */
	@Deprecated
	public static  long getAgedTime(String createDate) throws ParseException{
		String todaysDate = getTodaysStringDate();
		SimpleDateFormat format = new SimpleDateFormat(US_DATE_FORMAT);
		Date d1 = format.parse(createDate);
		Date d2 = format.parse(todaysDate);
		long duration = d2.getTime() - d1.getTime();
		return duration/1000;
		
	}
		


}

