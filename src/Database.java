import java.util.*;

public class Database {
    public static Map<Object, ArrayList<String>> usersDatabase;
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    public Database(){
        this.usersDatabase = new HashMap<Object, ArrayList<String>>();
    }

    public void insertUserOnDatabase(User userInfo){
        if (!this.usersDatabase.containsKey(userInfo.username)){
            this.usersDatabase.put(userInfo.username, new ArrayList<String>());
        }
        this.usersDatabase.get(userInfo.username).add(userInfo.password);
        this.usersDatabase.get(userInfo.username).add(userInfo.nickname);
    }

    public void showUserDatabase(){
        System.out.println(this.usersDatabase.keySet());
    }

    boolean checkAccountExistence(String user) {
        return this.usersDatabase.containsKey(user);
    }

    boolean compareWithUserDatabase(String userLogin, String userPassword) {
        return (usersDatabase.get(userLogin).get(0).equals(userPassword));
    }
}
