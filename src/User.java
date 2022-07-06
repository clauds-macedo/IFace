import java.util.*;

public class User {

    public String username, password, nickname;

    ArrayList<Feed> UserFeed = new ArrayList<>();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public User(){}

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public void dataForPostOnFeed(String sender) throws FeedException {
        System.out.println("No que está pensando?");
        String messageContent = scanner.next();

        if (messageContent.isEmpty()) {
            throw new FeedException("O corpo da mensagem não pode estar vazio.");
        }

        System.out.println("Selecione uma opção: \n" +
                "f - Postar para amigos\n" +
                "p - Postar em modo público");
        String postVisibility = scanner.next();
        if (!(postVisibility.equals("p") || postVisibility.equals("f"))) {
            throw new FeedException("Opção inválida.");
        }
        postOnFeed(new Feed(messageContent,
                sender,
                postVisibility.toLowerCase()));
    }
    public void postOnFeed(Feed post) {
        UserFeed.add(post);
    }
    public void showFeed(UserFriends userFriends, String loggedInUser) {
        for (Feed post : UserFeed) {
            if (post.getVisibility().equals("f")) {
                if (userFriends.isYourFriend(post.getSender())
                    || post.getSender().equals(loggedInUser)) {
                    System.out.println(post);
                }
            }
            else if (post.getVisibility().equals("p")) {
                System.out.println(post);
            }
        }
    }

    void deletePostOnFeed(String loggedInUser) {
        Iterator<Feed> post = UserFeed.iterator();
        while (post.hasNext()) {
            Feed currentPost = post.next();
            if (currentPost.getSender().equals(loggedInUser)) {
                post.remove();
            }
        }
    }
}
