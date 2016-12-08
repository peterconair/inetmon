package net.thegreshams.firebase4j.app;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class Demo {

	private static String firebase_baseUrl = "https://netmon-be85d.firebaseio.com/";
	private static Firebase firebase;

	public static void main(String[] args)
			throws FirebaseException, JsonParseException, JsonMappingException, IOException, JacksonUtilityException {

		for (String s : args) {

			if (s == null || s.trim().isEmpty())
				continue;
			if (s.trim().split("=")[0].equals("baseUrl")) {
				firebase_baseUrl = s.trim().split("=")[1];
			}
		}
		if (firebase_baseUrl == null || firebase_baseUrl.trim().isEmpty()) {
			throw new IllegalArgumentException("Program-argument 'baseUrl' not found but required");
		}

		// create the firebase
		firebase = new Firebase(firebase_baseUrl);

		// "DELETE" (the fb4jDemo-root)
		// FirebaseResponse response = firebase.delete();
		FirebaseResponse response = firebase.get();

		// "PUT" (test-map into the fb4jDemo-root)
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("PUT-root", "Root Node");
		response = firebase.put(dataMap);

		// "GET" (the fb4jDemo-root)
		response = firebase.get();
		System.out.println("\n\nResult of GET:\n" + response);
		System.out.println("\n");

		// "PUT" (test-map into a sub-node off of the fb4jDemo-root)
		dataMap = new LinkedHashMap<String, Object>();
		dataMap.put("date_time", "date_time");
		dataMap.put("icmp_seq", "icmp_seq");
		dataMap.put("to", "to");
		dataMap.put("ttl", "ttl");
		dataMap.put("status", "status");
		dataMap.put("time", "time");

		/*
		 * Map<String, Object> dataMap2 = new LinkedHashMap<String, Object>();
		 * dataMap2.put( "Sub-Key1", "This is the first sub-value" );
		 * dataMap.put( "Key_3", dataMap2 );
		 */

		for (int i = 0; i > 20; i++) {
			response = firebase.post("put"+i, dataMap);
		}
		
		//response = firebase.post("put1", dataMap);
		//response = firebase.post("put2", dataMap);
		//response = firebase.put("put3", dataMap);
		

		System.out.println("\n\nResult of PUT (for the test-PUT):\n" + response);
		System.out.println("\n");

		// "GET" (the test-PUT)
		response = firebase.get("put");
		System.out.println("\n\nResult of GET (for the test-PUT):\n" + response);
		System.out.println("\n");

		/*
		 * // "POST" (test-map into a sub-node off of the fb4jDemo-root)
		 * response = firebase.post( "test-POST", dataMap ); System.out.println(
		 * "\n\nResult of POST (for the test-POST):\n" + response );
		 * System.out.println("\n");
		 * 
		 * 
		 * 
		 * 
		 * // "DELETE" (it's own test-node) dataMap = new LinkedHashMap<String,
		 * Object>(); dataMap.put( "DELETE",
		 * "This should not appear; should have been DELETED" ); response =
		 * firebase.put( "test-DELETE", dataMap ); System.out.println(
		 * "\n\nResult of PUT (for the test-DELETE):\n" + response ); response =
		 * firebase.delete( "test-DELETE"); System.out.println(
		 * "\n\nResult of DELETE (for the test-DELETE):\n" + response );
		 * response = firebase.get( "test-DELETE" ); System.out.println(
		 * "\n\nResult of GET (for the test-DELETE):\n" + response );
		 */
	}

}
