import java.lang.reflect.Array;
import java.util.*;

public class Database {
    public static Map<Object, ArrayList<String>> usersDatabase;

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

    public void changeAccountInfo(String username, int index, String newInfo){
        String[] newUserData = {this.usersDatabase.get(username).get(0),
                this.usersDatabase.get(username).get(1)};
        newUserData[index - 1] = newInfo;

        ArrayList<String> wrapNewData = new ArrayList<>();

        wrapNewData.add(newUserData[0]);
        wrapNewData.add(newUserData[1]);

        this.usersDatabase.put(username, wrapNewData);
        System.out.println("Dado alterado com sucesso.");
    }

    boolean checkAccountExistence(String user) {
        return this.usersDatabase.containsKey(user);
    }

    boolean compareWithUserDatabase(String userLogin, String userPassword){
        return (usersDatabase.get(userLogin).get(0).equals(userPassword));
    }
}
