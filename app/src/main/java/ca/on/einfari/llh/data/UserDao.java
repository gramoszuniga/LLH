/*
    UserDao.java
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
public interface UserDao {

    @Insert
    long create(User user);

    @Query("SELECT * FROM user")
    List<User> readAll();

    @Query("SELECT * FROM user WHERE userName = :userName")
    User read(String userName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Delete
    void delete(User user);

}