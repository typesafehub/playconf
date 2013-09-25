package actors.messages;

public class CloseConnectionEvent {

    private String uuid;

    public CloseConnectionEvent(String uuid) {
        this.uuid = uuid;
    }
    
    public String uuid() { return uuid; }
}