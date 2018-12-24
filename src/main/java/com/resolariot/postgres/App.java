/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.resolariot.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Timer;

import org.slf4j.LoggerFactory;

/**
 *
 * @author pandhir
 */
public class App {

	private final String url = "jdbc:postgresql://ec2-23-21-188-236.compute-1.amazonaws.com:5432/df65a718i89dtb";
	private final String user = "xyjjlzjifvyfzq";
	private final String password = "60a0aafc09296d38da99e3a72c58782d47e5c0c4af4bc5632b2a9a167ce1dedc";

	private final org.slf4j.Logger LOG = LoggerFactory.getLogger(App.class);

	/**
	 * @param args the command line arguments
	 */
	public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			LOG.info("Connected to the PostgreSQL server successfully.");
			Statement stmt = conn.createStatement();
			LOG.info("after connection");
			ResultSet rs = stmt.executeQuery("select \"Voltage\" from public.\"Voltage Table\" limit 10");
			LOG.info("hello");
			while (rs.next()) {
				LOG.info(rs.getString(1));
				// String voltage = rs.getString("Voltage");
				// System.out.format("%s\n",voltage);
			}
			/*
			 * if(rs.next()){ if(starttime != null){
			 * 
			 * } else{ starttime = rs.getTimestamp("event_datetime__c"); long noofmillisecs
			 * = starttime.getTime()+15*60*1000; endtime = new Timestamp(noofmillisecs); }
			 * App app = new App(); app.timerfunction(starttime,endtime);
			 * 
			 * }
			 **/

		} catch (SQLException e) {
			LOG.info("here 11 " + e.getMessage());
		}
		return conn;
	}

	public void timerfunction(Timestamp startTime, Timestamp endTime) throws SQLException {

		// new TimerClass(startTime,endTime).callgcp();

		Timer timer = new Timer();
		timer.schedule(new TimerClass(startTime, endTime), 0, 5000);
	}

}
