package com.aspect.wop;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.BeforeClass;
import org.junit.Test;

public class RemoveAnIdTest {
	static long id =0;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	     String ID = System.getProperty("ID");
	     id=Integer.valueOf("ID");
	     System.out.println(id);
	    // logger.info("Reading config file : " + fileName);
	}

	@Test
	public void testRemoveAnId() {
		JerseyClientMain client = new JerseyClientMain();
		long theID=id;
		assertTrue("The value of the ID should be a long greater than 0, taken from the previous IDs entered", id > 0);
		try {
			client.removeAnId(theID);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
