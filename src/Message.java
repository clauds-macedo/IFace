import java.util.*;

public abstract class Message {

    private String messageContent, sender;

    public Message(String messageContent, String sender) {
        this.messageContent = messageContent;
        this.sender = sender;
    }

    @Override
    public String toString() {
        return  sender + ": " + messageContent;
    }

}