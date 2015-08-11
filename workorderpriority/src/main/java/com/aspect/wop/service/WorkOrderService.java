package com.aspect.wop.service;

import java.text.ParseException;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.aspect.wop.pojo.WorkOrder;
import com.aspect.wop.utils.WorkOrderUtil;


@Path("/")
public class WorkOrderService {
	
	//scenario 1
	@POST
	@Path("addIDToQueue")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkOrder addIDToQueue(@QueryParam("ID") long id, @QueryParam("TIME") long thetime) {
		WorkOrder wo  = new WorkOrder();
		wo.setId(id);
		wo.setDate(WorkOrderUtil.getTodaysStringDate());
		wo.setTimeMilisec(thetime);
		String[] theclass=WorkOrderUtil.determineIntClass(id);
		int classInt = Integer.parseInt(theclass[0]);
		wo.setWoclassInt(classInt);
		wo.setWoclass(theclass[1]);
		WorkOrderUtil.staticQUE.enqueue(wo);
		return wo;
 
	}
	
	//scenario 2
	@GET
	@Path("getAndRemoveTopId")
	@Produces(MediaType.APPLICATION_JSON)
	public WorkOrder getAndRemoveTopId() throws ParseException{
		// made this call to trigger the sorting
		List<WorkOrder> workorderids=WorkOrderUtil.staticQUE.getSortIdList();
		WorkOrder wo  = WorkOrderUtil.staticQUE.dequeue();
		return wo;
	}
	
	//scenario 3
	@GET
	@Path("getListOfIds")
	@Produces(MediaType.APPLICATION_JSON)
	public List<WorkOrder> getListOfIds() throws ParseException{
		List<WorkOrder> workorderids=WorkOrderUtil.staticQUE.getSortIdList();
		return workorderids;
	}
	
	
	//scenario 4
	@DELETE
	@Path("removeAnId")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeAnId(@QueryParam("ID") long id){
		String output ="";
		WorkOrder wo = new WorkOrder();
		wo.setId(id);
		boolean isremove = WorkOrderUtil.staticQUE.removeAgivenId(wo);
		if(isremove){
			output = WorkOrderUtil.DELETE_MSG_SUCCESS + id;
			
		}else {
			output = WorkOrderUtil.DELETE_MSG_FAILURE + id;
		}
		return Response.status(200).entity(output).build();
	}
	
	// scenario 5
	@GET
	@Path("getIDPositionInQueue")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getIDPositionInQueue(@QueryParam("ID") long id){
		WorkOrder wo = new WorkOrder();
		wo.setId(id);
		int idx = WorkOrderUtil.staticQUE.getIDPositionInQueue(wo);
		String output = "ID position in Queue : " + idx;
		return Response.status(200).entity(output).build();
	}
	
	// scenario 6
	@GET
	@Path("getMeanWaitTime")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getMeanWaitTime(@QueryParam("TIME") long time){
		String output ="";
		// please do not hold this against me it just for testing
		long meanWaitTime = WorkOrderUtil.staticQUE.getMeanWaitTime(time);
		output = "The average wait time for given time : " + time+" is " + meanWaitTime;
		return Response.status(200).entity(output).build();
	}
	
	
	//the methods below are used to ping my service please ignore
	//##################################
	@GET
	@Path("hello/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Jersey say : " + msg+ " : "+WorkOrderUtil.WO.getId() ;		
		return Response.status(200).entity(output).build();
 
	}
	@GET
	@Path("hello2")
	//@Produces(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public WorkOrder getMsg2(@QueryParam("param") String msg) {
		//serversession.setAttribute("HELLOSESSION", "HELLOSESSION");
		WorkOrder wo = new WorkOrder();
        wo.setId(3);
        wo.setWoclass("PRIORITY");
        wo.setRank(5);
        WorkOrderUtil.WO = wo;
        return wo;
 
	}

}


