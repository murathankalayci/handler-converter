import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

public class Main {
    static ArrayList<Organization> orgs = new ArrayList<>();

    public static void main(String[] args) {
        loadOrganizations();
        loadNodes();
        loadDeps();
        populate();
        printCSV();
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
                node.setParent(findOrganization(orgName));
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    private static void loadDeps() {
        try {
            File file = new File("depends.rsf");
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String str = s.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(str);
                tokenizer.nextToken();
                String firstNodeName = tokenizer.nextToken();
                String secondNodeName = tokenizer.nextToken();
                findNode(firstNodeName).getParent().getDeps().add(findNode(secondNodeName).getParent());
            }
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    private static Organization findOrganization(String name) {
        for (int i = 0; i < orgs.size(); i++) {
            Organization tmp = orgs.get(i);
            if (tmp.getName().equals(name)) return tmp;
        }
        return null;
    }

    private static Node findNode(String nodeName) {
        for (int i = 0; i < orgs.size(); i++) {
            Organization tmp = orgs.get(i);
            for (int j = 0; j < tmp.getNodes().size(); j++) {
                if (tmp.getNodes().get(j).getName().equals(nodeName)) return tmp.getNodes().get(j);
            }
        }
        return null;
    }

    private static void populate() {
        for (int i = 0; i < orgs.size(); i++) {
            orgs.get(i).populateMap();
        }
    }

    private static void printCSV() {
        try {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "CSVDepMaps.txt"));
            for (int i = 0; i < orgs.size(); i++) {
                Set set = orgs.get(i).getDepMap().entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()) {
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    out.println(orgs.get(i).getName()+" "+ ((Organization) mentry.getKey()).getName()+ " "+ mentry.getValue());
                }

            }


            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}

