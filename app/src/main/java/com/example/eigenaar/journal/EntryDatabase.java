package com.example.eigenaar.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database for the journal.
 */

public class EntryDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Journal";
    private static final String TABLE_NAME = "entries";

    private static EntryDatabase instance;

    // constructor such that class is a Singleton
    private EntryDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    // make sure there is one database
    public static EntryDatabase getInstance(Context context) {
        if (instance == null){
            instance = new EntryDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String SQL = "CREATE TABLE" + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, " +
                "content TEXT, mood TEXT, timestamp DATETIME default current_timestamp);";
        db.execSQL(SQL);

        // sample items
        db.execSQL("INSERT INTO " + TABLE_NAME + "(title, content, mood) VALUES(\"Test 1\", \"balblalbalblal\", \":)\");");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(title, content, mood) VALUES(\"Test 2\", \"dsagewGEWg dgs awewG balblalbalblal\", \":(\");");
        db.execSQL("INSERT INTO " + TABLE_NAME + "(title, content, mood) VALUES(\"Test 3\", \"DAGEWg eWG w dsagewGEWg dgs awewG balblalbalblal\", \":|\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // drop entries table if exists
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // recreate it
        onCreate(sqLiteDatabase);
    }

    // selecting the whole database
    public Cursor selectAll() {
        return getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    // insert new journal item
    public void insert(JournalEntry newItem){

        // set the content
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", newItem.getTitle());
        contentValues.put("content", newItem.getContent());
        contentValues.put("mood", newItem.getMood());

        // open connection to database and add the new item
        getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    // delete an item
    public void delete(long anId ){

        // open connection and delete item by id (because its unique)
        getWritableDatabase().delete(TABLE_NAME, "_id = ?", new String[] {String.valueOf(anId)});
    }
}
