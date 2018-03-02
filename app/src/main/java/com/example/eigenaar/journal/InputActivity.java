package com.example.eigenaar.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Handler;

public class InputActivity extends AppCompatActivity {

    // a delay is needed between submitting and going to the main screen
    Handler setDelay;
    Runnable startDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // initialize sleeper
        setDelay = new Handler();
    }

    public void addEntry(View view) {

        // get database instance
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        // insert the new item
        EditText titleView = findViewById(R.id.newTitle);
        String title = titleView.getText().toString();
        EditText contentView = findViewById(R.id.newContent);
        String content = contentView.getText().toString();
        EditText moodView = findViewById(R.id.newMood);
        String mood = moodView.getText().toString();
        db.insert(new JournalEntry(title, content, mood));

        // Show the user that it is done and go back to the main screen
        Toast.makeText(this, "Your new item has been created!", Toast.LENGTH_SHORT).show();
        startDelay = new Runnable() {
            @Override
            public void run() {

                // go to main activity
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        setDelay.postDelayed(startDelay, 100);
    }
}
