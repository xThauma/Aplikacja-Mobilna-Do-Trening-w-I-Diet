package kamiltm.project_sm.diet.food;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Kamil Lenartowicz on 2017-12-29.
 */

public class Food implements Parcelable {
    private int id;
    private String name;
    private Double kcal;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
    private Double saturated_fat;
    private Double polyunsaturated_fat;
    private Double monounsaturated_fat;
    private Double sugar;
    private Double fiber;
    private Double portion;
    private Double barcode;
    private Double vitaminA;
    private Double vitaminD;
    private Double vitaminE;
    private Double vitaminK;
    private Double vitaminB6;
    private Double calcium;
    private Double iron;
    private Double magnesium;
    private Double phosphorus;
    private Double zinc;
    private Double copper;
    private Double chromium;
    private Boolean favourite;
    private Boolean isChecked;

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public static Creator<Food> getCREATOR() {
        return CREATOR;
    }

    public Double getBarcode() {
        return barcode;
    }

    public void setBarcode(Double barcode) {
        this.barcode = barcode;
    }

    public Double getPortion() {
        return portion;
    }

    public void setPortion(Double portion) {
        this.portion = portion;
    }


    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kcal=" + kcal +
                ", protein=" + protein +
                ", carbohydrates=" + carbohydrates +
                ", fat=" + fat +
                ", saturated_fat=" + saturated_fat +
                ", polyunsaturated_fat=" + polyunsaturated_fat +
                ", monounsaturated_fat=" + monounsaturated_fat +
                ", sugar=" + sugar +
                ", fiber=" + fiber +
                ", portion=" + portion +
                ", barcode=" + barcode +
                '}';
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    public Food(String name, Double kcal, Double protein, Double carbohydrates, Double fat) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Boolean favourite) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.favourite = favourite;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber, Boolean favourite) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
        this.favourite = favourite;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber, Double portion, Boolean favourite) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
        this.portion = portion;
        this.favourite = favourite;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber, Double portion, Boolean favourite, Boolean isChecked) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
        this.portion = portion;
        this.favourite = favourite;
        this.isChecked = isChecked;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
    }

    public Food(int id, String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber, Double portion) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
        this.portion = portion;
    }

    public Food(String name, Double kcal, Double protein, Double carbohydrates, Double fat, Double saturated_fat, Double polyunsaturated_fat, Double monounsaturated_fat, Double sugar, Double fiber, Double portion, Double barcode, Boolean favourite, Boolean isChecked, Double vitaminA, Double vitaminD, Double vitaminE, Double vitaminK, Double vitaminB6, Double calcium, Double iron, Double magnesium, Double phosphorus, Double zinc, Double copper, Double chromium) {
        this.name = name;
        this.kcal = kcal;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.sugar = sugar;
        this.fiber = fiber;
        this.portion = portion;
        this.barcode = barcode;
        this.favourite = favourite;
        this.isChecked = isChecked;
        this.vitaminA = vitaminA;
        this.vitaminD = vitaminD;
        this.vitaminE = vitaminE;
        this.vitaminK = vitaminK;
        this.vitaminB6 = vitaminB6;
        this.calcium = calcium;
        this.iron = iron;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.zinc = zinc;
        this.copper = copper;
        this.chromium = chromium;
    }

    public Double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(Double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public Double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(Double vitaminD) {
        this.vitaminD = vitaminD;
    }

    public Double getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(Double vitaminE) {
        this.vitaminE = vitaminE;
    }

    public Double getVitaminK() {
        return vitaminK;
    }

    public void setVitaminK(Double vitaminK) {
        this.vitaminK = vitaminK;
    }

    public Double getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(Double vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public Double getCalcium() {
        return calcium;
    }

    public void setCalcium(Double calcium) {
        this.calcium = calcium;
    }

    public Double getIron() {
        return iron;
    }

    public void setIron(Double iron) {
        this.iron = iron;
    }

    public Double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Double magnesium) {
        this.magnesium = magnesium;
    }

    public Double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getZinc() {
        return zinc;
    }

    public void setZinc(Double zinc) {
        this.zinc = zinc;
    }

    public Double getCopper() {
        return copper;
    }

    public void setCopper(Double copper) {
        this.copper = copper;
    }

    public Double getChromium() {
        return chromium;
    }

    public void setChromium(Double chromium) {
        this.chromium = chromium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSaturated_fat() {
        return saturated_fat;
    }

    public void setSaturated_fat(Double saturated_fat) {
        this.saturated_fat = saturated_fat;
    }

    public Double getPolyunsaturated_fat() {
        return polyunsaturated_fat;
    }

    public void setPolyunsaturated_fat(Double polyunsaturated_fat) {
        this.polyunsaturated_fat = polyunsaturated_fat;
    }

    public Double getMonounsaturated_fat() {
        return monounsaturated_fat;
    }

    public void setMonounsaturated_fat(Double monounsaturated_fat) {
        this.monounsaturated_fat = monounsaturated_fat;
    }

    public Double getSugar() {
        return sugar;
    }

    public void setSugar(Double sugar) {
        this.sugar = sugar;
    }

    public Double getFiber() {
        return fiber;
    }

    public void setFiber(Double fiber) {
        this.fiber = fiber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.kcal);
        dest.writeValue(this.protein);
        dest.writeValue(this.carbohydrates);
        dest.writeValue(this.fat);
        dest.writeValue(this.saturated_fat);
        dest.writeValue(this.polyunsaturated_fat);
        dest.writeValue(this.monounsaturated_fat);
        dest.writeValue(this.sugar);
        dest.writeValue(this.fiber);
        dest.writeValue(this.portion);
        dest.writeValue(this.favourite);
    }

    protected Food(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.kcal = (Double) in.readValue(Double.class.getClassLoader());
        this.protein = (Double) in.readValue(Double.class.getClassLoader());
        this.carbohydrates = (Double) in.readValue(Double.class.getClassLoader());
        this.fat = (Double) in.readValue(Double.class.getClassLoader());
        this.saturated_fat = (Double) in.readValue(Double.class.getClassLoader());
        this.polyunsaturated_fat = (Double) in.readValue(Double.class.getClassLoader());
        this.monounsaturated_fat = (Double) in.readValue(Double.class.getClassLoader());
        this.sugar = (Double) in.readValue(Double.class.getClassLoader());
        this.fiber = (Double) in.readValue(Double.class.getClassLoader());
        this.portion = (Double) in.readValue(Double.class.getClassLoader());
        this.favourite = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
