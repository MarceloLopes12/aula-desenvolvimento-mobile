package dev.marcelo.superflix.data.database;

import android.content.Context;

import androidx.room.Room;

import dev.marcelo.superflix.data.database.AppDataBase;

public class FactoryDataBase {

    private static AppDataBase appDataBase = null;

    public static AppDataBase getInstanceAppDataBase(Context context) {

        if(appDataBase == null) {
            appDataBase = Room.databaseBuilder(context,
                    AppDataBase.class, "database-superflix").build();
        }

        return appDataBase;
    }

}



