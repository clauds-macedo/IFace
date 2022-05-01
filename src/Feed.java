public class Feed extends Message {

    private String visibility, postOwner;

    public Feed(String messageContent, String sender, String visibility) {
        super(messageContent, sender);
        this.visibility = visibility;
        this.postOwner = sender;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public String getPostOwner() {
        return this.postOwner;
    }
    @Override
    public String toString() {
        return super.toString()+
                "\nVisibilidade: " + this.visibility + "\n";
    }

}
