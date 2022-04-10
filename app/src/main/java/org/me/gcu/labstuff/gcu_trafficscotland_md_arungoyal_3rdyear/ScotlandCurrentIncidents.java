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
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class ScotlandCurrentIncidents extends Fragment {

    private Feed feed;
    private xmlParser parser;
    private TextView title, description, link, location, pubDate;
    private ArrayList<TrafficScotlandRoadworks> items;
    private String urlCurrentIncidents = "https://trafficscotland.org/rss/feeds/currentIncidents.aspx";

    public ScotlandCurrentIncidents() {
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
        View view = inflater.inflate(R.layout.fragment_scotland_current_incidents, container, false);
        // create objects
        feed = new Feed();
        parser = new xmlParser();
        // get references
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("TRAFFIC SCOTLAND CURRENT INCIDENTS");
        title = view.findViewById(R.id.txtViewTitle);
        description = view.findViewById(R.id.txtViewDescription);
        link = view.findViewById(R.id.txtViewLink);
        location = view.findViewById(R.id.txtViewLocation);
        pubDate = view.findViewById(R.id.txtViewPubDate);
        // set the listeners
        new DownloadFeed().execute(urlCurrentIncidents);
        //return view to layout
        return view;
    }

    public class DownloadFeed extends AsyncTask<String, Void, String> {
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
            items = parser.getItems();
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            Log.d("Roadworks Reader", "Feed Read");
            //update the display
            ScotlandCurrentIncidents.this.updateDisplay();
        }
    }

    public void updateDisplay()
    {
        if(items == null){
            Context context = getActivity().getApplicationContext();
            CharSequence text = "Unable to get RSS feed";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        for(TrafficScotlandRoadworks sr : items){
            title.setText("Title: " + sr.getTitle());
            description.setText("Description: " + sr.getDescription());
            link.setText("Link: " + sr.getLink());
            location.setText("Location: " + sr.getLocation());
            pubDate.setText("Published Date: " + sr.getPubDate());
        }
    }
}