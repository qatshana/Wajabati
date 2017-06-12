package com.judeventures.customer.wajabati;

/**
 * Created by Abdel on 5/19/2017.
 */

public class MenueItem {
    private String itemName;
    private String desc;
    private String price;
    private String category;

    public String getItemName() {
        return itemName;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public MenueItem(String itemName, String desc, String price, String category) {
        this.itemName = itemName;
        this.desc = desc;
        this.price = price;
        this.category = category;
    }

}
