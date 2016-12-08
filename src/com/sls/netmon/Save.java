package com.sls.netmon;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.error.JacksonUtilityException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

public class Save {
	protected static final Logger LOGGER = Logger.getRootLogger();
	private static Firebase firebase;
	
	@SuppressWarnings("unused")
	private static FirebaseResponse response;

	public static void save(Result result, String firebaseUrl) {

		try {
			firebase = new Firebase(firebaseUrl);

			Map<String, Object> internetdataMap = new LinkedHashMap<String, Object>();

			response = firebase.get();

			internetdataMap = new LinkedHashMap<String, Object>();
			internetdataMap.put("date_time", result.getDateTime());
			internetdataMap.put("icmp_seq", result.getIcmpSeq());
			internetdataMap.put("from", result.getFrom());
			internetdataMap.put("ttl", result.getTtl());
			internetdataMap.put("status", result.getStatus());
			internetdataMap.put("type", result.getType());

			response = firebase.post("log", internetdataMap);
		} catch (JacksonUtilityException exception) {
			exception.printStackTrace();
		} catch (UnsupportedEncodingException exception) {
			exception.printStackTrace();
		} catch (FirebaseException exception) {
			exception.printStackTrace();
		}

	}

}
