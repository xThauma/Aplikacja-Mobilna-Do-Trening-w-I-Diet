package kamiltm.project_sm.diet.food;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Meal implements Parcelable {
    List<Food> foodList;
    String name;

    public Meal(List<Food> foodList, String name) {
        if (foodList.isEmpty()) throw new NullPointerException("No elements in list.");
        this.foodList = foodList;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "foodList=" + foodList +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<Meal> getCREATOR() {
        return CREATOR;
    }

    public Meal(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Meal() {
        foodList = new ArrayList<>();
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.foodList);
    }

    protected Meal(Parcel in) {
        this.foodList = in.createTypedArrayList(Food.CREATOR);
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel source) {
            return new Meal(source);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}
