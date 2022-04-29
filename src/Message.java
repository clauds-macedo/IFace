import java.util.*;

public abstract class Message {

    private String messageContent, addreessee, sender;

    public Message(String messageContent, String addreessee, String sender) {
        this.messageContent = messageContent;
        this.addreessee = addreessee;
        this.sender = sender;
    }

//    public void usersWhoSentMessages(Map<String, ArrayList<List<Message>>> INBOX) {
//        List<Message> users = new ArrayList<Message>();
//        for(List<Message> usernames : INBOX.get(accountManagement.getLoggedInUser())) {
//            System.out.println(usernames);
//        }
//    }

    abstract void sendMessage();

    public void showMessage() {

    }

    @Override
    public String toString() {
        return "Enviado por: " + sender
                + "\n" + messageContent;
    }

}