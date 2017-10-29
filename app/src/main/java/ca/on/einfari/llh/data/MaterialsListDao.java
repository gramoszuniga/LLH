/*
    MaterialsListDao.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.29: Created
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
public interface MaterialsListDao {

    @Insert
    long create(MaterialsList materialsList);

    @Query("SELECT * FROM materialsList")
    List<MaterialsList> readAll();

    @Query("SELECT * FROM materialsList WHERE id = :id")
    MaterialsList read(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(MaterialsList materialsList);

    @Query("DELETE FROM materialsList")
    void deleteAll();

    @Delete
    void delete(MaterialsList materialsList);

}