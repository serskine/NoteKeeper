package pluralsight.com.soupthatisthick.notekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NoteInfo implements Parcelable {
    private CourseInfo course;
    private String title;
    private String text;

    public NoteInfo(CourseInfo course, String title, String text) {
        this.course = course;
        this.title = title;
        this.text = text;
    }

    private NoteInfo(Parcel source) {
        course = source.readParcelable(CourseInfo.class.getClassLoader());
        title = source.readString();
        text = source.readString();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CourseInfo getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompareKey() {
        return course.getCourseId() + "|" + title + "|" + text;
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() { return getCompareKey();  }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(course, 0);
        dest.writeString(title);
        dest.writeString(text);
    }

    public final static Parcelable.Creator<NoteInfo> CREATOR = new Parcelable.Creator<NoteInfo>() {

        @Override
        public NoteInfo createFromParcel(Parcel source) {
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };
}
