package com.aspect.wop;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.aspect.wop.pojo.WorkOrder;
import com.aspect.wop.utils.WorkOrderUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class JerseyClientMain {
	
	private static final Logger LOGGER = Logger.getLogger(JerseyClientMain.class.getName());
	public static void main(String[] args) {
		
		JerseyClientMain wm = new JerseyClientMain();
		//wm.callHello1();
		
		long id = 6362129667659802038l;
     	try {
     		Handler fileHandler  = new FileHandler("./wo.formatter.log");
     		LOGGER.addHandler(fileHandler);
//     		for(int i=1;i<=10;i++){
//			   wm.addIDToQueue();
//     		}
	     	wm.getMeanWaitTime();
	     	//wm.getAndRemoveTopId();
	     	//wm.removeAnId(id);
	     	//wm.getIDPositionInQueue(id);
	     	//wm.getListOfIds();

		} catch (JsonParseException  jx) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "Exception occur", jx);
			//ex.printStackTrace();
		}  catch (JsonMappingException  jmx) {
             LOGGER.log(Level.SEVERE, "Exception occur", jmx);
			//ex.printStackTrace();
		
		}  catch (IOException iox) {
	        LOGGER.log(Level.SEVERE, "Exception occur", iox);
			//ex.printStackTrace();
		} catch (Exception ex) {
          LOGGER.log(Level.SEVERE, "Exception occur", ex);
		 //ex.printStackTrace();
	   }

	}
	
	
	//scenario 1
	public void addIDToQueue() throws JsonParseException, JsonMappingException, IOException{
		String scenario = "addIDToQueue";
		String mediaType="application/json";
		String method="POST";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","addIDToQueue");
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/rest/addIDToQueue?ID=");
		StringBuilder sb = new StringBuilder(end_point);
		long timeidadded = WorkOrderUtil.getCurrentTimeInMiliSecs();
		long ID= WorkOrderUtil.getRandLong(WorkOrderUtil.MIN_ID, WorkOrderUtil.MAX_ID);
		sb.append(ID);
		sb.append("&TIME=");
		sb.append(timeidadded);
		String url=sb.toString();
		makeServiceCall(mediaType, url, method, scenario);
		
		
	}
	
	//scenario 2
	public void getAndRemoveTopId() throws JsonParseException, JsonMappingException, IOException{
		String scenario = "getAndRemoveTopId";
		String mediaType="application/json";
		String method="GET";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","getAndRemoveTopId");
		StringBuilder sb = new StringBuilder(end_point);
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/workorder/getAndRemoveTopId");
		String url=sb.toString();
		makeServiceCall(mediaType, url, method, scenario);
		
	}
	
	//scenario 3
	public void getListOfIds() throws JsonParseException, JsonMappingException, IOException{
		String scenario = "getListOfIds";
		String mediaType="application/json";
		String method="GET";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","getListOfIds");
		StringBuilder sb = new StringBuilder(end_point);
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/rest/getListOfIds");
		String url=sb.toString();

		makeServiceCall(mediaType, url, method, scenario);
		
		
	}
	
	//scenario 4
	public void removeAnId(long ID) throws JsonParseException, JsonMappingException, IOException{
		String scenario = "removeAnId";
		String mediaType="text/plain";
		String method="DELETE";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","removeAnId");
		StringBuilder sb = new StringBuilder(end_point);
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/rest/removeAnId?ID=");
		sb.append(ID);
		String url=sb.toString();
		makeServiceCall(mediaType, url, method, scenario);
		
	}
	
	//scenario 5
	public void getIDPositionInQueue(long ID) throws JsonParseException, JsonMappingException, IOException{
		String scenario = "getIDPositionInQueue";
		String mediaType="text/plain";
		String method="GET";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","getIDPositionInQueue");
		StringBuilder sb = new StringBuilder(end_point);
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/workorder/getIDPositionInQueue?ID=");
		sb.append(ID);
		String url=sb.toString();
		makeServiceCall(mediaType, url, method, scenario);
		
	}
	
	//scenario 6
	public void getMeanWaitTime() throws JsonParseException, JsonMappingException, IOException{
		String scenario = "getMeanWaitTime";
		String mediaType="text/plain";
		String method="GET";
		String end_point=WorkOrderUtil.readPropertyFileFromClasspath("endpoint.properties","getMeanWaitTime");
		StringBuilder sb = new StringBuilder(end_point);
		//StringBuilder sb = new StringBuilder("http://localhost:8080/workorderpriority/workorder/getMeanWaitTime?TIME=");
		long timeidadded = WorkOrderUtil.getCurrentTimeInMiliSecs();
		sb.append(timeidadded);
		String url=sb.toString();
		makeServiceCall(mediaType, url, method, scenario);
		
	}
	
	/**
	 * obvious what this is doing
	 * @param mediaType
	 * @param url
	 * @param method
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	private void makeServiceCall(String mediaType, String url, String method, String scenario) throws JsonParseException, JsonMappingException, IOException{
		Client client = Client.create();
		ClientResponse response = null;
		WebResource webResource = client.resource(url);
		if(StringUtils.equals(method, "POST")){
			response = webResource.accept(mediaType)
	                .post(ClientResponse.class);
		}else if(StringUtils.equals(method, "GET")){
			response = webResource.accept(mediaType)
	                .get(ClientResponse.class);
		}else if(StringUtils.equals(method, "PUT")){
			response = webResource.accept(mediaType)
	                .put(ClientResponse.class);
		}else if(StringUtils.equals(method, "DELETE")){
			response = webResource.accept(mediaType)
	                .delete(ClientResponse.class);
		}
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		//System.out.println();
		//System.out.println();
		String output = response.getEntity(String.class);;
		if(StringUtils.isNotEmpty(output)){
			if(StringUtils.equals(mediaType, "application/json")&&!scenario.equals("addIDToQueue")){
				jsonPrettyPrint(output);
			}else{
				  
				  System.out.println(output);
			}
		}else System.out.println("No result");
		
	}
	

	
	private void jsonPrettyPrint(String jsonStr) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		WorkOrder[] wolist = mapper.readValue(jsonStr, WorkOrder[].class);
		System.out.println("-----------Start SORTED List--------------");
		for(WorkOrder wo:wolist){
		    System.out.println("ID: " + wo.getId());
		    System.out.println("Date: " + wo.getDate());
		    System.out.println("Time: " + wo.getTimeMilisec());
		    System.out.println("Rank: " + wo.getRank());
		    System.out.println("Class rank id: " + wo.getWoclassInt());
		    System.out.println("Class: " + wo.getWoclass());
		    System.out.println("________________________________________");
		    System.out.println();
		}
		System.out.println("-----------End SORTED List--------------");

	}

	

	//#####################################################################
	public void callHello1() throws JsonParseException, JsonMappingException, IOException{
		String method="GET";
		//String mediaType="application/json";
		String mediaType="text/plain";
		String url="http://localhost:8080/workorderpriority/rest/hello/Alric";
		makeServiceCall(mediaType, url, method,"");


	}
	
	
	public void callHello2() throws JsonParseException, JsonMappingException, IOException{
		String method="GET";
		String mediaType="application/json";
		String url="http://localhost:8080/workorderpriority/rest/hello2?param=Alric";
		makeServiceCall(mediaType, url, method,"");
//		Client client = Client.create();
//		WebResource webResource = client.resource(url);
//		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
//                .get(ClientResponse.class);
//		if (response.getStatus() != 200) {
//			   throw new RuntimeException("Failed : HTTP error code : "
//				+ response.getStatus());
//		}
//
//	   String output = response.getEntity(String.class);
//	   System.out.println(output);

	}

}


