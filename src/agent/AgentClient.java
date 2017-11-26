package agent;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class AgentClient implements Runnable{

    private Socket mySocket;

    public AgentClient(String hostname, int serverPort, InetAddress myAddr, int myPort) throws IOException{
        System.out.println("Created new agent client");
        mySocket = new Socket(hostname, serverPort, myAddr, myPort);

    }

    @Override
    public void run() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(mySocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println("Info to server");
    }
}
