package myapp.is.com.todo;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

//Here We are creating the DataBase(SQLite) of the Room.

@Database(entities = {ListEntity.class}, version = 1, exportSchema = false)
public abstract class ListDatabase extends RoomDatabase {
    private static volatile ListDatabase instance;    //We create it because we want to make single instance of the class which is

    // used by our whole app.
    //we can not make more than one instance that is why it is called singalton.
    public abstract ListDAO ListDAO();

     static  ListDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (ListDatabase.class) {
                if (instance == null) {       //Here we are creating instance of database if databse instance is null.
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ListDatabase.class, "list_database")
                            .fallbackToDestructiveMigration()//this will help if we change the version no. .
                            .addCallback(sRoomDatabaseCallBack).build();
                }
            }

        }
        return instance;
    }
    //Here is the code for creating the callback

    private static RoomDatabase.Callback sRoomDatabaseCallBack =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(instance).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final ListDAO mDao;

        PopulateDbAsync(ListDatabase db) {
            mDao = db.ListDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

    }
}
