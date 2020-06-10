package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating intent without intent extra
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDispayContent();

    }

    private void initializeDispayContent() {
        // get reference to the list view
        final ListView listNotes = findViewById(R.id.list_notes);

        // get list of notes using the data manager reference
        List<NoteInfo> notes = DataManager.getInstance().getNotes();

        // adapter to manage the list of notes
        ArrayAdapter<NoteInfo>  adapterNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        // populate adapter
        listNotes.setAdapter(adapterNotes);

        // handling the user's selections i.e. item click
        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // the body of this method get called each time a user makes a selection from a list view
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //NoteActivity.class is the class we intend to open when a user clicks a note (pass an intent)
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
                // adding intent extra on click
//                NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);

                startActivity(intent);
            }
        });
    }

}
