package agent.listener;


import agent.Agent;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AgentListener extends Thread{

    private ServerSocket mySocket;
    private Socket clientSocket;
    private List<ListenerClient> connections;

    public AgentListener(Agent agent) throws Exception{
        this.mySocket = new ServerSocket(agent.getMyPort());
        connections = new ArrayList<>();
        Collections.synchronizedList(connections);
        System.out.println("My server port: " + mySocket.getLocalPort());
    }

    @Override
    public void run() {

        while(true) {
            try {
                clientSocket = mySocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress address = clientSocket.getInetAddress();
            int port = clientSocket.getPort();
            System.out.println("creating new connection with " + address + ":" + port);
            ListenerClient newClient = new ListenerClient(clientSocket);
            newClient.start();
            connections.add(newClient);

        }
    }

    public List<ListenerClient> getConnections() {
        return connections;
    }
}
