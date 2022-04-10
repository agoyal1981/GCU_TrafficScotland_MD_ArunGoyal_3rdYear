package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Feed {

    public Feed(){}

    public String downloadRSS(String urlRSS) throws IOException{
        URL url = new URL(urlRSS);
        BufferedReader buffer = null;
        InputStream inputStream = null;
        String result = "";
        int response = -1;

        URLConnection urlConnection = url.openConnection();
        try{
            HttpURLConnection httpURL = (HttpURLConnection) urlConnection;
            httpURL.setAllowUserInteraction(false);
            httpURL.setInstanceFollowRedirects(true);
            httpURL.setRequestMethod("GET");
            httpURL.setConnectTimeout(5000);
            httpURL.connect();

            response = httpURL.getResponseCode();

            if(response == HttpURLConnection.HTTP_OK){
                Log.e("XML TAG", "Connection not found");

                //READ DATA
                inputStream = httpURL.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);

                // pass data to string
                String rssData = "";

                while((rssData = bufferedReader.readLine()) != null)
                {
                    result = result + rssData;
                }
            }else{
                Log.e("XML TAG", "Connection not Found");
            }
        }catch (IOException e){
            Log.e("Sorry, Connection not found", e.toString());
        }
        return result;
    }
}
