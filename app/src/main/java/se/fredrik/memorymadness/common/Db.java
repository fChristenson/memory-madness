package se.fredrik.memorymadness.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fredrik on 2016-11-05.
 */

public class Db extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "memory_madness";
    private static final int DATABASE_VERSION = 1;

    private static final String SCORES_TABLE_NAME = "scores";
    private static final String ID = "_id";
    private static final String SCORE = "score";

    private static final String CREATE_SCORES_TABLE =
            "CREATE TABLE " + SCORES_TABLE_NAME + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    SCORE + " INTEGER NOT NULL);";

    private static final String GREATEST_SCORE_QUERY = "SELECT score FROM "
            + SCORES_TABLE_NAME
            + " ORDER BY " + SCORE + " DESC LIMIT 1";

    public Db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean insertScore(int score) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SCORE, score);
        db.insert(SCORES_TABLE_NAME, null, values);
        return true;
    }

    public int getBestScore() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(GREATEST_SCORE_QUERY, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return -1;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SCORES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
