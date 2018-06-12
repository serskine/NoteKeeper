package pluralsight.com.soupthatisthick.notekeeper.model;

public class RandomManager {
    private static RandomManager ourInstance = null;

    public static RandomManager getInstance() {
        return ourInstance;
    }

    public int getInt(int range) {
        return getInt(0, range);
    }

    public int getInt(int min, int max) {
        return (int) (min + Math.random() * (max-min));
    }

    public String getText(String prefix) {
        int i = 1 + getInt(100);
        return prefix + "(" + i + ")";
    }

    public CourseInfo getCourse() {
        int index = getInt(DataManager.getInstance().getCourses().size());
        CourseInfo courseInfo = DataManager.getInstance().getCourses().get(index);
        return courseInfo;
    }

    public NoteInfo getNote() {
        int index = getInt(DataManager.getInstance().getNotes().size());
        NoteInfo noteInfo = DataManager.getInstance().getNotes().get(index);
        return noteInfo;
    }

}
