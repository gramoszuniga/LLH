/*
    MaterialsList.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.29: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Quote.class, parentColumns = "id",
        childColumns = "quote", onDelete = ForeignKey.CASCADE), @ForeignKey(entity = Product.class,
                parentColumns = "id", childColumns = "product", onDelete = ForeignKey.CASCADE)})
public class MaterialsList {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int quote;
    private int product;
    private int quantity;

    public MaterialsList(int quote, int product, int quantity) {
        this.quote = quote;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}