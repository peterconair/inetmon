package com.sls.netmon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;


public class Ping {
	protected static final Logger LOGGER = Logger.getRootLogger();


	public Ping() {

	}

	public static void ping(String ip, int time, String url, String type) {

		String pingCmd = "";
		if (time == 0) {
			pingCmd = "ping " + ip;
		} else {
			pingCmd = "ping " + ip + " -n -i " + time;
		}

		LOGGER.info(pingCmd);
		try {
			Runtime r = Runtime.getRuntime();
			Process p = r.exec(pingCmd);

			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (!url.equals("")) {
					Save.save(mapObject(inputLine, type), url);
				} else {
					mapObject(inputLine, type);
				}
			}
			in.close();

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Result mapObject(String result, String type) {
		Result resultObj = new Result();

		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// Connected
		if (result.contains("from")) {
			LOGGER.info("OK " + ft.format(new Date()) + " : " + result);
			resultObj.setDateTime(ft.format(new Date()));

			int startFrom = result.indexOf("from");
			int endFrom = result.indexOf(":");
			String from = result.substring(startFrom + 5, endFrom).trim();

			resultObj.setFrom(from);

			int startSeq = result.indexOf("icmp_seq=");
			int endSeq = result.indexOf("ttl");
			String icmpSeq = result.substring(startSeq + 9, endSeq).trim();

			resultObj.setIcmpSeq(icmpSeq);

			int startTtl = result.indexOf("ttl=");
			int endTtl = result.indexOf("time");
			String ttl = result.substring(startTtl + 4, endTtl).trim();

			resultObj.setTtl(ttl);

			int startTime = result.indexOf("time=");
			int endTime = result.indexOf("ms");
			String time = result.substring(startTime + 5, endTime).trim();

			resultObj.setTime(time);
			resultObj.setStatus("OK");
			resultObj.setType(type);
			resultObj.setDescription(result);

			// Request timeout
		} else if (result.contains("Request timeout")) {
			LOGGER.info("Not Connected " + ft.format(new Date()) + " : " + result);

			int startSeq = result.indexOf("icmp_seq ");
			String icmpSeq = result.substring(startSeq + 9).trim();

			resultObj.setIcmpSeq(icmpSeq);
			resultObj.setTime("");
			resultObj.setTtl("");
			resultObj.setDateTime(ft.format(new Date()));
			resultObj.setStatus("Not Connected");
			resultObj.setType(type);
			resultObj.setDescription(result);

			// Other
		} else {
			LOGGER.info("Other " + ft.format(new Date()) + " : " + result);

			resultObj.setTime("");
			resultObj.setTtl("");
			resultObj.setDateTime(ft.format(new Date()));
			resultObj.setStatus("Other");
			resultObj.setType(type);
			resultObj.setDescription(result);

		}
		return resultObj;

	}

}
