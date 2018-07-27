package com.example.android.inventory.db;

import android.provider.BaseColumns;

/**
 * Created by gbolatto on 7/27/2018.
 */
public class ItemContract {

    public static abstract class ItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "items";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ITEM_NAME = "name";
        public static final String COLUMN_ITEM_PRICE = "price";
        public static final String COLUMN_ITEM_QUANTITY = "quantity";
        public static final String COLUMN_ITEM_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_ITEM_SUPPLIER_PHONE = "supplier_phone";
    }
}