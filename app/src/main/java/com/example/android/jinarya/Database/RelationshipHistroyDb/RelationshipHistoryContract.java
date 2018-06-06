package com.example.android.jinarya.Database.RelationshipHistroyDb;

import android.provider.BaseColumns;

/**
 * Created by Aadhyamo on 28/01/18.
 */

public class RelationshipHistoryContract {

    public RelationshipHistoryContract() {

    }

    public static final class RelationshipHistoryEntry implements BaseColumns {

        public static final String TABLE_NAME = "relationshipHistory";

        public static final String COLUMN_OWNER_NAME = "userOneName";

        public static final String COLUMN_OWNER_AGE = "userOneAge";

        public static final String COLUMN_OWNER_GENDER = "userOneGender";

        public static final String COLUMN_GUEST_NAME = "userTwoName";

        public static final String COLUMN_GUEST_AGE = "userTwoAge";

        public static final String COLUMN_GUEST_GENDER = "userTwoGender";

        public static final String COLUMN_RELATIONSHIP_HISTORY = "knowEachOtherSince";

    }

}
