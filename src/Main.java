import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Organization> orgs = new ArrayList<>();

    public static void main(String[] args) {
        loadOrganizations();
        loadNodes();
        for (int i = 0; i < orgs.size(); i++) {
            System.out.println(orgs.get(i).getName());
            System.out.println("--------------------");
            for(int j = 0; j< orgs.get(i).getNodes().size(); j++){
                System.out.println(orgs.get(i).getNodes().get(j).getName());
            }
            System.out.println();
        }
    }

    private static void loadOrganizations() {
        try {
            File file = new File("CSVOrganizations.txt");
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String str = s.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(str);
                String s1 = tokenizer.nextToken();
                Organization org = new Organization(s1);
                orgs.add(org);
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    private static void loadNodes() {
        try {
            File file = new File("contain.rsf");
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String str = s.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(str);
                tokenizer.nextToken();
                String orgName = tokenizer.nextToken();
                Node node = new Node(tokenizer.nextToken());
                findOrganization(orgName).getNodes().add(node);
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    private static Organization findOrganization(String name) {
        for (int i = 0; i < orgs.size(); i++) {
            Organization tmp = orgs.get(i);
            if(tmp.getName().equals(name))return tmp;
        }
        return null;
    }
}

