/*
    LLHDatabase.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.25: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1)
public abstract class LLHDatabase extends RoomDatabase {

    private static LLHDatabase instance;
    public abstract UserDao userDao();

    public static LLHDatabase getDatabase(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context, LLHDatabase.class, "LLH Database").build();
        }

        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

}