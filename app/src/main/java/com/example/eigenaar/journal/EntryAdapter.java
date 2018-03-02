package com.example.eigenaar.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Adapter for entries.
 */

public class EntryAdapter extends ResourceCursorAdapter {

    // constructor
    public EntryAdapter(Context context, Cursor cursor){
        super(context, R.layout.entry_row, cursor);
    }

    public void bindView(View view, Context context, Cursor cursor){

        // title
        int titleIndexCursor =  cursor.getColumnIndex("title");
        String titleCursor = cursor.getString(titleIndexCursor);
        TextView title = view.findViewById(R.id.entryTitle);
        title.setText(titleCursor);

        // mood
        int moodIndexCursor =  cursor.getColumnIndex("mood");
        String moodCursor = cursor.getString(moodIndexCursor);
        TextView mood = view.findViewById(R.id.entryMood);
        mood.setText(moodCursor);

        // date
        int dateIndexCursor =  cursor.getColumnIndex("timestamp");
        String dateCursor = cursor.getString(dateIndexCursor);
        TextView date = view.findViewById(R.id.entryDate);
        date.setText(dateCursor);
    }
}
