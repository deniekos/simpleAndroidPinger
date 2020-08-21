package com.dekosapp.connquality;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

private TextView txtInput;
private TextView txtLatencyValue;
private TextView txtLatencyTitle;
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
txtInput = (EditText)findViewById(R.id.txtInput);
txtLatencyValue = (TextView)findViewById(R.id.txtLatency);
txtLatencyTitle = (TextView)findViewById(R.id.txtLatencyTitle);
txtInput.setText("");

}



public void onClickGetLatencyIP(View v) {
String ip =txtInput.getText().toString();
Log.v("TAG", ip);
if (ip.equals("")) {
    Toast.makeText(this.getApplicationContext(), "You cannot leave the input empty !",
            Toast.LENGTH_SHORT).show();
}
else {
    getLatencyIP(ip);
}
}

    public void onClickGetLatencyURL(View v) throws MalformedURLException, UnknownHostException {
        String url =txtInput.getText().toString();
        Log.v("TAG", url);
        if (url.equals("")) {
            Toast.makeText(this.getApplicationContext(), "You cannot leave the input empty !",
                    Toast.LENGTH_SHORT).show();
        }
        else {
//            getLatencyURL(url);
            Toast.makeText(this.getApplicationContext(), "I still develop the method to convert url to ipAddress, my getLatencyURL method returning force close when i convert String to URL using new URL(). If you have any ideas just leave a comment",
                    Toast.LENGTH_LONG).show();
        }

    }


public int getLatencyIP(String ipAddress){
        String pingCommand = "/system/bin/ping -c " + 1 + " " + ipAddress;
        String inputLine = "";
        double avgRtt = 0;
        int latency = 0;

        try {
            Log.v("TEST", "getLatencyIP");
            // execute the command on the environment interface
            Process process = Runtime.getRuntime().exec(pingCommand);
            // gets the input stream to get the output of the executed command
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            inputLine = bufferedReader.readLine();
            while ((inputLine != null)) {
                if (inputLine.length() > 0 && inputLine.contains("avg")) {  // when we get to the last line of executed ping command
                    break;
                }
                inputLine = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            Log.v("", "getLatency: EXCEPTION");
            e.printStackTrace();
        }

// Extracting the average round trip time from the inputLine string
        String afterEqual = inputLine.substring(inputLine.indexOf("="), inputLine.length()).trim();
        String afterFirstSlash = afterEqual.substring(afterEqual.indexOf('/') + 1, afterEqual.length()).trim();
        String strAvgRtt = afterFirstSlash.substring(0, afterFirstSlash.indexOf('/'));
        avgRtt = Double.valueOf(strAvgRtt);
        latency = (int) avgRtt;
        txtLatencyValue.setText(strAvgRtt + "ms");
        txtLatencyTitle.setText("your latency to " + ipAddress + " is:");

        Log.v("TEST RESULT DOUBLE", strAvgRtt);
        Log.v("TEST RESULT INTEGER", String.valueOf(latency));

        return latency;
    }

    public int getLatencyURL(String url) throws MalformedURLException, UnknownHostException {
        String urlString = url;
        URL myURL = new URL(urlString);
        InetAddress address = InetAddress.getByName(myURL.getHost());
        String ipAddress = address.getHostAddress();
        String pingCommand = "/system/bin/ping -c " + 1 + " " + ipAddress;
        String inputLine = "";
        double avgRtt = 0;
        int latency = 0;

        try {
            Log.v("TEST", "getLatencyIP");
            // execute the command on the environment interface
            Process process = Runtime.getRuntime().exec(pingCommand);
            // gets the input stream to get the output of the executed command
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            inputLine = bufferedReader.readLine();
            while ((inputLine != null)) {
                if (inputLine.length() > 0 && inputLine.contains("avg")) {  // when we get to the last line of executed ping command
                    break;
                }
                inputLine = bufferedReader.readLine();
            }
        }
        catch (IOException e){
            Log.v("", "getLatency: EXCEPTION");
            e.printStackTrace();
        }

// Extracting the average round trip time from the inputLine string
        String afterEqual = inputLine.substring(inputLine.indexOf("="), inputLine.length()).trim();
        String afterFirstSlash = afterEqual.substring(afterEqual.indexOf('/') + 1, afterEqual.length()).trim();
        String strAvgRtt = afterFirstSlash.substring(0, afterFirstSlash.indexOf('/'));
        avgRtt = Double.valueOf(strAvgRtt);
        latency = (int) avgRtt;
        txtLatencyValue.setText(strAvgRtt + "ms");
        txtLatencyTitle.setText("your latency to " + ipAddress + " is:");

        Log.v("TEST RESULT DOUBLE", strAvgRtt);
        Log.v("TEST RESULT INTEGER", String.valueOf(latency));

        return latency;
    }



}
