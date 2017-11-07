/*
    LLHDatabase.java
    Assignment 2

    Revision History:
        Gonzalo Ramos Zúñiga, 2017.10.25: Created
 */

package ca.on.einfari.llh.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {User.class, Product.class, Quote.class, MaterialsList.class}, version = 1)
public abstract class LLHDatabase extends RoomDatabase {

    private static LLHDatabase instance;

    public abstract UserDao userDao();

    abstract ProductDao productDao();

    public abstract QuoteDao quoteDao();

    public abstract MaterialsListDao materialsListDao();

    public static LLHDatabase getDatabase(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), LLHDatabase.class,
                    "LLH Database").addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    populateDatabase(context.getApplicationContext());
                }
            }).build();
        }

        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    private static void populateDatabase(Context context) {
        new AsyncTask<Context, Void, Void>() {

            @Override
            protected Void doInBackground(Context... contexts) {
                getDatabase(contexts[0]).userDao().create(new User("admin", "admin", "admin",
                        "admin", "Male", "admin@admin.admin"));
                getDatabase(contexts[0]).productDao().create(new Product("1x6x6", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("2x4x8", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("2x4x8 Grooved", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("2x6x8", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("4x4x10", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("6x6x10", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("4x4 Post Cap", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("6x6 Post Cap", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("2x8 Lattice", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("Fence Bracket", "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("8\" Building Form",
                        "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("10\" Building Form",
                        "Piece"));
                getDatabase(contexts[0]).productDao().create(new Product("Concrete Mix", "Bag"));
                getDatabase(contexts[0]).productDao().create(new Product("1\"1/2 Galvanized Nail",
                        "Box"));
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Log.d("DB", "LLH Database created successfully.");
            }

        }.execute(context);
    }

}