package com.lfbl.gymglishsites.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lfbl.gymglishsites.data.GymglishContract.*;
import com.lfbl.gymglishsites.model.SiteModel;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class GymglishDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gymglishsites.db";

    public GymglishDbHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " + LoginEntry.TABLE_NAME + " (" +
                LoginEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                LoginEntry.COLUMN_USER + " TEXT NOT NULL, " +
                LoginEntry.COLUMN_PASS + " TEXT NOT NULL, " +
                " UNIQUE (" + LoginEntry.COLUMN_USER + "));";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);

        final String SQL_CREATE_SITES_TABLE = "CREATE TABLE " + SitesEntry.TABLE_NAME + " (" +
                SitesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SitesEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                SitesEntry.COLUMN_DESC + " TEXT NOT NULL, " +
                SitesEntry.COLUMN_URL + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_SITES_TABLE);

        // Insert default login data
        db.insert(LoginEntry.TABLE_NAME, null, getContentDefaultLogin());

        // Insert sites data
        db.beginTransaction();
        try {
            for (ContentValues value : getContentGymglishSites()) {
                db.insert(SitesEntry.TABLE_NAME, null, value);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LoginEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SitesEntry.TABLE_NAME);
        onCreate(db);
    }

    private ContentValues getContentDefaultLogin() {
        ContentValues values = new ContentValues();
        values.put(LoginEntry.COLUMN_USER, "admin");
        values.put(LoginEntry.COLUMN_PASS, "123");

        return values;
    }

    private ContentValues[] getContentGymglishSites() {
        final SiteModel[] SITES = new SiteModel[]{
                new SiteModel("Gymglish", "", "gymglish.com"),
                new SiteModel("Frantastique", "", "frantastique.com"),
                new SiteModel("VaTeFaire Conjuguer", "", "vatefaireconjuguer.com"),
                new SiteModel("The Word of the Month", "", "thewordofthemonth.com"),
                new SiteModel("Rich Morning", "", "richmorning.com"),
                new SiteModel("The Delavigne Corporation", "", "delavignecorp.com"),
                new SiteModel("Comment Utiliser son CPF", "", "comment-utiliser-son-cpf.fr"),
                new SiteModel("Anglais Conjugaison", "", "anglais-conjugaison.com"),
                new SiteModel("CPF Anglais & Gymglish", "", "anglais-cpf.fr"),
                new SiteModel("Gymglish Twitter", "", "twitter.com/GymGlish"),
                new SiteModel("Gymglish Google+", "", "plus.google.com/+Gymglisha9"),
                new SiteModel("Gymglish Facebook", "", "m.facebook.com/gymglish"),
                new SiteModel("Gymglish Instagram", "", "instagram.com/gymglish")
        };

        ContentValues[] valuesArr = new ContentValues[SITES.length];

        for (int i = 0; i < SITES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(SitesEntry.COLUMN_NAME, SITES[i].getName());
            values.put(SitesEntry.COLUMN_DESC, SITES[i].getDescription());
            values.put(SitesEntry.COLUMN_URL, SITES[i].getUrl());

            valuesArr[i] = values;
        }

        return valuesArr;
    }


}
