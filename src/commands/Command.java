package commands;

public enum Command {

    CLK("RETURN COUNTER VALUE"),
    NET("RETURN ALL AGENTS INFO"),
    SYC("SYNCHRONISE COUNTER");

    private String info;

    Command(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
