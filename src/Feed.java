public class Feed extends Message {

    private String visibility;

    public Feed(String messageContent, String sender, String visibility) {
        super(messageContent, sender);
        this.visibility = visibility;
    }

    public String getVisibility() {
        return this.visibility;
    }

    @Override
    public String toString() {
        return super.toString()+
                "\nVisibilidade: " + this.visibility + "\n";
    }

}
