public class FriendsException extends Exception {

    public String userIsNotYourFriend() {
        return "O usuario nao eh seu amigo.";
    }

    public String emptyFriendlist() {
        return "Sua lista de amigos esta vazia.";
    }

}
