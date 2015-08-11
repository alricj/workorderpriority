package com.aspect.wop;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class AddIDToQueueTest {
	private static final Logger LOGGER = Logger.getLogger(AddIDToQueueTest.class.getName());
	@Test
	public void testAddIDToQueue() {
		JerseyClientMain client = new JerseyClientMain();
 		
		try {
	     for(int i=1;i<=10;i++){
			client.addIDToQueue();
	     }
		} catch (JsonParseException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			//e.printStackTrace();
		} catch (JsonMappingException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			//e.printStackTrace();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
			//e.printStackTrace();
		}
 		
	}

}
