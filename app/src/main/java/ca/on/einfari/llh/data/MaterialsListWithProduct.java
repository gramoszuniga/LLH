/*
    MaterialsListWithProduct.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.26: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class MaterialsListWithProduct {

    @Embedded
    public MaterialsList materialsList;

    @Relation(parentColumn = "product", entityColumn = "id")
    public List<Product> product;

}