package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ScotlandRoadworks extends Fragment {

    private Feed feed;// get the data
    private xmlParser parser; // Extract the data
    private ListView lstItemsRoadworks; // Widget in roadworks fragment
    private ArrayList<TrafficScotlandRoadworks> Items; // data structure to hold the Data
    private String urlRoadworks = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    private SimpleAdapter adapter;
    private View view;

    public ScotlandRoadworks() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
        }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    @Override
    public void onDetach(){
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_scotland_roadworks, container, false);
        // create objects
        feed = new Feed();
        parser = new xmlParser();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TRAFFIC SCOTLAND ROADWORKS");
        lstItemsRoadworks = view.findViewById(R.id.lstItemsRoadworks);//
        //
        new DownloadFeed().execute(urlRoadworks);

        return view;
    }

   public class DownloadFeed extends AsyncTask<String, Void, String>{
        @Override
       protected String doInBackground(String... params){
            String in = null;
            try{
                in = feed.downloadRSS(params[0]);
            }catch(IOException e){
                e.printStackTrace();
            }
            return in;
        }

        @Override
       protected void onPostExecute(String result){
            Log.d("reader", "RSS Feed Downloaded");
            new ReadFeed().execute(result);
        }
    }
    public class ReadFeed extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params){
            String s = params[0];
            parser.pullParser(s);
            Items = parser.getItems();
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            Log.d("Roadworks Reader", "Feed Read");
            //update the display
            ScotlandRoadworks.this.updateDisplay();
        }
    }

    public void updateDisplay()
    {
        if(Items == null){
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Unable to get RSS feed";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for(TrafficScotlandRoadworks sr : Items){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("pubDate", ("Publication Date: " + sr.getPubDateFormatted()));
            hashMap.put("title", ("Title: " + sr.getTitle()));
            hashMap.put("DateDetails", ("Start Date: " + sr.getStartDate() +
                    " - End Date: " + sr.getEndDate()));
            hashMap.put("description", ("Description: " + sr.getDescription()));
            hashMap.put("link",("Link: " + sr.getLink()));
            hashMap.put("location", ("Location: " + sr.getLocation()));
            data.add(hashMap);
        }

        //Create resource variables - from and to
        int resource = R.layout.roadworks;
        String[] from = {"pubDate", "title", "DateDetails", "description", "link","location"};
        int[] to = {R.id.txtViewPubDate, R.id.txtViewTitle, R.id.txtViewDateDetails, R.id.txtViewDescription,
        R.id.txtViewLink, R.id.txtViewLocation};

        //create set adapter
        if(getActivity() != null){
            SimpleAdapter adapter = new SimpleAdapter(getActivity().getApplicationContext(), data, resource, from, to);
            lstItemsRoadworks.setAdapter(adapter);
        }
    }
}