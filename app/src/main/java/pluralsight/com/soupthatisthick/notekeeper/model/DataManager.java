package pluralsight.com.soupthatisthick.notekeeper.model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<CourseInfo> courses = new ArrayList<>();
    private List<NoteInfo> notes = new ArrayList<>();

    public static DataManager getInstance() {
        if (ourInstance==null) {
            ourInstance = new DataManager();
            ourInstance.initializeCourses();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Jim Wilson";
    }

    public String getCurrentUserEmail() {
        return "jimw@jwhh.com";
    }

    public List<NoteInfo> getNotes() {
        return notes;
    }

    public List<CourseInfo> getCourses() {
        return courses;
    }

    public int createNewNote() {

        final CourseInfo courseInfo = getCourses().get(RandomManager.getInstance().getInt(getCourses().size()));
        NoteInfo noteInfo = new NoteInfo(
            courseInfo,
            RandomManager.getInstance().getText("title"),
            RandomManager.getInstance().getText("text")
        );
        notes.add(noteInfo);
        return notes.size()-1;
    }


    private void initializeExampleNotes() {
        for(int i=1; i<=10; i++) {
            CourseInfo courseInfo = RandomManager.getInstance().getCourse();
            NoteInfo noteInfo = new NoteInfo(
                courseInfo,
                RandomManager.getInstance().getText("title"),
                RandomManager.getInstance().getText("text")
            );
            notes.add(noteInfo);
        }
    }

    private void initializeCourses() {
        RandomManager randomManager = RandomManager.getInstance();
        for(int i=1; i<=10; i++) {
            final String courseTitle = randomManager.getText("Course");
            final List<ModuleInfo> modules = new ArrayList<>();
            int numModules = (int) (Math.random()*5+2);

            for(int j=1; j<numModules; j++) {
                final String moduleId = randomManager.getText("id");
                final String moduleTitle = randomManager.getText(courseTitle + "title");
                ModuleInfo moduleInfo = new ModuleInfo(
                    moduleId,
                    moduleTitle,
                    false
                );
                modules.add(moduleInfo);
            }
            CourseInfo courseInfo = new CourseInfo(
                    "id 1",
                    courseTitle,
                    modules
            );
            courses.add(courseInfo);
        }
    }


}
