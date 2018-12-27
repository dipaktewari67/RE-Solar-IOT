package com.resolariot.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;

public class DBConnection {

	private final static String url = "jdbc:postgresql://ec2-23-21-188-236.compute-1.amazonaws.com:5432/df65a718i89dtb";
	private final static String user = "xyjjlzjifvyfzq";
	private final static String password = "60a0aafc09296d38da99e3a72c58782d47e5c0c4af4bc5632b2a9a167ce1dedc";

	private final static org.slf4j.Logger LOG = LoggerFactory.getLogger(DBConnection.class);

	private static Connection conn = null;

	private DBConnection() {
	}

	public static Connection getInstance() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, user, password);
				LOG.info("Connected to the PostgreSQL server successfully.");
			} catch (SQLException e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return conn;
	}

}
