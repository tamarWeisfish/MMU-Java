package com.hit.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.beans.PropertyChangeEvent;

import com.hit.services.CacheUnitController;
import com.hit.services.CacheUnitService;

public class Server extends java.lang.Object
        implements java.beans.PropertyChangeListener, java.lang.Runnable {

    private ServerSocket server;
    private Socket someClient;
    private Boolean flag;
    private boolean shutdown;
    private boolean isSocketExist;
    private final CacheUnitController<String> controller;
/* Communicates with the client receives requests,
 forwards them to HandleRequest,
 receives a reply from him and forwards them to the client*/
    public Server() throws IOException {
        flag=false;
        shutdown = true;
        isSocketExist = true;
        controller=new CacheUnitController<String>();
        server = new ServerSocket(12345);
    }

    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if (evt.getNewValue().equals("start")) {
            if (!shutdown) return;
            flag = true;
            shutdown = false;
            isSocketExist = true;
            new Thread(this).start();
        } else if (evt.getNewValue().equals("shutdown") && !shutdown){
            isSocketExist = false;
            shutdown = true;
            controller.Stop();
            flag = false;
        }
    }

    public void run() {
        try {
            System.out.println("server");
            while (!shutdown) {
                Socket socket = server.accept();
                if (isSocketExist) {
                    Thread t = new Thread(new HandleRequest<String>(socket, controller));
                    t.start();
                }
            }
            server.close();
        } catch (Exception e) {
            try {
                throw e;
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
