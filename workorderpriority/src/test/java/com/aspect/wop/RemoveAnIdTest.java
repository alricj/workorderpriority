package com.aspect.wop;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.BeforeClass;
import org.junit.Test;

public class RemoveAnIdTest {
	long id =273830789187502477l;
	@Test
	public void testRemoveAnId() {
		JerseyClientMain client = new JerseyClientMain();

		assertTrue("The value of the ID should be a long greater than 0, taken from the previous IDs entered", id > 0);
		try {
			client.removeAnId(id);
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
