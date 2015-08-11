package com.aspect.wop;


import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

import com.aspect.wop.JerseyClientMain;

public class GetAndRemoveTopIdTest {

	@Test
	public void testGetAndRemoveTopId() {
		JerseyClientMain client = new JerseyClientMain();
		try {
			client.getAndRemoveTopId();;
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
