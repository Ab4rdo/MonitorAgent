package agent;

import exceptions.AgentException;

public class AgentApp {

//    private

    public static void main(String[] args) {
        Agent a = null;
        System.out.println(args[0]);
        int init = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        try {
            a = new Agent(init, port);
        } catch (AgentException e) {
            e.printStackTrace();
        }
        a.start();


    }

}
