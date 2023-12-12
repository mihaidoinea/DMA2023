package ro.ase.csie.ie.dma10;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, exportSchema = false, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    private static final String DB_NAME = "dma09_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context)
    {
        if(databaseManager == null)
        {
            synchronized (DatabaseManager.class)
            {
                databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return databaseManager;
    }

    public abstract MovieDao getMovieDao();
}
