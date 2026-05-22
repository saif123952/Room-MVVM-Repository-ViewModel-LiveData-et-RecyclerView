package com.example.lab19.persistence.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskEntry.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskAccess taskAccess();

    private static volatile AppDatabase dbInstance;

    public static AppDatabase getDatabase(final Context context) {
        if (dbInstance == null) {
            synchronized (AppDatabase.class) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "app_registry_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return dbInstance;
    }
}
