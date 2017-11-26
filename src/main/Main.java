package main;

import agent.Agent;

public class Main {


    public static void main(String[] args) {

       String[] args1 = { "1000", "9999"};
       String[] args2 = { "1000", "9998", "localhost", "9999"};
       String[] args3 = { "1000", "9997", "localhost", "9999" };

        Agent.main(args1);
        Agent.main(args2);
        Agent.main(args3);


    }



}
