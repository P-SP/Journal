package com.example.eigenaar.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get info from main activity
        Intent intent = getIntent();
        JournalEntry clickedItem = (JournalEntry) intent.getSerializableExtra("clicked_item");

        // get places
        TextView title = findViewById(R.id.title);
        TextView content = findViewById(R.id.content);
        TextView date = findViewById(R.id.date);
        TextView mood = findViewById(R.id.mood);

        // make content scrollable
        content.setMovementMethod(new ScrollingMovementMethod());

        // put right values in it
        title.setText(clickedItem.getTitle());
        content.setText(clickedItem.getContent());
        date.setText(clickedItem.getTimestamp().toString());
        mood.setText(clickedItem.getMood());
    }
}
