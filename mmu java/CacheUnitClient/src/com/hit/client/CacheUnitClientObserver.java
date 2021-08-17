package com.hit.client;

import com.hit.view.CacheUnitView;
import java.util.Scanner;
/* Called by CacheUnitView with user request,
 opens the request sends it to CacheUnitClient receives a reply ת
 and returns text to display to the user.*/
public class CacheUnitClientObserver extends java.lang.Object implements java.beans.PropertyChangeListener {
    private CacheUnitClient cacheUnitClient;

    public CacheUnitClientObserver() {
        cacheUnitClient = new CacheUnitClient();
    }

    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        System.out.println("CacheUnitClientObserver in propertyChange\n ");
        if(evt.getNewValue().equals("show statistics")){
            String re = cacheUnitClient.send("show statistics");
            CacheUnitView view = (CacheUnitView) evt.getSource();
            view.updateUIData(re);
        }else{
            Scanner command = (Scanner) evt.getNewValue();
            String datalin = "";
            while (command.hasNextLine()) {
                datalin = datalin + command.nextLine();
            }
            System.out.println(datalin+"datalin\n");
            String re = cacheUnitClient.send(datalin);
            System.out.println("req: " + re);
            CacheUnitView view = (CacheUnitView) evt.getSource();
            String response=re;
            System.out.println("req: " + response);
            //String response = null;
            //response = cacheUnitClient.send(request);
            String response2;
            System.out.println(response);
            if (response.equals("true")) {
                response2 = "                                          success";
                System.out.println("                                          success\n");
            } else if (response.equals("false")) {
                response2 = "                                          fail";
            } else if (response.split(" ")[0].equals("\"error:")) {
                if (response.contains("\"")) {
                    response2 = ((String) response).split("\"")[1] + ";fail";
                } else response2 = response + "\n                                          ;fail";
            } else response2 = response + "\n                                          ;success";
            view.updateUIData(response2);
        }



    }

}
