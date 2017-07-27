package com.example.android.eventcountdown;

import android.provider.BaseColumns;

/**
 * Created by WanChing on 27/7/2017.
 */

public class EventContract {

    public EventContract(){}

    public static class EventEntry implements BaseColumns{
        public static final String TABLE_NAME="event";
        public static final String COLUMN_NAME_TITLE="title";
        public static final String COLUMN_NAME_DESCRIPTION="description";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_NOTIFY="notify";

    }
}
