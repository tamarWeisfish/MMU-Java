package com.hit.util;

import sun.net.www.protocol.file.FileURLConnection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/* Listens to the server's requests If "start" is received,
the server opens for client requests and if "shutdown" the server closes
for client requests and the cache is updated in the hard memory*/
public class CLI extends java.lang.Object implements java.lang.Runnable {
    private final BufferedReader in;
    private final OutputStream out;
    private final PropertyChangeSupport observer;



    public CLI(InputStream in, OutputStream out) throws IOException {
        this.in = new BufferedReader(new InputStreamReader(in));
        this.out = out;
        observer = new PropertyChangeSupport(this);


    }

    public void addPropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        observer.addPropertyChangeListener(pcl);

    }

    public void removePropertyChangeListener(java.beans.PropertyChangeListener pcl) {
        observer.removePropertyChangeListener(pcl);

    }

    @Override
    public void run() {
        try {
            write("Please enter your command\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String line = "";
        while (true) {
            try {
                line=in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.equals("start")) {
                try {
                    write("Starting server.......\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                observer.firePropertyChange("command", null, line);
            } else if (line.equals("shutdown")) {
                try {
                    write("Shutdown server\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                observer.firePropertyChange("command", null, line);
                try {
                    write("Please enter your command\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    write("Not a valid command\n");

                    write("Please enter your command\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }






    public void write(java.lang.String string) throws IOException {
       try {
           out.write(string.getBytes(StandardCharsets.UTF_8));
       }catch (Exception exception){
           exception.printStackTrace();
       }


    }


}
