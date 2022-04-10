package org.me.gcu.labstuff.gcu_trafficscotland_md_arungoyal_3rdyear;

import android.service.dreams.DreamService;

public class DescAndDateFormat {

    public String getDescription(String desc){
        int result = desc.lastIndexOf("<br/>");
        if(result == -1){
            return desc;
        }else
        {
            return desc.substring(result+6, desc.length());
        }
    }

    public String[] getDates(String date) throws StringIndexOutOfBoundsException
    {
        if((date.indexOf("Start Date: ") == -1)||(date.indexOf("End Date: ") == -1))
        {
            return null;
        }
        else
        {
            String startDateIn = date.substring(date.indexOf("Start Date: "), date.indexOf(':'));

            String date1 = date.substring(startDateIn.length() + 2, date.indexOf('<'));
            String remainingString = date.substring(date.indexOf('>'));

            String endDateIn = remainingString.substring(remainingString.indexOf("End Date: "),
                    date.indexOf(':'));

            String date2 = "";
            if(date.indexOf("<br/>Delay") != -1){
                date2 = remainingString.substring(endDateIn.length() + 2,remainingString.indexOf('<'));
            }
            else{
                date2 = remainingString.substring(endDateIn.length() + 2);
            }
            String[] results = new String[2];
            results[0] = date1;
            results[1] = date2;
            return results;
        }
    }

    public String convertDate(String date){
        String regex = "\\d+";
        String result = "";
        String[] words = date.split(" ");
        for(String word:words){
            if(word.matches(regex)){
                result += word+ "/";
            }else if(!getMonth(word).isEmpty()){
                result += getMonth(word) + "/";
            }
        }
        return result.substring(0, result.length() -1);
    }

    public String getMonth(String month){
        switch (month)
        {
            case "January": return "01";
            case "February": return "02";
            case "March": return "03";
            case "April": return "04";
            case "May": return "05";
            case "June": return "06";
            case "July": return "07";
            case "August": return "08";
            case "September": return "09";
            case "October": return "10";
            case "November": return "11";
            case "December": return "12";

            default: return "";
        }
    }
}
