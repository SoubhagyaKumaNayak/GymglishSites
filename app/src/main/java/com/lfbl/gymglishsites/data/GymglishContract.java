package com.lfbl.gymglishsites.data;

import android.provider.BaseColumns;

/**
 * Created by Luiz F. Lazzarin on 10/06/2016.
 * Email: lf.lazzarin@gmail.com
 * Github: /luizfelippe
 */

public class GymglishContract {

    public static final class LoginEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "login";

        // Columns
        public static final String COLUMN_USER = "user";
        public static final String COLUMN_PASS = "pass";
    }

    public static final class SitesEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "sites";

        // Columns
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESC = "desc";
        public static final String COLUMN_URL = "url";
    }

}
