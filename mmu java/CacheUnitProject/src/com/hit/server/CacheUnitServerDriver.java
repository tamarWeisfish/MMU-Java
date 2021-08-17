package com.hit.server;

import com.hit.util.CLI;

import java.io.IOException;
import java.lang.*;
/* The running department of the server*/
public class CacheUnitServerDriver {
    public static void main(String[] args) throws IOException {
        CLI cli = new CLI(System.in, System.out);
        Server server = new Server();
        cli.addPropertyChangeListener(server);
        new Thread( cli).start();
//        for (int i = 0; i < 500; i++) {
//            System.out.println("{\"id\": "+i+",\"content\": \"Some String Data\"}");
//        }
    }
}

