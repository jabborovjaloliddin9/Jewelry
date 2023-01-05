package info.jewelry.sqlite.database.model;


public class UserSale {
    public static final String TABLE_NAME = "user_sale";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SALE_PERSENT = "sale_persent";

    private int id;
    private int userId;
    private int salePersent;

    public static final String CREATE_TABLE =
            "CREATE TABLE users_sale("
                    + " id SERIAL PRIMARY KEY,"
                    + " FOREIGN KEY (user_id)"
                    + " REFERENCES users(id)"
                    + " ON DELETE CASCADE,"
                    + " sale_persent INTEGER DEFAULT 0"
                    + ");";


    public UserSale() {
    }

    public UserSale(int id, int userId, int salePersent) {
        this.id = id;
        this.userId = userId;
        this.salePersent = salePersent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSalePersent() {
        return salePersent;
    }

    public void setSalePersent(int salePersent) {
        this.salePersent = salePersent;
    }
}
