package kamiltm.project_sm.diet.food;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.MealState;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    private List<Food> foodList;
    private Date datee;
    private String date;
    public static final String FOOD_OBJECT = "food";
    public static final String FOOD_OBJECT2 = "food2";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, calories, proteins, carbs, fats;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.foodNameTV);
            calories = view.findViewById(R.id.foodContentCalories);
            proteins = view.findViewById(R.id.foodContentProteins);
            fats = view.findViewById(R.id.foodContentFats);
            carbs = view.findViewById(R.id.foodContentCarbs);
        }
    }


    public FoodAdapter(List<Food> foodList) {
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
                .inflate(R.layout.food_row, parent, false);

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


        holder.calories.setOnClickListener(e -> {
            Intent intent = new Intent(holder.itemView.getContext(), WholeInfoActivity.class);
            intent.putExtra(FOOD_OBJECT2, food);
            Log.d("FoodAdapter.class", "dupa");
            ((Activity) holder.itemView.getContext()).startActivity(intent);
        });

        holder.title.setOnClickListener(e -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(holder.itemView.getContext());
            final EditText edittext = new EditText(holder.itemView.getContext());
            adb.setTitle(holder.itemView.getResources().getString(R.string.add_question));
            adb.setMessage(holder.itemView.getResources().getString(R.string.are_you_sure_you_want_to_add_a_product_with_name) + " " + food.getName() + holder.itemView.getResources().getString(R.string.continuation_of_add_product_adbb));
            adb.setNegativeButton(holder.itemView.getResources().getString(R.string.cancel), null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            edittext.setLayoutParams(lp);
            adb.setView(edittext);
            adb.setPositiveButton("Ok", (dialog, which) -> {

                datee = DateState.dateState;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(datee);

                int user = SharedPrefManager.getInstance(holder.itemView.getContext()).getId();

                String portionOfFood = edittext.getText().toString();
                if (portionOfFood.matches("")) {
                    portionOfFood = "1";
                }


                String url_breakfast = Constants.URL_INSERT_BREAKFAST_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + food.getId() + "&portion=" + portionOfFood;
                String url_dinner = Constants.URL_INSERT_DINNER_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + food.getId() + "&portion=" + portionOfFood;
                String url_supper = Constants.URL_INSERT_SUPPER_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + food.getId() + "&portion=" + portionOfFood;
                String url_lunch = Constants.URL_INSERT_LUNCH_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + food.getId() + "&portion=" + portionOfFood;

                Log.d("FoodAdapter", url_breakfast);

                if (MealState.meal_state <= 1) {
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_breakfast, null, response -> {
                    }, error -> {
                        Log.e("ERROR", " " + error.toString());
                    });
                    RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                } else if (MealState.meal_state == 2) {
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_dinner, null, response -> {
                    }, error -> {
                        Log.e("ERROR", " " + error.toString());
                    });
                    RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                } else if (MealState.meal_state == 3) {
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_supper, null, response -> {
                    }, error -> {
                        Log.e("ERROR", " " + error.toString());
                    });
                    RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                } else if (MealState.meal_state == 4) {
                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_lunch, null, response -> {
                    }, error -> {
                        Log.e("ERROR", " " + error.toString());
                    });
                    RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                }

                ((Activity) holder.itemView.getContext()).finish();

            });
            adb.show();

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
