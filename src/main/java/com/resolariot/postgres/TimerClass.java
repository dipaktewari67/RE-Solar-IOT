
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
import java.util.TimerTask;

/**
*
* @author pandhir
*/
public class TimerClass extends TimerTask {
    
    Connection conn = null;
    private Timestamp startTime;
    private Timestamp endTime;
    private int counter = 0;
    private final String url = "jdbc:postgresql://ec2-23-21-188-236.compute-1.amazonaws.com:5432/df65a718i89dtb";
    private final String user = "xyjjlzjifvyfzq";
    private final String password = "60a0aafc09296d38da99e3a72c58782d47e5c0c4af4bc5632b2a9a167ce1dedc";
    
    public TimerClass(Timestamp startTime,Timestamp endTime) throws SQLException{
        this.startTime = startTime;
        this.endTime =endTime;
        conn = DriverManager.getConnection(url, user, password);
    }
    
    @Override
    public void run() {
        if(counter != 0){
            startTime = endTime;
            long noofmillisecs = endTime.getTime()+15*60*1000;
            endTime =new Timestamp(noofmillisecs);
        }
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select Voltage from public.\"Voltage Table\" limit 10");
            counter++;
            while (rs.next()) {
                  //String lastName = rs.getString(1);
                  Timestamp timestamp = rs.getTimestamp("event_datetime__c");
            }
            
        } catch (SQLException e) {
            System.out.println("here "+e.getMessage());
        }
               
    }
  /*  
    public void callgcp(){
        
    //HttpClient client = new DefaultHttpClient();
    System.out.println("here1 ");
    HttpPut putreq = new HttpPut("https://pubsub.googleapis.com/v1/IOTFleet");
    HttpPost post = new HttpPost("https://pubsub.googleapis.com/v1/IOTFleet:publish");
    System.out.println("here2 ");
    List<String> str = new ArrayList <>();
    str.add("test1");
    str.add("test2");
    Gson gson = new Gson();
    String jsonPostgresList = gson.toJson(str);
    StringEntity input;
        try {
            input = new StringEntity(jsonPostgresList);
            post.setEntity(input);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TimerClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    } **/
}
