package com.hit.client;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/* Called by CacheUnitClientObserver,
 communicates with the server,
 sends a suitable request,
 receives a reply and returns it to CacheUnitClientObserver*/
public class CacheUnitClient  extends  java.lang.Object{
    public   CacheUnitClient(){

    }
    public java.lang.String	send(java.lang.String request){
        String content = "";
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            System.out.println(request);
            writer.writeUTF(request);
            writer.flush();


            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            StringBuilder sb = new StringBuilder();

            do {
                content = in.readUTF();
                sb.append(content);
            } while (in.available() != 0);

            content = sb.toString();
            System.out.println("message from server: " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    return content;
    }

}
