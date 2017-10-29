/*
    ProductDao.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.25: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    long create(Product product);

    @Query("SELECT * FROM product")
    List<Product> readAll();

    @Query("SELECT * FROM product WHERE id = :id")
    Product read(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Product product);

    @Query("DELETE FROM product")
    void deleteAll();

    @Delete
    void delete(Product product);

}