package agent.data;

import agent.Agent;
import commands.Command;

public class AgentData {

    private long counterValue;
    private Command command;

    public AgentData(Agent agent){
        counterValue = agent.getCounterValue();
        command = null;
    }

    public AgentData(Agent agent, Command c) {
        counterValue = agent.getCounterValue();
        command = c;
    }

    public long getCounterValue() {
        return counterValue;
    }

    public Command getCommand() {
        return command;
    }
}
