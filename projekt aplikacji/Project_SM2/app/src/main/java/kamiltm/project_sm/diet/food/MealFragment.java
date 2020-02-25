package kamiltm.project_sm.diet.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.MealState;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 2017-12-29.
 */

public class MealFragment extends Fragment {

    private List<Food> foodListBreakfast = new ArrayList<>();
    private List<Food> foodListLunch = new ArrayList<>();
    private List<Food> foodListDinner = new ArrayList<>();
    private List<Food> foodListSupper = new ArrayList<>();

    private FoodAdapterBreakfastWithDelete foodAdapterBreakfast;
    private FoodAdapterLunchWithDelete foodAdapterLunch;
    private FoodAdapterDinnerWithDelete foodAdapterDinner;
    private FoodAdapterSupperWithDelete foodAdapterSupper;

    protected RecyclerView recyclerViewBreakfast, recyclerViewLunch, recyclerViewDinner, recyclerViewSupper;
    private Button changeDateBTNleft, changeDateBTNright, changeDateBTN;
    private Date datee, newDate;
    private String date;
    private final String TAG = "MealFragment";
    private final Integer USER_ID = SharedPrefManager.getInstance(getContext()).getId();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_meals_fragment, container, false);
        initViews(view);
        handleInput(view);
        return view;
    }

    public void setDateAndRefreshKcals(View view) {
        datee = newDate;
        DateState.dateState = datee;
        date = sdf.format(newDate);
        changeDateBTN.setText(date);
        getBreakfastData(USER_ID, date, Constants.URL_GETUSERBREAKFAST);
        getLunchData(USER_ID, date, Constants.URL_GETUSERLUNCH);
        getDinnerData(USER_ID, date, Constants.URL_GETUSERDINNER);
        getSupperData(USER_ID, date, Constants.URL_GETUSERSUPPER);
        DietFragment.setFragmentKcal(view);
    }


    public void handleInput(View view) {
        changeDateBTNleft.setOnClickListener(e -> {
            newDate = new DateTime(datee).minusDays(1).toDate();
            setDateAndRefreshKcals(view);
        });

        changeDateBTNright.setOnClickListener(e -> {
            newDate = new DateTime(datee).plusDays(1).toDate();
            setDateAndRefreshKcals(view);
        });
    }

    public void initViews(View view) {
        JodaTimeAndroid.init(getContext());
        changeDateBTNleft = view.findViewById(R.id.changeDateBTNleft);
        changeDateBTNright = view.findViewById(R.id.changeDateBTNright);
        changeDateBTN = view.findViewById(R.id.changeDateBTN);

        recyclerViewBreakfast = view.findViewById(R.id.breakfastRV);
        foodAdapterBreakfast = new FoodAdapterBreakfastWithDelete(foodListBreakfast);
        RecyclerView.LayoutManager mLayoutManagerBreakfast = new LinearLayoutManager(getContext());
        recyclerViewBreakfast.setLayoutManager(mLayoutManagerBreakfast);
        recyclerViewBreakfast.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBreakfast.setAdapter(foodAdapterBreakfast);

        recyclerViewLunch = view.findViewById(R.id.lunchRV);
        foodAdapterLunch = new FoodAdapterLunchWithDelete(foodListLunch);
        RecyclerView.LayoutManager mLayoutManagerLunch = new LinearLayoutManager(getContext());
        recyclerViewLunch.setLayoutManager(mLayoutManagerLunch);
        recyclerViewLunch.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLunch.setAdapter(foodAdapterLunch);

        recyclerViewDinner = view.findViewById(R.id.dinnerRV);
        foodAdapterDinner = new FoodAdapterDinnerWithDelete(foodListDinner);
        RecyclerView.LayoutManager mLayoutManagerDinner = new LinearLayoutManager(getContext());
        recyclerViewDinner.setLayoutManager(mLayoutManagerDinner);
        recyclerViewDinner.setItemAnimator(new DefaultItemAnimator());
        recyclerViewDinner.setAdapter(foodAdapterDinner);

        recyclerViewSupper = view.findViewById(R.id.supperRV);
        foodAdapterSupper = new FoodAdapterSupperWithDelete(foodListSupper);
        RecyclerView.LayoutManager mLayoutManagerSupper = new LinearLayoutManager(getContext());
        recyclerViewSupper.setLayoutManager(mLayoutManagerSupper);
        recyclerViewSupper.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSupper.setAdapter(foodAdapterSupper);

        datee = DateState.dateState;
        date = sdf.format(datee);
        changeDateBTN.setText(date);
    }

    public void refrestBreakfastList() {
        if (!foodListBreakfast.isEmpty()) {
            foodListBreakfast.removeAll(foodListBreakfast);
            foodAdapterBreakfast.notifyDataSetChanged();
        }
    }

    public void refrestLunchList() {

        if (!foodListLunch.isEmpty()) {
            foodListLunch.removeAll(foodListLunch);
            foodAdapterLunch.notifyDataSetChanged();
        }
    }

    public void refrestDinnerList() {
        if (!foodListDinner.isEmpty()) {
            foodListDinner.removeAll(foodListDinner);
            foodAdapterDinner.notifyDataSetChanged();
        }
    }

    public void refrestSupperList() {
        if (!foodListSupper.isEmpty()) {
            foodListSupper.removeAll(foodListSupper);
            foodAdapterSupper.notifyDataSetChanged();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
        getBreakfastData(USER_ID, date, Constants.URL_GETUSERBREAKFAST);
        getLunchData(USER_ID, date, Constants.URL_GETUSERLUNCH);
        getDinnerData(USER_ID, date, Constants.URL_GETUSERDINNER);
        getSupperData(USER_ID, date, Constants.URL_GETUSERSUPPER);
    }

    public Double reformatString(String text) {
        return Double.valueOf(text);
    }

    public void getBreakfastData(int id_users, String date, String url) {
        url += id_users + "&date=" + date;
        Log.i(TAG, "Url: " + url);
        Log.i(TAG, "State: " + MealState.meal_state);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {
            try {
                refrestBreakfastList();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String portion = post.getString("portion");
                    int favourite = post.getInt("favourite");
                    JSONArray food = post.getJSONArray("id_products");
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        String id2 = post2.getString("id");
                        String protein = post2.getString("protein");
                        String carbohydrates = post2.getString("carbohydrates");
                        String fat = post2.getString("fat");
                        String nameOFFood = post2.getString("name");
                        String kcal = post2.getString("kcal");
                        String fiber = post2.getString("fiber");
                        String saturated_fat = post2.getString("saturated_fat");
                        String polyunsaturated_fat = post2.getString("polyunsaturated_fat");
                        String monounsaturated_fat = post2.getString("monounsaturated_fat");
                        String sugar = post2.getString("sugar");

                        Boolean fav = false;
                        if (favourite == 1)
                            fav = true;
                        else fav = false;

                        Food f = new Food(Integer.valueOf(id),
                                nameOFFood, reformatString(kcal),
                                reformatString(protein),
                                reformatString(carbohydrates),
                                reformatString(fat),
                                reformatString(saturated_fat),
                                reformatString(polyunsaturated_fat),
                                reformatString(monounsaturated_fat),
                                reformatString(sugar),
                                reformatString(fiber),
                                reformatString(portion),
                                fav);
                        Log.i(TAG, "Food: " + f.toString());
                        foodListBreakfast.add(f);
                    }

                }
                foodAdapterBreakfast.notifyDataSetChanged();
            } catch (JSONException je) {

                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void getLunchData(int id_users, String date, String url) {
        url += id_users + "&date=" + date;
        Log.i(TAG, "Url: " + url);
        Log.i(TAG, "State: " + MealState.meal_state);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {

            try {
                refrestLunchList();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String portion = post.getString("portion");
                    int favourite = post.getInt("favourite");
                    JSONArray food = post.getJSONArray("id_products");
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        String id2 = post2.getString("id");
                        String protein = post2.getString("protein");
                        String carbohydrates = post2.getString("carbohydrates");
                        String fat = post2.getString("fat");
                        String nameOFFood = post2.getString("name");
                        String kcal = post2.getString("kcal");
                        String fiber = post2.getString("fiber");
                        String saturated_fat = post2.getString("saturated_fat");
                        String polyunsaturated_fat = post2.getString("polyunsaturated_fat");
                        String monounsaturated_fat = post2.getString("monounsaturated_fat");
                        String sugar = post2.getString("sugar");

                        Boolean fav = false;
                        if (favourite == 1)
                            fav = true;
                        else fav = false;

                        Food f = new Food(Integer.valueOf(id),
                                nameOFFood, reformatString(kcal),
                                reformatString(protein),
                                reformatString(carbohydrates),
                                reformatString(fat),
                                reformatString(saturated_fat),
                                reformatString(polyunsaturated_fat),
                                reformatString(monounsaturated_fat),
                                reformatString(sugar),
                                reformatString(fiber),
                                reformatString(portion),
                                fav);
                        Log.i(TAG, "Food: " + f.toString());
                        foodListLunch.add(f);
                    }

                }
                foodAdapterLunch.notifyDataSetChanged();
            } catch (JSONException je) {

                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void getDinnerData(int id_users, String date, String url) {
        url += id_users + "&date=" + date;
        Log.i(TAG, "Url: " + url);
        Log.i(TAG, "State: " + MealState.meal_state);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {

            try {
                refrestDinnerList();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String portion = post.getString("portion");
                    int favourite = post.getInt("favourite");
                    JSONArray food = post.getJSONArray("id_products");
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        String id2 = post2.getString("id");
                        String protein = post2.getString("protein");
                        String carbohydrates = post2.getString("carbohydrates");
                        String fat = post2.getString("fat");
                        String nameOFFood = post2.getString("name");
                        String kcal = post2.getString("kcal");
                        String fiber = post2.getString("fiber");
                        String saturated_fat = post2.getString("saturated_fat");
                        String polyunsaturated_fat = post2.getString("polyunsaturated_fat");
                        String monounsaturated_fat = post2.getString("monounsaturated_fat");
                        String sugar = post2.getString("sugar");

                        Boolean fav = false;
                        if (favourite == 1)
                            fav = true;
                        else fav = false;

                        Food f = new Food(Integer.valueOf(id),
                                nameOFFood, reformatString(kcal),
                                reformatString(protein),
                                reformatString(carbohydrates),
                                reformatString(fat),
                                reformatString(saturated_fat),
                                reformatString(polyunsaturated_fat),
                                reformatString(monounsaturated_fat),
                                reformatString(sugar),
                                reformatString(fiber),
                                reformatString(portion),
                                fav);
                        Log.i(TAG, "Food: " + f.toString());
                        foodListDinner.add(f);
                    }

                }
                foodAdapterDinner.notifyDataSetChanged();
            } catch (JSONException je) {

                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void getSupperData(int id_users, String date, String url) {
        url += id_users + "&date=" + date;
        Log.i(TAG, "Url: " + url);
        Log.i(TAG, "State: " + MealState.meal_state);


        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {

            try {
                refrestSupperList();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String portion = post.getString("portion");
                    int favourite = post.getInt("favourite");
                    JSONArray food = post.getJSONArray("id_products");
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        String id2 = post2.getString("id");
                        String protein = post2.getString("protein");
                        String carbohydrates = post2.getString("carbohydrates");
                        String fat = post2.getString("fat");
                        String nameOFFood = post2.getString("name");
                        String kcal = post2.getString("kcal");
                        String fiber = post2.getString("fiber");
                        String saturated_fat = post2.getString("saturated_fat");
                        String polyunsaturated_fat = post2.getString("polyunsaturated_fat");
                        String monounsaturated_fat = post2.getString("monounsaturated_fat");
                        String sugar = post2.getString("sugar");

                        Boolean fav = false;
                        if (favourite == 1)
                            fav = true;
                        else fav = false;

                        Food f = new Food(Integer.valueOf(id),
                                nameOFFood, reformatString(kcal),
                                reformatString(protein),
                                reformatString(carbohydrates),
                                reformatString(fat),
                                reformatString(saturated_fat),
                                reformatString(polyunsaturated_fat),
                                reformatString(monounsaturated_fat),
                                reformatString(sugar),
                                reformatString(fiber),
                                reformatString(portion),
                                fav);
                        Log.i(TAG, "Food: " + f.toString());
                        foodListSupper.add(f);
                    }

                }
                foodAdapterSupper.notifyDataSetChanged();

            } catch (JSONException je) {

                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


}
