package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class xmlParser {
    TrafficScotlandRoadworks item;

    private ArrayList<TrafficScotlandRoadworks> items;

    public ArrayList<TrafficScotlandRoadworks> getItems(){return items;}

    public void pullParser(String reader)
    {
        items = new ArrayList<TrafficScotlandRoadworks>();
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xml = factory.newPullParser();
            xml.setInput(new StringReader(reader));
            int event = xml.getEventType();
            DescAndDateFormat ddf = new DescAndDateFormat();

            while(event != XmlPullParser.END_DOCUMENT){
                if(event == XmlPullParser.START_DOCUMENT){
                    System.out.println("Start Document");
                }
                else if(event == XmlPullParser.START_TAG){
                    if(xml.getName().equals("Item")){
                        item = new TrafficScotlandRoadworks();
                        event = xml.nextTag();
                        if(xml.getName().equals("title")){
                            event = xml.next();
                            item.setTitle(xml.getText());
                            event = xml.nextTag();
                            event = xml.nextTag();
                        }
                        if(xml.getName().equals("description")){
                            event = xml.next();
                            item.setDescription(ddf.getDescription(xml.getText()));
                            String[] dates = ddf.getDates(xml.getText());

                            if(dates != null){
                                item.setStartDate(ddf.convertDate(dates[0]));
                                item.setEndDate(ddf.convertDate(dates[1]));
                            }
                        }
                        if(xml.getName().equals("link")){
                            event = xml.next();
                            item.setLink(xml.getText());
                            event = xml.nextTag();
                            event = xml.nextTag();
                        }
                        if(xml.getName().equals("location")) {
                            event = xml.next();
                            item.setLocation(xml.getText());
                            event = xml.nextTag();
                            event = xml.nextTag();
                        }

                        if(xml.getName().equals("pubDate")) {
                            event = xml.next();
                            item.setPubDate(xml.getText());
                            event = xml.nextTag();
                            event = xml.nextTag();
                        }

                        items.add(item);
                    }

                }
                event = xml.next();
            }


        } catch (XmlPullParserException e) {
            Log.e("MyTag", "Parsing Error");
        }catch (IOException e1){
            Log.e("MyTag", "IO Error");
        }
        Log.e("xmlParser", "End of Document");
    }
}
