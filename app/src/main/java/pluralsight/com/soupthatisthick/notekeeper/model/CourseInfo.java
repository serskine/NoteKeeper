package pluralsight.com.soupthatisthick.notekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseInfo implements Parcelable {
    private final String courseId;
    private final String title;
    private final List<ModuleInfo> modules;

    public CourseInfo(final String courseId, final String title, final List<ModuleInfo> modules) {
        this.courseId = courseId;
        this.title = title;
        this.modules = modules;
    }

    protected CourseInfo(Parcel in) {

        courseId = in.readString();
        title = in.readString();
        modules = in.readArrayList(ModuleInfo.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseId);
        dest.writeString(title);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public List<ModuleInfo> getModules() {
        return modules;
    }

    public boolean[] getModulesCompletionStatus() {
        boolean[] status = new boolean[modules.size()];

        for(int i=0; i<modules.size(); i++) {
            status[i] = modules.get(i).isComplete();
        }

        return status;
    }

    public void setModulesCompletionStatus(boolean[] status) {
        for(int i=0; i<modules.size(); i++) {
            modules.get(i).setComplete(status[i]);
        }
    }

    public ModuleInfo getModule(String moduleId) {
        for(ModuleInfo moduleInfo : modules) {
            if (moduleId.equals(moduleInfo.getModuleId())) {
                return moduleInfo;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.title;
    }

    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }
        if (o==null || getClass() != o.getClass()) {
            return false;
        }
        CourseInfo that = (CourseInfo) o;
        return courseId.equals(that.getCourseId());
    }
}
