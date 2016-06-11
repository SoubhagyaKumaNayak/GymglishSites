package com.lfbl.gymglishsites.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class DatabaseTest extends AndroidTestCase {

    private static final String LOG_TAG = DatabaseTest.class.getSimpleName();

    public void setUp() throws Exception {
        assertNotNull(mContext);

        mContext.deleteDatabase(GymglishDbHelper.DATABASE_NAME);
    }

    public void testDatabase() {
        SQLiteDatabase db = new GymglishDbHelper(mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly", c.moveToFirst());

        c = db.query(GymglishContract.SitesEntry.TABLE_NAME, null, null, null, null, null, null);

        assertTrue(c.getCount() > 0);

        while (c.moveToNext()) {
            String name = c.getString(1);
            String desc = c.getString(2);
            String url = c.getString(3);
            Log.i(LOG_TAG, "Name: " + name + " | Desc: " + desc + " | Url: " + url);
        }

        c.close();
    }

}
