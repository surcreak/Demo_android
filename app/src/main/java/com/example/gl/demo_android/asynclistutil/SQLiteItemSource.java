package com.example.gl.demo_android.asynclistutil;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gl.demo_android.utils.DemoLog;

public class SQLiteItemSource implements ItemSource {

    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;

    public SQLiteItemSource(SQLiteDatabase sqLiteDatabase) {
        if (sqLiteDatabase == null) {
            DemoLog.AsyncListUtilLog("sqLiteDatabase is null");
        }
        this.sqLiteDatabase = sqLiteDatabase;
        if (cursor == null || cursor.isClosed()) {
            cursor = sqLiteDatabase.rawQuery("SELECT id, title, content FROM data", null);
        }
    }

    @Override
    public int getCount() {
        DemoLog.AsyncListUtilLog("cursor.getCount()="+cursor.getCount());
        return cursor.getCount();
    }

    @Override
    public Item getItem(int positiion) {
        cursor.moveToPosition(positiion);
        return getItem(cursor);
    }

    private Item getItem(Cursor cursor) {
        Item item = new Item(cursor.getString(0),
                cursor.getString(1), cursor.getString(2));
        DemoLog.AsyncListUtilLog("getItem="+item);
        return item;
    }

    @Override
    public void close() {
        cursor.close();
    }
}
