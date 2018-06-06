package com.example.android.jinarya.Database.ReportsDb;

import android.provider.BaseColumns;

/**
 * Created by Aadhyamo on 19/12/17.
 */

public class ReportsContract {

    public ReportsContract() {

    }

    public static final class ReportsEntry implements BaseColumns {

        public static final String TABLE_NAME = "reports";

        public static final String COLUMN_OWNER_NAME = "ownerName";

        public static final String COLUMN_OWNER_AGE = "ownerAge";

        public static final String COLUMN_OWNER_GENDER = "ownerGender";

        public static final String COLUMN_GUEST_NAME = "guestName";

        public static final String COLUMN_GUEST_AGE = "guestAge";

        public static final String COLUMN_GUEST_GENDER = "guestGender";

        public static final String COLUMN_RELATIONSHIP_HISTORY = "relationshipHistory";

        public static final String COLUMN_TEST_TYPE = "testType";

        public static final String COLUMN_REPORT_INSIGHTS_FOR_OWNER = "ownerInsights";

        public static final String COLUMN_REPORT_INSIGHTS_FOR_GUEST = "guestInsights";

        public static final String COLUMN_COMPATIBILITY_SCORE = "score";

        public static final String COLUMN_COMPATIBILITY_SCORE_FEEDBACK = "compatibilityScoreFeedback";

        public static final String COLUMN_REPORT_ADVICE_FOR_OWNER = "ownerAdvice";

        public static final String COLUMN_REPORT_ADVICE_FOR_GUEST = "guestAdvice";

        public static final String COLUMN_TIMESTAMP = "timeStamp";

    }

}
