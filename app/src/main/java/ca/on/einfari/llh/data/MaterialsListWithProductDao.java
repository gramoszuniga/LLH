/*
    MaterialsListWithProductDao.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.11.26: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

@Dao
public interface MaterialsListWithProductDao {

    @Query("SELECT * FROM materialsList WHERE quote = :id")
    @Transaction
    public List<MaterialsListWithProduct> readAll(int id);

}