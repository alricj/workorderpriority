package com.aspect.wop.queue;

import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.aspect.wop.utils.WorkOrderUtil;
import com.aspect.wop.utils.WorkOrderUtil.CLASSESOFIDS;
import com.aspect.wop.utils.WorkOrderComparator;
import com.aspect.wop.utils.WorkOrderIDComparator;
import com.aspect.wop.pojo.WorkOrder;

public class Queue {
	private LinkedList<WorkOrder> list;
	//private List<WorkOrder> list;
	// Queue constructor
	public Queue()
	{
		// Create a new LinkedList, not an arrayList
		// because I am adding and removing IDs
		list = new LinkedList<WorkOrder>();
	}

	public boolean isEmpty()
	{
		return (list.size() == 0);
	}
    
	/**
	 * Append the item to the end of our linked list, to
	 * the back of the Queue
	 * @param item
	 */
	public void enqueue(WorkOrder item)
	{
		
		list.add(item);
	}
	
	public boolean isContain(WorkOrder item){
		boolean iscontain=false;
		if(list!=null&&!list.isEmpty())
			iscontain=list.contains(item);
		return iscontain;
	}
	
	public List<WorkOrder> getSortIdList() throws ParseException {
		if(list!=null&&!list.isEmpty()){
			list=assignRank();
			Collections.sort(list, new WorkOrderComparator());
		}
		return list;
	}
	
	/**
	 * This method determines the rank to be used as one of the sort criteria
	 * @return
	 * @throws ParseException
	 */
	private LinkedList<WorkOrder> assignRank() throws ParseException{
		long agedTime=0l;
		long rank=0l;
		for(WorkOrder wo: list){
			agedTime = WorkOrderUtil.getCurrentTimeInMiliSecs() - wo.getTimeMilisec();
			if(wo.getWoclassInt()==CLASSESOFIDS.VIP.getPriorityRank()){
				rank = WorkOrderUtil.getVIPRank(agedTime);
				wo.setRank(rank); 
			}
			else if(wo.getWoclassInt()==CLASSESOFIDS.PRIORITY.getPriorityRank()){
				rank = WorkOrderUtil.getPriorityRank(agedTime);
				wo.setRank(rank); 
			} else {
				rank = agedTime;
				wo.setRank(rank); 
			}
			
		}
		return list;
	}
	
	/**
	 * utility method to dump the queue
	 * @return
	 */
	public String dumpQueue(){
		String strDump="";
		if(!isEmpty()){
			for(WorkOrder wo: list){
				strDump+=wo.toString();
			}
		}

		return strDump;
	}
	
	public int getQueueSize(){
		return list.size();
	}
	
    /**
     * The item at the front of the queue is returned and 
	 *  deleted from the queue.
     * @return
     */
	public WorkOrder dequeue()
	{
		WorkOrder item = list.get(0);
		list.remove(0);
		return item;
	}
	
    /**
     * The item at the front of the queue is returned and 
	// deleted from the queue. Returns null if precondition
	// not met.
     * @return
     */
	public WorkOrder peek()
	{
		return list.get(1);
	}
	
	// scenario 4
	public boolean removeAgivenId(WorkOrder wo){	
		boolean isremove = false;
		if(isContain(wo)){
			if(list!=null&&!list.isEmpty()){
				WorkOrderIDComparator idComparator = new WorkOrderIDComparator();
				Collections.sort(list, idComparator);
				int idx = Collections.binarySearch(list, wo, idComparator);
				list.remove(idx);
				isremove = true;
			}
		}
		return isremove;
	}
	
	// scenario 5
	/**
	 * returns the positional index of the ID in the queue
	 * @param wo
	 * @return
	 */
	public int getIDPositionInQueue(WorkOrder wo){
	//Collections.	
		int idx =-1;
		if(isContain(wo)){
			WorkOrderIDComparator idComparator = new WorkOrderIDComparator();
			Collections.sort(list, idComparator);
			idx = Collections.binarySearch(list, wo, idComparator);
		}
		return idx;
	}
	
	// scenario 6
	/**
	 * calculates the mean wait time given the current time
	 * @param atime. divided by 1000 to return the number of seconds.
	 * @return
	 */
	public long getMeanWaitTime(long atime){
		long meanWaitTime=0;
		long sum = 0;
		for(WorkOrder wo: list){
			sum+=atime-wo.getTimeMilisec();
		}
		if(list!=null&&!list.isEmpty()){
		   meanWaitTime = sum/list.size();
		}
		return meanWaitTime/WorkOrderUtil.A_1000_MILISEC_IN_A_SEC;
	}
}

