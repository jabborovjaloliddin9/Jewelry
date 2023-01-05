package info.jewelry.sqlite.database.model;


public class Jewelry {
    public static final String TABLE_NAME = "jewelries";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String TYPE_ID = "type_id";

    private int id;
    private String name;
    private int typeId;

    public static final String CREATE_TABLE =
            "CREATE TABLE jewelries ("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) NOT NULL UNIQUE,"
                    + " FOREIGN KEY (type_id) "
                    + " REFERENCES jewelry_types(id)"
                    + " ON DELETE CASCADE "
                    + ");";

    public Jewelry() {
    }

    public Jewelry(int id, String name, int typeId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
