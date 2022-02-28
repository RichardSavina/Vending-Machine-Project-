package com.techelevator;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Logger {
    private String text;
    private Date date = Calendar.getInstance().getTime();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy; HH:mm:ss a");
    private String dateTime = dateFormat.format(date);
    public Logger(){
    }


    public String getDateTime() {
        return dateTime;
    }

    public void logToFile(String dateTime, String text) throws FileNotFoundException {
            File loggedInfo = new File("C:\\Users\\Student\\workspace\\java-capstone-module-1-team-3\\target\\Log.txt");
            boolean append = loggedInfo.exists() ? true : false;
            try(PrintWriter writer = new PrintWriter(new FileOutputStream("Log.txt", append))) {
                writer.append(dateTime + text+ "\n");
            } catch (IOException e) {
                System.out.println("file does not exist" + e.getMessage());
            }
        }

    }