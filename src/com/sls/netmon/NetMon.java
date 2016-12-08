package com.sls.netmon;

import org.apache.log4j.Logger;

public class NetMon {
	protected static final Logger LOGGER = Logger.getRootLogger();

	public static void main(String[] args) {

		String type = "local";
		String ip = "";
		int time = 0;
		String firebase_baseUrl = ""
				+ "";

		if (args.length == 0) {
			System.err.println("Please specify the arguments :[type] [ip] [time] [firebase url]");
			return;
		} else {
			if (args.length == 1) {
				type = args[0].trim();
			}
			if (args.length == 2) {
				type = args[0].trim();
				ip = args[1].trim();
			}
			if (args.length == 3) {
				type = args[0].trim();
				ip = args[1].trim();
				time = Integer.parseInt(args[2].trim());

			}
			if (args.length == 4) {
				type = args[0].trim();
				ip = args[1].trim();
				time = Integer.parseInt(args[2].trim());
				firebase_baseUrl = args[3].trim();
			}
		}

		System.out.println("Configurations : " + type + " " + ip + " " + time + " " + firebase_baseUrl);

		Ping.ping(ip, time, firebase_baseUrl, type);
		// Ping.ping(ip, time, "");

	}

}
