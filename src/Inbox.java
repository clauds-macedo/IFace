import java.util.ArrayList;

public class Inbox extends Message {

    private String addressee;

    public Inbox(String addressee, String sender, String content) {
        super(content, sender);
        this.addressee = addressee;
    }

    @Override
    public String toString() {
        return super.toString()
                + " - Para: " + addressee
                + "\n";
    }

    public String getAddressee() { return addressee; }
}
