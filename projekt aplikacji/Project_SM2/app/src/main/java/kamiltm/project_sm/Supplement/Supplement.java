package kamiltm.project_sm.Supplement;

import android.os.Parcel;
import android.os.Parcelable;

public class Supplement implements Parcelable {
    String title;
    String desc;
    int resource;

    public Supplement(String title, String desc, int resource) {
        this.title = title;
        this.desc = desc;
        this.resource = resource;
    }


    @Override
    public String toString() {
        return "Supplement{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", resource=" + resource +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeInt(this.resource);
    }

    protected Supplement(Parcel in) {
        this.title = in.readString();
        this.desc = in.readString();
        this.resource = in.readInt();
    }

    public static final Parcelable.Creator<Supplement> CREATOR = new Parcelable.Creator<Supplement>() {
        @Override
        public Supplement createFromParcel(Parcel source) {
            return new Supplement(source);
        }

        @Override
        public Supplement[] newArray(int size) {
            return new Supplement[size];
        }
    };
}
