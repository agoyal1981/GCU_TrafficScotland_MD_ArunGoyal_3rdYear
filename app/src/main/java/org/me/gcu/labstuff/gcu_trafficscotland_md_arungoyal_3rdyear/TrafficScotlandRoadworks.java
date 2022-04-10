package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrafficScotlandRoadworks {
    private String title;
    private String description;
    private String link;
    private String location;
    private String pubDate;
    private String startDate;
    private String endDate;

    private SimpleDateFormat dateInFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    private SimpleDateFormat dateEndFormat = new SimpleDateFormat("EEEE, h:mm a (MMM d)");



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getPubDateFormatted(){
        try{
            if(pubDate != null)
            {
                Date date = dateInFormat.parse(pubDate);
                String pubDateFormatted = dateEndFormat.format(date);
                return pubDateFormatted;
            }
            else{
                return "No Date Found";
            }
        } catch (ParseException e) {
            return "No Date Found";
        }
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getStartDate() { return startDate; }

    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }

    public void setEndDate(String endDate) { this.endDate = endDate; }


    public TrafficScotlandRoadworks()
    {
        this.title = title;
        this.description = description;
        this.link = link;
        this.location = location;
        this.pubDate = pubDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TrafficScotlandRoadworks{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", location='" + location + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
