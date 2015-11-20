package my.android.datapersistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ztxs on 15-11-20.
 */
public class WebSiteDB extends SQLiteOpenHelper {
    private static String DB_NAME="website.db";
    public WebSiteDB(Context context, int version) {
        super(context, DB_NAME, null, version);
    }

    private static String CREATE_WEBSITE="create table website("
            +"id integer primary key autoincrement"
            +",name text"
            +",url text"
            +")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WEBSITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(newVersion){

        }
    }
}
