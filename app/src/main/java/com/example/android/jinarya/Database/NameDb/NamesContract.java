package com.example.android.jinarya.Database.NameDb;

import android.provider.BaseColumns;

/**
 * Created by Aadhyamo on 27/01/18.
 */

public class NamesContract {

    public NamesContract() {

    }

    public static final class NamesEntry implements BaseColumns {

        public static final String TABLE_NAME = "names";

        public static final String COLUMN_NAME = "Name";

        public static final String COLUMN_AGE = "age";

        public static final String COLUMN_GENDER = "gender";

    }

}

