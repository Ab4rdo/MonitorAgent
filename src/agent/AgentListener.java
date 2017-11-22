package agent;



import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AgentListener implements Runnable{

    private final ServerSocket listenSocket;
    private volatile boolean clientFound;
    private volatile Socket clientAgent;

    /**
     * threaded class for agent listening socket
     * @param agentSocket
     */
    public AgentListener(ServerSocket agentSocket) {

        listenSocket = agentSocket;
        clientFound = false;
        clientAgent = null;
    }

    @Override
    public void run() {
        while(!clientFound) {
            try {
                clientAgent = listenSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(clientAgent!=null) { clientFound = true; }
        }
        System.out.println("Found client");
    }

    public synchronized Socket getClientSocket() {
        return clientAgent;
    }

    public synchronized boolean isClientFound() {
        return clientFound;
    }

    public InetAddress getSocketIp() {
        return listenSocket.getInetAddress();
    }
}
