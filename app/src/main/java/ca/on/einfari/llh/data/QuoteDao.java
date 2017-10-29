/*
    QuoteDao.java
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
public interface QuoteDao {

    @Insert
    long create(Quote quote);

    @Query("SELECT * FROM quote")
    List<Quote> readAll();

    @Query("SELECT * FROM quote WHERE id = :id")
    Quote read(int id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Quote quote);

    @Query("DELETE FROM quote")
    void deleteAll();

    @Delete
    void delete(Quote quote);

}