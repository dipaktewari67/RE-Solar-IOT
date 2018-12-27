/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.resolariot.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.resolariot.demo.VoltageRequest;

public class App {

	private final static Logger LOG = LoggerFactory.getLogger(App.class);

	private static final String STORMY = "stormy";
	private static final String MOON = "moon";
	private static final String RAINY = "rainy";
	private static final String SUNNY = "sunny";

	public void connect() {
		try {
			Statement stmt = DBConnection.getInstance().createStatement();
			LOG.info("after connection");
			ResultSet rs = stmt.executeQuery("select \"Voltage\" from public.\"Voltage Table\" limit 10");
			LOG.info("hello");
			while (rs.next()) {
				LOG.info(rs.getString(1));
				String s = rs.getString(1);
				LOG.info(s);
			}

		} catch (SQLException e) {
			LOG.info("here 11 " + e.getMessage());
		}
	}

	public List<Double> getVoltage(VoltageRequest voltageRequest) {
		List<Double> values = null;
		try {
			Statement stmt = DBConnection.getInstance().createStatement();
			LOG.info("after connection");

			boolean statusBool = voltageRequest.getStatus();
			String weather = voltageRequest.getWeather();

			ResultSet rs = null;
			String query = null;
			if (statusBool) {
				if (weather.equals(SUNNY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=11 and \"Voltage\"<=12 limit 10");

				else if (weather.equals(RAINY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=9 and \"Voltage\"<=10 limit 10");

				else if (weather.equals(STORMY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=10 and \"Voltage\"<=11 limit 10");

				else if (weather.equals(MOON))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=8 and \"Voltage\"<=9 limit 10");
			} else {
				if (weather.equals(SUNNY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=7 and \"Voltage\"<8 limit 10");

				else if (weather.equals(RAINY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=1 and \"Voltage\"<2 limit 10");

				else if (weather.equals(STORMY))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=2 and \"Voltage\"<3 limit 10");

				else if (weather.equals(MOON))
					query = ("select \"Voltage\" from public.\"Voltage Table\" where \"Voltage\">=3 and \"Voltage\"<4 limit 10");
			}

			if (query != null) {
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					if (values == null)
						values = new ArrayList<Double>();
					values.add(rs.getDouble(1));

					LOG.info(String.valueOf(rs.getDouble(1)));
				}
			}

		} catch (SQLException e) {
			LOG.info(e.getMessage(), e);
		}
		return values;
	}

	public void timerfunction(Timestamp startTime, Timestamp endTime) throws SQLException {

		// new TimerClass(startTime,endTime).callgcp();

		Timer timer = new Timer();
		timer.schedule(new TimerClass(startTime, endTime), 0, 5000);
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

}
