package pluralsight.com.soupthatisthick.notekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModuleInfo implements Parcelable {
    private boolean complete;
    private String title;
    private String moduleId;

    public ModuleInfo(final String moduleId, String title, boolean isComplete) {
        this.moduleId = moduleId;
        this.title = title;
        this.complete = isComplete;
    }

    private ModuleInfo(Parcel in) {
        moduleId = in.readString();
        title = in.readString();
        complete = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(moduleId);
        dest.writeString(title);
        dest.writeByte((byte) (complete ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModuleInfo> CREATOR = new Creator<ModuleInfo>() {
        @Override
        public ModuleInfo createFromParcel(Parcel in) {
            return new ModuleInfo(in);
        }

        @Override
        public ModuleInfo[] newArray(int size) {
            return new ModuleInfo[size];
        }
    };

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }
}
