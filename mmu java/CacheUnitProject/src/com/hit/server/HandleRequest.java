

package com.hit.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;


import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
/* Gets What Server Request opens it ,
and passes to CacheUnitController Gets an answer and returns to Server */
public class HandleRequest <T>  implements  Runnable{

    private InputStream reqsocket;
    private DataOutputStream output;
    private CacheUnitController<T> controller;
    private Socket socket;

    public HandleRequest(Socket s, CacheUnitController<T> stringCacheUnitController) {
        socket=s;
        try {
            output = new DataOutputStream(s.getOutputStream());
            reqsocket = s.getInputStream();
            this.controller = stringCacheUnitController;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            DataInputStream reader = new DataInputStream(new BufferedInputStream(reqsocket));
            StringBuilder sb = new StringBuilder();
            String content = "";
            do {
                content = reader.readUTF();
                sb.append(content);
            } while (reader.available() != 0);
            content = sb.toString();
            Type requestType = new TypeToken<Request<DataModel<T>[]>>() {
            }.getType();
            if(content.equals("show statistics")){
                String c=controller.showStatistics();
                output.writeUTF(c);
            }else{
                System.out.println("hhhhhhhhnd\n"+content);
                Request<DataModel<T>[]> request = new Gson().fromJson(content, requestType);
                String action = request.getHeaders().get("action");
                DataModel<T>[] body = request.getBody();
                System.out.println(action+"action\n");
                switch (action) {
                    case "UPDATE":
                        output.writeUTF(String.valueOf(controller.update(body)));
                        break;
                    case "GET":
                        System.out.println("get");
                        output.writeUTF(new Gson().toJson(controller.get(body)));
                        break;
                    case "DELETE":
                        output.writeUTF(String.valueOf(controller.delete(body)));;
                        break;
                    default:
                        break;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
