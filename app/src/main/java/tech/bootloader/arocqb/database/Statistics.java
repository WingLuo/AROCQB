package tech.bootloader.arocqb.database;

import org.litepal.crud.LitePalSupport;

public class Statistics   extends LitePalSupport {
    /**
     * 开始类型 A B C
     */
    private String type;
    private int quantity;
    private int count;

    public Statistics() {
    }

    public Statistics(String type, int quantity, int count) {
        this.type = type;
        this.quantity = quantity;
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
