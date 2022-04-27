import java.util.*;

abstract class AbsMessage {

    private String adressee, messageContent;
    Map<String, ArrayList<Message>> INBOX = new HashMap<String, ArrayList<Message>>();
    public AbsMessage(String addressee, String messageContent) {
        this.adressee = addressee;
        this.messageContent = messageContent;
    }

    void sendMessage(String sender, Message message) {

    }

    abstract void showMessages();

}
