package pluralsight.com.soupthatisthick.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import pluralsight.com.soupthatisthick.notekeeper.model.CourseInfo;
import pluralsight.com.soupthatisthick.notekeeper.model.DataManager;
import pluralsight.com.soupthatisthick.notekeeper.model.NoteInfo;

public class NoteActivity extends AppCompatActivity {

    public static final String NOTE_POSITION = NoteActivity.class.getSimpleName() + "_NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;

    private NoteInfo noteInfo;
    private boolean isNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // Get a reference to the tool bar
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a reference to the spinner
        final Spinner spinner = findViewById(R.id.spinner_courses);
        final List<CourseInfo> courses = DataManager.getInstance().getCourses();
        final ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            courses
        );
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCourses);

        readDisplayStateValues();

        final EditText textNoteTitle = findViewById(R.id.text_not_title);
        final EditText textNoteText = findViewById(R.id.text_note_text);

        if (!isNewNote) {
            displayNote(spinner, textNoteTitle, textNoteText);
        }
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
//        noteInfo = intent.getParcelableExtra(NOTE_POSITION);
        int notePosition = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        isNewNote = (notePosition != POSITION_NOT_SET);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courseInfos = DataManager.getInstance().getCourses();
        int courseIndex = courseInfos.indexOf(noteInfo.getCourse());
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(noteInfo.getTitle());
        textNoteText.setText(noteInfo.getText());
    }
}
