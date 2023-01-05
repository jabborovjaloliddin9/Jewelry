package info.jewelry.sqlite.database.model;


public class JewelryType {
    public static final String TABLE_NAME = "jewelry_types";

    public static final String COLUMN_ID = "id";
    public static final String NAME = "name";

    private int id;
    private String name;

    public static final String CREATE_TABLE =
            "CREATE TABLE jewelry_types ("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) UNIQUE NOT NULL"
                    + ");";

    public JewelryType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public JewelryType() {
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
}
