package info.jewelry.sqlite.database.model;


public class JewelryMaterial {
    public static final String TABLE_NAME = "jewelry_material";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JEWELRY_ID = "jewelry_id";
    public static final String COLUMN_MATERIAL_ID = "material_id";
    public static final String COLUMN_MATERIAL_AMOUNT = "materila_amount";

    private int id;
    private String jewelryId;
    private String materialId;
    private int materilAmount;

    public static final String CREATE_TABLE =
            "CREATE TABLE jewelry_materials ("
                    + " id SERIAL PRIMARY KEY,"
                    + " FOREIGN KEY (jewelry_id)"
                    + " REFERENCES jewelries(id)"
                    + " ON DELETE CASCADE,"
                    + " FOREIGN KEY (material_id)"
                    + " REFERENCES materials(id)"
                    + " ON DELETE CASCADE,"
                    + " materila_amount INTEGER DEFAULT 0"
                    + ");";


    public JewelryMaterial() {
    }

    public JewelryMaterial(int id, String jewelryId, String materialId, int materilAmount) {
        this.id = id;
        this.jewelryId = jewelryId;
        this.materialId = materialId;
        this.materilAmount = materilAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJewelryId() {
        return jewelryId;
    }

    public void setJewelryId(String jewelryId) {
        this.jewelryId = jewelryId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public int getMaterilAmount() {
        return materilAmount;
    }

    public void setMaterilAmount(int materilAmount) {
        this.materilAmount = materilAmount;
    }
}
