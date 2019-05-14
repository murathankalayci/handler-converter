public class Node {
    private String name;
    private Organization parent;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Organization getParent() {
        return parent;
    }

    public void setParent(Organization parent) {
        this.parent = parent;
    }
}
