import java.util.*;

public class Community {

    static Map<String, ArrayList<String>> COMMUNITIES = new HashMap<String, ArrayList<String>>();;
    AccountManagement accountManagement = new AccountManagement();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    private String communityName, communityDescription;

    public Community(){}
    public Community(String communityName, String communityDescription) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
    }

    void insertCommunityOnList(Community newCommunity) {
        if (COMMUNITIES.containsKey(newCommunity.communityName)) {
            System.out.println("Essa comunidade já existe!");
            return;
        }

        COMMUNITIES.put(newCommunity.communityName, new ArrayList<String>());
        COMMUNITIES.get(newCommunity.communityName).add(accountManagement.getLoggedInUser()); //dono vai sempre estar na pos. 0 do array de membros
        COMMUNITIES.get(newCommunity.communityName).add(newCommunity.communityDescription);

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

    void enterInCommunity() {
        System.out.println("Digite o nome da comunidade na qual deseja entrar: ");
        String community_name = scanner.useDelimiter("\\n").next();
        if (COMMUNITIES.isEmpty()) return;
        else if (!COMMUNITIES.containsKey(community_name)) {
            System.out.println("A comunidade não existe.");
            return;
        }

        if (COMMUNITIES.get(community_name).contains(this.accountManagement.getLoggedInUser())) {
            System.out.println("Você já está nessa comunidade.");
            return;
        }

        COMMUNITIES.get(community_name).add(this.accountManagement.getLoggedInUser());
        System.out.println("Entrou na comunidade " + community_name + " com sucesso! \n");
    }
}
