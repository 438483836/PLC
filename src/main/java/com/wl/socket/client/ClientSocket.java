package com.wl.socket.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Vincent on 2017/5/20.
 */
public class ClientSocket {

    public static void main(String args[]){
        String host = "127.0.0.1";
        int port = 9090;
        try {
            Socket client = new Socket(host,port);
            Writer writer = new OutputStreamWriter(client.getOutputStream());
            writer.write("Hello Socket");
            writer.flush();
            writer.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
