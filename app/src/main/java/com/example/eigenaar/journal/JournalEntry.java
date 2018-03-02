package com.example.eigenaar.journal;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class for a journal item.
 */

public class JournalEntry implements Serializable {

    // properties
    private String title, content, mood;
    private Timestamp timestamp;

    // constructor before the item is in the database
    public JournalEntry(String aTitle, String aContent, String aMood) {
        title = aTitle;
        content = aContent;
        mood = aMood;
    }

    // constructor after the item is in the database
    public JournalEntry(String aTitle, String aContent, String aMood, Timestamp aTimestamp) {
        title = aTitle;
        content = aContent;
        mood = aMood;
        timestamp = aTimestamp;
    }

    // getter functions for all fields
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    // setter functions for all fields (specific exercise so I didn't delete the ones I don't use)
    public void setTitle(String aTitle) {
        title = aTitle;
    }

    public void setContent(String aContent) {
        content = aContent;
    }

    public void setMood(String aMood) {
        mood = aMood;
    }

    public void setTimestamp(Timestamp aTimestamp) {
        timestamp = aTimestamp;
    }
}
