package com.example.android.inventory;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.inventory.db.ItemContract.ItemEntry;
import com.example.android.inventory.db.ItemDbHelper;

public class MainActivity extends AppCompatActivity {

    ItemDbHelper mDbHelper = new ItemDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertData();
        queryData();
    }

    private void insertData() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemEntry.COLUMN_ITEM_NAME, "Pencil");
        values.put(ItemEntry.COLUMN_ITEM_PRICE, 25);
        values.put(ItemEntry.COLUMN_ITEM_QUANTITY, 100);
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_NAME, "GB Pencil Co.");
        values.put(ItemEntry.COLUMN_ITEM_SUPPLIER_PHONE, "(555) 555-5555");

        long newRowId = db.insert(ItemEntry.TABLE_NAME, null, values);
    }

    private void queryData() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // only getting the name, price, and quantity for this query
        String[] projection = {
                ItemEntry._ID,
                ItemEntry.COLUMN_ITEM_NAME,
                ItemEntry.COLUMN_ITEM_PRICE,
                ItemEntry.COLUMN_ITEM_QUANTITY
        };

        Cursor cursor = db.query(
                ItemEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        try {
            TextView displayInventory = findViewById(R.id.display_inventory);
            displayInventory.setText(
                            ItemEntry.COLUMN_ITEM_NAME + " " +
                            ItemEntry.COLUMN_ITEM_PRICE + " " +
                            ItemEntry.COLUMN_ITEM_QUANTITY + "\n");

            int nameColIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_NAME);
            int priceColIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_PRICE);
            int quantityColIndex = cursor.getColumnIndex(ItemEntry.COLUMN_ITEM_QUANTITY);

            while (cursor.moveToNext()) {
                String currentName = cursor.getString(nameColIndex);
                int currentPrice = cursor.getInt(priceColIndex);
                int currentQuantity = cursor.getInt(quantityColIndex);

                displayInventory.append("\n" +
                        currentName + " " +
                        currentPrice + " " +
                        currentQuantity);
            }
        } finally {
            cursor.close();
        }
    }
}