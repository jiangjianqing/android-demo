package my.android.fragment.website;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cz_jjq.baselibrary.util.LogUtil;
import com.example.cz_jjq.baselibrary.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.android.datapersistence.WebSiteDB;

/**
 * Helper class for providing sample url for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class WebSiteContent {

    private static WebSiteDB webSiteDB;
    private static SQLiteDatabase db;
    /**
     * An array of sample (dummy) items.
     */
    public static List<WebSiteItem> ITEMS = new ArrayList<WebSiteItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, WebSiteItem> ITEM_MAP = new HashMap<String, WebSiteItem>();

    static {
        initDatabase();



        // Add 3 sample items.
        if(ITEMS.size()==0) {
            addItem(new WebSiteItem("百度", "http://www.baidu.com"));
            addItem(new WebSiteItem("2", "Item 2"));
            addItem(new WebSiteItem("3", "Item 3"));
        }
    }

    private static void addItem(WebSiteItem item) {
        ContentValues values=new ContentValues();
        values.put("name",item.name);
        values.put("url",item.url);
        db.beginTransaction();
        try{
            db.insert("website",null,values);
            ITEMS.add(item);
            ITEM_MAP.put(item.name, item);
            db.setTransactionSuccessful();//事务成功
        }catch (Exception e){
            LogUtil.e("WebSiteContent", e.getMessage());
        }finally {
            db.endTransaction();
        }
    }

    private static void initDatabase(){
        webSiteDB=new WebSiteDB(MyApplication.getContext(),1);
        db=webSiteDB.getWritableDatabase();

        Cursor cursor=db.query("website",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                WebSiteItem item=new WebSiteItem("","");
                item.name=cursor.getString(cursor.getColumnIndex("name"));
                item.url=cursor.getString(cursor.getColumnIndex("url"));

                ITEMS.add(item);
                ITEM_MAP.put(item.name, item);
            }while (cursor.moveToNext());
        }
        cursor.close();

        //db.query("website",)
    }

    /**
     * A dummy item representing a piece of url.
     */
    public static class WebSiteItem {
        public String name;
        public String url;

        public WebSiteItem(String name, String url) {
            this.name = name;
            this.url = url;
        }

        @Override
        public String toString() {
            return url;
        }
    }

}
