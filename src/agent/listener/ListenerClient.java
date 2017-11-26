package agent.listener;

import agent.data.AgentData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListenerClient extends Thread {

    private Socket clientSocket;
    private String clientName;
    private volatile AgentData msg;

    public ListenerClient(Socket socket) {
        clientSocket = socket;
        clientName = clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort();
    }

    @Override
    public void run() {

        BufferedReader br = null;

        while(true) {

            try {
                br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String line;

            try {
                while ((line = br.readLine()) != null) {
                    String arr[] = line.split(":");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
