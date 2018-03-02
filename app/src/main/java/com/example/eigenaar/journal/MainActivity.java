package com.example.eigenaar.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;
    ListView journalList;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = EntryDatabase.getInstance(getApplicationContext());
        journalList = findViewById(R.id.listViewJournal);

        // instantiate and attach adapter to ListView
        adapter = new EntryAdapter(this, db.selectAll());
        journalList.setAdapter(adapter);

        // connect listener to listView
        journalList.setOnItemClickListener(new ListItemClickListener());
        journalList.setOnItemLongClickListener (new ListItemLongClickListener());
    }

    @Override
    protected void onResume(){
        super.onResume();

        // update the main activity
        updateData();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the current position
        outState.putInt("position", position);

    }

    public void onRestoreInstanceState(Bundle inState){
        super.onRestoreInstanceState(inState);

        // select the old position again
        int oldPosition = inState.getInt("position");

        // select the old position and scroll back to it
        journalList.setSelection(oldPosition);
        journalList.smoothScrollToPosition(oldPosition);
    }

    // show the item when it is clicked on
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            // make sure position is remembered in case of rotation
            position = i;

            // make a JournalEntry
            Cursor clicked = (Cursor) adapterView.getItemAtPosition(i);
            String title = clicked.getString(clicked.getColumnIndex("title"));
            String content = clicked.getString(clicked.getColumnIndex("content"));
            String mood = clicked.getString(clicked.getColumnIndex("mood"));
            String timestamp = clicked.getString(clicked.getColumnIndex("timestamp"));
            JournalEntry clickedItem = new JournalEntry(title, content, mood, Timestamp.valueOf(timestamp));

            // pass information to the next activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("clicked_item", clickedItem);
            startActivity(intent);
        }
    }

    // when an item is clicked on for a long time, delete that item
    private class ListItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

            // get the id of the current item
            Cursor clickedItem = (Cursor) adapterView.getItemAtPosition(i);
            long id = clickedItem.getInt(clickedItem.getColumnIndex("_id"));

            // delete the item & update the main activity
            db.delete(id);
            updateData();
            Toast.makeText(MainActivity.this, "The selected item is deleted.", Toast.LENGTH_SHORT).show();

            // event is handled with
            return true;
        }
    }

    // make sure interface is up to date
    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    // if the user clicked on the plus sign
    public void newItem(View view) {

        // go to next activity
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }
}
