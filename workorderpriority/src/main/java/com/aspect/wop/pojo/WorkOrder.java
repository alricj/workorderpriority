package com.aspect.wop.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@XmlRootElement
public class WorkOrder {
	

	long id;
	String date;
	long timeMilisec;
	String woclass="";
	int woclassInt;
	long rank;
	public int getWoclassInt() {
		return woclassInt;
	}

	public void setWoclassInt(int woclassInt) {
		this.woclassInt = woclassInt;
	}


	
	


	public WorkOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public WorkOrder(long id, String date, long timeMilisec, String woclass,
			int woclassInt, long rank) {
		super();
		this.id = id;
		this.date = date;
		this.timeMilisec = timeMilisec;
		this.woclass = woclass;
		this.woclassInt = woclassInt;
		this.rank = rank;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}
	
	public long getTimeMilisec() {
		return timeMilisec;
	}


	public void setTimeMilisec(long timeMilisec) {
		this.timeMilisec = timeMilisec;
	}



	public String getWoclass() {
		return woclass;
	}




	public void setWoclass(String woclass) {
		this.woclass = woclass;
	}



	public long getRank() {
		return rank;
	}



	public void setRank(long rank) {
		this.rank = rank;
	}
	
	@Override
	public boolean equals(Object obj){
		if(this==obj) return true;
		if(this==null) return false;
		
		WorkOrder other = (WorkOrder)obj;
		if(id !=other.id) return false;
		
		return true;
		
	}

    
	@Override
	public int hashCode(){
		final int prime = 31;
		int retVal = 1;
		retVal = (int) (prime * retVal + id);
		
		return retVal;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("ID: ");
		sb.append(id);
		sb.append(" Date :");
		sb.append(date);
		sb.append(" Time IN MiliSec: ");
		sb.append(timeMilisec);
		sb.append(" Class: ");
		sb.append(woclass);
		sb.append(" Rank: ");
		sb.append(rank);
		sb.append(" In Rank: ");
		sb.append(woclassInt);
		sb.append(System.getProperty("line.separator"));
		return sb.toString();
	}
	

	
	public String getWorkOrderJSON(WorkOrder wo){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput = gson.toJson(wo);
        return jsonOutput;
		
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		

	}

}
