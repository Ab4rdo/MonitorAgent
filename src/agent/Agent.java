package agent;

import counter.Counter;
import exceptions.AgentException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agent extends Thread{

    private final List<Agent> otherAgents;
    private final Thread counter;
    private final Thread listener;
    private final int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private InetAddress ip;
    private volatile boolean isStop;

    /**
     * Agent is a new thread that should store Counter thread, ServerListener thread and Socket thread
     * @param initCount
     * @param port
     * @throws AgentException
     */

    public Agent(int initCount, int port) throws AgentException {
        otherAgents = Collections.synchronizedList(new ArrayList<>());
        counter = new Thread(new Counter(initCount));

        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener = new Thread(new AgentListener(serverSocket));
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        isStop = false;
    }

    public synchronized void startListen() throws IOException{
        listener.run();
    }


    @Override
    public synchronized void start() {

        super.start();

        try {
            startListen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();
        while (!isStop) {
            counter.run();
        }
    }

    public InetAddress getSocketIp() {
        return serverSocket.getInetAddress();
    }

    public synchronized void stopAgent() {
        isStop = true;
    }

}
