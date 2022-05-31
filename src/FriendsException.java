public class FriendsException extends Exception {

    public String userIsNotYourFriend() {
        return "O usuário não é seu amigo.";
    }

    public String emptyFriendlist() {
        return "Sua lista de amigos esta vazia.";
    }

}
