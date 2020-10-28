package com.grupo3.superflix.data.database;

import android.content.Context;

import androidx.room.Room;

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



