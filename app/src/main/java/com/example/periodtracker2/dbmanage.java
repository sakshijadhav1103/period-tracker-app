package com.example.periodtracker2;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class dbmanage extends SQLiteOpenHelper
{
    private static final String dbname="period.db";
    public dbmanage( Context context)
    {
        super(context,dbname , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String qry="create table perioddb(id integer primary key autoincrement, start_date Date, end_date Date)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS perioddb");
        onCreate(db);
    }
    public String addrec(Date p1, Date p2)
    {
        try
        {
            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues cv= new ContentValues();
            cv.put("start_date",p1.getTime());
            cv.put("end_date",p2.getTime());
            long res=db.insert("perioddb",null,cv);
            if (res==-1)
                return "Failed";
            else
                return "successfully inserted";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Failed: " + e.getMessage();
        }
    }
}
