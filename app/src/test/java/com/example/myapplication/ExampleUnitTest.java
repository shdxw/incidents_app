package com.example.myapplication;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        readJSON("src\\main\\java\\com\\example\\myapplication\\incidents.json").toString();
    }
    private static String time(String date) {
        String[] time = date.split("[+]")[0].split("T");
        return time[0] + " " + time[1];
    }

    private static ArrayList<State> readJSON(String path) {
        ArrayList<State> rsl = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
            JSONObject jsonObject = obj;
            JSONArray arr = (JSONArray) jsonObject.get("incidents");
            for (Object o : arr) {
                JSONObject object = (JSONObject) o;
                String status = (String) object.get("STATUS");
                String timeNow = (String) object.get("ISKNOWNERRORDATE");
                String time = (String) object.get("TARGETFINISH");
                String desc = (String) object.get("DESCRIPTION");
                String system = (String) object.get("EXTSYSNAME");
                String ticketId = (String) object.get("TICKETID");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                System.out.println(time(timeNow).toString());
                System.out.println(new State(system, status, Integer.parseInt(ticketId), LocalDateTime.parse(time(time), formatter),
                        LocalDateTime.parse(time(timeNow), formatter), desc).toString());
                rsl.add(new State(system, status, Integer.parseInt(ticketId), LocalDateTime.parse(time(time), formatter),
                        LocalDateTime.parse(time(timeNow), formatter), desc));
            }


        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(rsl.size());
        return rsl;
    }
}