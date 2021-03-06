package com.lfbl.gymglishsites.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class GymglishContract {

    public static final String CONTENT_AUTHORITY = "com.lfbl.gymglishsites";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class LoginEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "login";

        // Columns
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_PASS = "pass";

        // Content Provider
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    }

    public static final class SitesEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "sites";

        // Columns
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_URL = "url";

        // Content Provider
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_NAME).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_NAME;
    }

}
