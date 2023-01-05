package info.jewelry.sqlite.database.model;


public class Material {
    public static final String TABLE_NAME = "materials";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COST = "cost";

    private int id;
    private String name;
    private int cost;

    public static final String CREATE_TABLE =
            "CREATE TABLE materials("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) UNIQUE NOT NULL,"
                    + " cost INTEGER NOT NULL"
                    + ");";

    public Material() {
    }

    public Material(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
