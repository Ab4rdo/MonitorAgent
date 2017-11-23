package agent;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AgentListener implements Runnable{

    private final ServerSocket listenSocket;
    private volatile boolean isStop;
    private volatile List<Socket> clientAgents;

    /**
     * threaded class for agent listening socket
     * @param agentSocket
     */
    public AgentListener(ServerSocket agentSocket) {
        System.out.println("Agent listener established");
        listenSocket = agentSocket;
        isStop = false;
        clientAgents = Collections.synchronizedList(new LinkedList<Socket>());
    }

    @Override
    public void run() {
        System.out.println("Agent listener is running");
        while(!isStop) {
            Socket temp = null;
            try {
                temp = listenSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(temp != null) {
                clientAgents.add(temp);
            }
        }
        System.out.println("Agent listener stoped");
    }

    public synchronized void stopAgentListener() { isStop = true; }

    public synchronized List<Socket> getClientSockets() {
        return clientAgents;
    }

    public synchronized boolean isStop() {
        return isStop;
    }

    public synchronized InetAddress getSocketIp() {
        return listenSocket.getInetAddress();
    }
}
