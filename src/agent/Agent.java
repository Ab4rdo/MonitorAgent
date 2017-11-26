package agent;

import agent.listener.AgentListener;
import commands.Command;
import counter.Counter;

import java.net.InetAddress;

public class Agent extends Thread{


    private int myPort;
    private Counter counter;
    private AgentListener listener;
    private Thread client;
    private volatile boolean isStoped;

    public static void main(String args[]) {

        if(args.length < 2) {
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }

        int initCounter = Integer.parseInt(args[0]);
        int myPort = Integer.parseInt(args[1]);

        // creating agent
        if(args.length==4) {
            String hostname = args[2];
            int hostPort = Integer.parseInt(args[3]);
            try {
                // starting normal agent
                new Agent(initCounter, myPort, hostname, hostPort).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                // starting introducing agent
                new Agent(initCounter, myPort).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Constructor for introducing agent
     * @param initCounter
     * @param myPort
     * @throws Exception
     */
   public Agent(int initCounter, int myPort) throws Exception {
       System.out.println("Created introducing agent");
       this.myPort = myPort;
       counter = new Counter(initCounter);
       listener = new AgentListener(this);
       isStoped = false;
   }

    /**
     * Constructor for standard agent
     * @param initCounter
     * @param myPort
     * @param hostname
     * @param hostPort
     * @throws Exception
     */
   public Agent(int initCounter, int myPort, String hostname, int hostPort) throws Exception{
       System.out.println("Created agent");
       this.myPort = myPort;
       counter = new Counter(initCounter);
       InetAddress myAddr = InetAddress.getLocalHost();
       client = new Thread(new AgentClient(hostname, hostPort, myAddr, myPort));
       isStoped = false;
   }


    @Override
    public synchronized void start() {
        super.start();
        counter.start();
        if(client != null) {
            client.start();
        } else {
            listener.start();
        }
    }

    @Override
    public void run() {
       while(!isStoped) {

       }
    }

    public synchronized int getMyPort() {
        return myPort;
    }

    public synchronized long getCounterValue() {
       return counter.getCount();
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
        counter.start();
    }
}
