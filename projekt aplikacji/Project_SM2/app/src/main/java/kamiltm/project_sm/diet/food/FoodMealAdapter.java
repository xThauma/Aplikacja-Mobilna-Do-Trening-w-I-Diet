package kamiltm.project_sm.diet.food;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class FoodMealAdapter extends RecyclerView.Adapter<FoodMealAdapter.MyViewHolder> {

    private List<Food> foodList;
    private ProgressDialog progressDialog;
    private Date datee;
    private String date;
    private String portionOfFood;
    private static final String FOOD_OBJECT = "food";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, calories, proteins, carbs, fats;
        public ImageButton mealCB;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.foodNameTVMeal);
            calories = view.findViewById(R.id.foodContentCaloriesMeal);
            proteins = view.findViewById(R.id.foodContentProteinsMeal);
            fats = view.findViewById(R.id.foodContentFatsMeal);
            carbs = view.findViewById(R.id.foodContentCarbsMeal);
            mealCB = view.findViewById(R.id.mealCB);
        }
    }


    public FoodMealAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    public String stringFormat(Double value, Double portion) {
        if (portion != 0) {
            value *= portion;
        }
        String pattern = "##0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(value);
        return format;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meal_add_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Food food = foodList.get(position);
        Double multiplier = food.getPortion();
        if (multiplier == null) {
            multiplier = 1.0;
        }
        food.setPortion(1.0);

        Double portion = multiplier * 100;
        String titleWithGrams = food.getName() + " - " + portion + " " + holder.itemView.getResources().getString(R.string.grams);


        String kcals = holder.itemView.getResources().getString(R.string.kcals);
        String proteins = holder.itemView.getResources().getString(R.string.proteins);
        String carbs = holder.itemView.getResources().getString(R.string.carbohydrates);
        String fats = holder.itemView.getResources().getString(R.string.fats);

        Double d_kcals = food.getKcal() * multiplier;
        Double d_proteins = food.getProtein() * multiplier;
        Double d_carbs = food.getCarbohydrates() * multiplier;
        Double d_fats = food.getFat() * multiplier;

        String message = kcals + " " + d_kcals + "g, " + proteins + " " + d_proteins + "g, " + carbs + " " + d_carbs + "g, " + fats + " " + d_fats + "g";


        holder.title.setOnClickListener(e -> {
            Intent intent = new Intent(holder.itemView.getContext(), WholeInfoActivity.class);
            intent.putExtra(FoodAdapter.FOOD_OBJECT2, food);
            Log.d("Food", food.toString());
            ((Activity) holder.itemView.getContext()).startActivity(intent);
        });


        holder.mealCB.setOnClickListener(e -> {
            if (!food.getChecked()) {
                holder.mealCB.setImageResource(R.drawable.ic_check_box_black);
            } else {
                holder.mealCB.setImageResource(R.drawable.ic_check_box_empty);
            }
            food.setChecked(!food.getChecked());
        });


        holder.calories.setText(stringFormat(food.getKcal(), food.getPortion()));
        holder.title.setText(food.getName());
        holder.carbs.setText(stringFormat(food.getCarbohydrates(), food.getPortion()));
        holder.fats.setText(stringFormat(food.getFat(), food.getPortion()));
        holder.proteins.setText(stringFormat(food.getProtein(), food.getPortion()));
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }


}
