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

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MyViewHolder> {

    private List<Meal> mealList;
    private Date datee;
    private String date;
    private int counter = 0;
    public final static String MEAL_OBJECT = "meal";

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


    public MealAdapter(List<Meal> mealList) {
        this.mealList = mealList;
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
        Meal meal = mealList.get(position);
        Double d_kcals = 0.0;
        Double d_proteins = 0.0;
        Double d_carbs = 0.0;
        Double d_fats = 0.0;

        for (Food f : meal.getFoodList()) {
            d_kcals += f.getKcal();
            d_proteins += f.getProtein();
            d_carbs += f.getCarbohydrates();
            d_fats += f.getFat();
        }

        holder.calories.setOnClickListener(e -> {
            Intent intent = new Intent(holder.itemView.getContext(), MealsActivity.class);
            intent.putExtra(MEAL_OBJECT, meal);
            ((Activity) holder.itemView.getContext()).startActivity(intent);
        });

        holder.title.setOnClickListener(e -> {
            int size = meal.getFoodList().size();
            for (Food food : meal.getFoodList()) {
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
                    counter++;
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
                    if (counter == size) {
                        Log.d(counter + "", size + "");
                        ((Activity) holder.itemView.getContext()).finish();
                    }

                });
                adb.show();

            }
        });


        holder.calories.setText(stringFormat(d_kcals, 1.0));
        holder.title.setText(meal.getName());
        holder.carbs.setText(stringFormat(d_carbs, 1.0));
        holder.fats.setText(stringFormat(d_fats, 1.0));
        holder.proteins.setText(stringFormat(d_proteins, 1.0));
    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }


}
