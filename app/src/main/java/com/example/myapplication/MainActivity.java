package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList();
    ListView countriesList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // начальная инициализация списка
        setInitialData();
        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list, states);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                State selectedState = (State) parent.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getDescription(),
//                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                for (State state : states) {
                    if (state.getStatus().equals(selectedState.getStatus())) {
                        intent.putExtra("status", selectedState.getStatus());
                        intent.putExtra("desc", selectedState.getDescription());
                        intent.putExtra("register", selectedState.getReportedBy());
                        intent.putExtra("level", selectedState.getLevel());
                        intent.putExtra("nowDate", selectedState.getLastDate().toString().replace('T', ' '));
                        intent.putExtra("lastDate", selectedState.getNowDate().toString().replace('T', ' '));
                        intent.putExtra("system", selectedState.getSysName());
                        intent.putExtra("otcl", selectedState.getNorm());
                        intent.putExtra("weight", selectedState.getLnorm());
                        startActivity(intent);
                    }
                }

            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }


    private void setInitialData()  {
        states.addAll(readJSON("incidents.json"));
    }


    private String time(String date) {
        String[] time = date.split("[+]")[0].split("T");
        return time[0] + " " + time[1];
    }



    private ArrayList<State> readJSON(String path) {
        ArrayList<State> rsl = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.incidents);

            Reader br = new BufferedReader(new InputStreamReader(inputStream));

            JSONObject obj = (JSONObject) parser.parse(br);
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
                String norm = (String) object.get("NORM");
                String lnorm = (String) object.get("LNORM");
                String level = (String) object.get("CRITIC_LEVEL");
                String reported = (String) object.get("REPORTEDBY");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                rsl.add(new State(system, status, Integer.parseInt(ticketId),reported, LocalDateTime.parse(time(time), formatter),
                        LocalDateTime.parse(time(timeNow), formatter), desc, norm, lnorm, level));
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