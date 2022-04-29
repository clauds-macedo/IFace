import java.util.*;

public class Community {

    static Map<String, ArrayList<String>> COMMUNITIES;

    AccountManagement accountManagement;
    Database database;

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    private String communityName, communityDescription;

    public Community(String communityName, String communityDescription) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.COMMUNITIES = new HashMap<String, ArrayList<String>>();
        this.accountManagement = new AccountManagement();
    }

    void createCommunity() {
        if (this.COMMUNITIES.containsKey(this.communityName)) {
            System.out.println("Essa comunidade já existe!");
            return;
        }

        System.out.println("Digite a descrição da comunidade que deseja criar.");
        String description = this.scanner.useDelimiter("\\n").next();

        this.COMMUNITIES.put(this.communityName, new ArrayList<String>());
        this.COMMUNITIES.get(this.communityName).add(this.accountManagement.getLoggedInUser()); //dono vai sempre estar na pos. 0 do array de membros
        this.COMMUNITIES.get(this.communityName).add(description);

        System.out.println("Comunidade criada!");
        return;
    }

    void showCommunities(){
        if (this.COMMUNITIES.isEmpty()) {
            System.out.println("Nenhuma comunidade criada.");
            return;
        }

        Iterator<String> COMMUNITIES= this.COMMUNITIES.keySet().iterator();
        while (COMMUNITIES.hasNext()) {
            String COMMUNITY = COMMUNITIES.next();
            int qtMembers = this.COMMUNITIES.get(COMMUNITY).size() - 1;
            System.out.println(
                    "========================================\n"
                            + "Comunidade: " + COMMUNITY + "\n"
                            + "Dono: " + this.COMMUNITIES.get(COMMUNITY).get(0) + "\n"
                            + "Qtd. membros: "  + qtMembers
            );
            if (this.COMMUNITIES.get(COMMUNITY).contains(this.accountManagement.getLoggedInUser())) {
                System.out.println("Você está nessa comunidade.\n"
                        + "========================================\n");
            }
            else {
                System.out.println("========================================");
            }
        }
    }

    void enterInCommunity(){
        String community_name = scanner.useDelimiter("\\n").next();
        if (this.COMMUNITIES.isEmpty()) return;
        else if (!this.COMMUNITIES.containsKey(community_name)) return;

        if (this.COMMUNITIES.get(community_name).contains(this.accountManagement.getLoggedInUser())) {
            System.out.println("Você já está nessa comunidade.");
            return;
        }

        this.COMMUNITIES.get(community_name).add(this.accountManagement.getLoggedInUser());
        System.out.println("Entrou na comunidade " + community_name + "com sucesso! \n");
    }



}
