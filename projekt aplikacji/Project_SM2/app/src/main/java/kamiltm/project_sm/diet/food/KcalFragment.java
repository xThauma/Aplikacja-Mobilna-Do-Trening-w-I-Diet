package kamiltm.project_sm.diet.food;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 2017-12-29.
 */

public class KcalFragment extends Fragment {

    private TextView kcal1TV, kcal2TV, kcal3TV;
    private Double kcalsEaten, kcalsLeft;
    private Double daily_kcals;
    private int user;
    private String date;
    public static Date datee;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_kcal_fragment, null);
        initView(view);
        return view;
    }

    public void initView(View view) {
        kcal1TV = view.findViewById(R.id.kcal1);
        kcal2TV = view.findViewById(R.id.kcal2);
        kcal3TV = view.findViewById(R.id.kcal3);

        user = SharedPrefManager.getInstance(getContext()).getId();

        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserCalories(user);
    }

    private void getUserCalories(int id) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_GETUSERDAILYKCAL + id, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    Double daily_kcal = post.getDouble("daily_kcal");
                    Integer ddaily_kcal = daily_kcal.intValue();
                    kcal1TV.setText(ddaily_kcal.toString());
                    daily_kcals = daily_kcal;
                }
                RemoveKcalFromBreakfast(user, date);
                RemoveKcalFromDinner(user, date);
                RemoveKcalFromSupper(user, date);
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void RemoveKcalFromBreakfast(int id_users, String date) {
        kcalsEaten = 0.0;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_GETUSERBREAKFAST + id_users + "&date=" + date, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    Double portion = post.getDouble("portion");
                    JSONArray food = post.getJSONArray("id_products");
                    double kcal = 0;
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        kcal = post2.getDouble("kcal");
                        kcalsEaten += kcal * portion;

                    }
                }
                kcalsLeft = daily_kcals - kcalsEaten;
                Integer kcalsEatenInt = kcalsEaten.intValue();
                kcal2TV.setText(kcalsEatenInt.toString());
                Integer kcalsLeftInt = kcalsLeft.intValue();
                kcal3TV.setText(kcalsLeftInt.toString());
                updateUserKcals(id_users, kcalsLeft);
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void RemoveKcalFromDinner(int id_users, String date) {
        kcalsEaten = 0.0;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_GETUSERDINNER + id_users + "&date=" + date, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    Double portion = post.getDouble("portion");
                    JSONArray food = post.getJSONArray("id_products");
                    double kcal = 0;
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        kcal = post2.getDouble("kcal");
                        kcalsEaten += kcal * portion;

                    }
                }
                kcalsLeft = daily_kcals - kcalsEaten;
                Integer kcalsEatenInt = kcalsEaten.intValue();
                kcal2TV.setText(kcalsEatenInt.toString());
                Integer kcalsLeftInt = kcalsLeft.intValue();
                kcal3TV.setText(kcalsLeftInt.toString());
                updateUserKcals(id_users, kcalsLeft);
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void RemoveKcalFromSupper(int id_users, String date) {
        kcalsEaten = 0.0;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_GETUSERSUPPER + id_users + "&date=" + date, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    Double portion = post.getDouble("portion");
                    JSONArray food = post.getJSONArray("id_products");
                    double kcal = 0;
                    for (int j = 0; j < food.length(); j++) {
                        JSONObject post2 = food.getJSONObject(j);
                        kcal = post2.getDouble("kcal");
                        kcalsEaten += kcal * portion;

                    }
                }
                kcalsLeft = daily_kcals - kcalsEaten;
                Integer kcalsEatenInt = kcalsEaten.intValue();
                kcal2TV.setText(kcalsEatenInt.toString());
                Integer kcalsLeftInt = kcalsLeft.intValue();
                kcal3TV.setText(kcalsLeftInt.toString());
                updateUserKcals(id_users, kcalsLeft);
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    private void updateUserKcals(int id_users, Double kcalsLeft) {
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_UPDATEKCAL + id_users + "&daily_kcal=" + kcalsLeft, null, response -> {
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    //TODO zrobic dla lunchy

}
