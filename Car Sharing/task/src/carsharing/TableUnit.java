package carsharing;

public class TableUnit {
    private final int id;
    private final String name;
    private final int referenceID;

    public TableUnit(int id, String name, int referenceID) {
        this.id = id;
        this.name = name;
        this.referenceID = referenceID;
    }

    public TableUnit(int id, String name) {
        this(id, name, 0);
    }

    public int getReferenceID() {
        return referenceID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + ". " + name;
    }
}
