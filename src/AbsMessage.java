import java.util.*;

abstract class AbsMessage extends Database {

    private String adressee, messageContent, sender, kindOfAdressee;

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    Map<String, ArrayList<List<Message>>> INBOX = new HashMap<String, ArrayList<List<Message>>>();
    UserFriends userFriends = new UserFriends();
    public AbsMessage(String addressee, String messageContent, String sender) {
        this.adressee = addressee;
        this.messageContent = messageContent;
        this.sender = sender;
    }

    void sendMessage(Message message) {
        if (!userFriends.isYourFriend(this.adressee) && this.kindOfAdressee.equals("f")) {
            System.out.println("O usuário " + this.adressee + " não é seu amigo.");
            return;
        }
        if (!checkAccountExistence(this.adressee)) {
            System.out.println("Esse usuário não existe no nosso banco de dados.");
            return;
        }
        if (!INBOX.containsKey(this.adressee)) {
            INBOX.put(adressee, new ArrayList<List<Message>>());
        }
        INBOX.get(adressee).add((List<Message>) message);
    }

    @Override
    public String toString() {
        return "Enviado por: " + sender +"\n" +
                messageContent;
    }

    abstract void showMessage();
    abstract void usersWhoSentMessages();

}
