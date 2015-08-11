package com.aspect.wop;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class GetMeanWaitTimeTest {

	@Test(expected=Exception.class)
	public void testGetMeanWaitTime() {
		JerseyClientMain client = new JerseyClientMain();
		try {
			client.getMeanWaitTime();
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
