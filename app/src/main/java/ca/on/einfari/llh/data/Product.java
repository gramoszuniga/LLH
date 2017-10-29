/*
    Product.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.27: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private String unit;

    public Product(String description, String unit) {
        this.description = description;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}