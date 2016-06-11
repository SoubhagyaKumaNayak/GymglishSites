package com.lfbl.gymglishsites.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class GymglishProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private GymglishDbHelper mOpenHelper;
    private ContentResolver mContentResolver;

    static final int LOGIN = 100;
    static final int WEBSITE = 101;

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(GymglishContract.CONTENT_AUTHORITY, GymglishContract.LoginEntry.TABLE_NAME, LOGIN);
        matcher.addURI(GymglishContract.CONTENT_AUTHORITY, GymglishContract.SitesEntry.TABLE_NAME, WEBSITE);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        if(getContext() != null){
            mOpenHelper = new GymglishDbHelper(getContext());
            mContentResolver = getContext().getContentResolver();
        }

        return true;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case LOGIN:
                return GymglishContract.LoginEntry.CONTENT_TYPE;
            case WEBSITE:
                return GymglishContract.SitesEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    /**
     * I'm implementing only the query because there's no need to insert/update/delete
     * since the data is inserted once on the GymglishDbHelper
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        Cursor c;

        switch (sUriMatcher.match(uri)){
            case LOGIN: {
                c = db.query(
                        GymglishContract.LoginEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case WEBSITE: {
                c = db.query(
                        GymglishContract.SitesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        c.setNotificationUri(mContentResolver, uri);
        return c;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
