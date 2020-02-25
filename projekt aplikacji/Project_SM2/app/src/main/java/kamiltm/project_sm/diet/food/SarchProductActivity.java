package kamiltm.project_sm.diet.food;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.connectors.MealState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

public class SarchProductActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private Date datee;
    private String date;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private final String TAG = "SarchProductActivity";
    protected RecyclerView recyclerView;

    private List<Food> mealFoodsList = new ArrayList<>();
    private List<Meal> mealList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private List<Food> foodListMeal = new ArrayList<>();
    private List<Food> foodListToSaveMeals = new ArrayList<>();

    private FoodMealAdapter foodAdapterMeal;
    private FoodAdapter foodAdapter;
    private MealAdapter mealAdapter;

    private TextView proteinTooltipTV, CarbsTooltipTV, fatsTooltipTV, calorieTooltipTV;
    private Button searchProduct;
    private EditText productName;
    private int user;
    private ImageButton barCodeIB;

    private MenuItem saveItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_search_product_layout);
        productName = findViewById(R.id.searchProductET);
        searchProduct = findViewById(R.id.searchProductBTN);
        user = SharedPrefManager.getInstance(this).getId();
        recyclerView = findViewById(R.id.searchProductsRV);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        proteinTooltipTV = findViewById(R.id.proteinTooltipTV);
        CarbsTooltipTV = findViewById(R.id.CarbsTooltipTV);
        fatsTooltipTV = findViewById(R.id.fatsTooltipTV);
        calorieTooltipTV = findViewById(R.id.calorieTooltipTV);
        proteinTooltipTV.setTooltipText(getResources().getString(R.string.proteins));
        CarbsTooltipTV.setTooltipText(getResources().getString(R.string.carbohydrates));
        fatsTooltipTV.setTooltipText(getResources().getString(R.string.fats));
        calorieTooltipTV.setTooltipText(getResources().getString(R.string.calories));
        barCodeIB = findViewById(R.id.barCodeIB);

        initRV();
        refreshList();

        barCodeIB.setOnClickListener(e -> startActivity(new Intent(this, ScanActivity.class)));

        searchProduct.setOnClickListener(e -> {
            initRV();
            String search = productName.getText().toString();
            if (TextUtils.isEmpty(search)) {
                productName.setError(getResources().getString(R.string.emptySearchEditText));
                return;
            }
            getData(user, search);
        });


        if (MealState.meal_state <= Constants.BREAKFAST_STATE) {
            makeSnackbar(getString(R.string.eggs));
        } else if (MealState.meal_state == Constants.DINNER_STATE) {
            makeSnackbar(getString(R.string.chickenFilet));
        } else if (MealState.meal_state == Constants.SUPPER_STATE) {
            makeSnackbar(getString(R.string.oats));
        } else if (MealState.meal_state == Constants.LUNCH_STATE) {
            makeSnackbar(getString(R.string.protein_bar));
        }


    }

    private void makeSnackbar(String text) {
        Log.d(TAG, text);
        String buttonText = this.getResources().getString(R.string.add);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, text, Snackbar.LENGTH_LONG).setAction(buttonText, v -> {
                    AlertDialog.Builder adb = new AlertDialog.Builder(this);
                    final EditText edittext = new EditText(this);
                    adb.setTitle(this.getResources().getString(R.string.add_question));
                    adb.setMessage(this.getResources().getString(R.string.are_you_sure_you_want_to_add_a_product_with_name) + " " + text + this.getResources().getString(R.string.continuation_of_add_product_adbb));
                    adb.setNegativeButton(this.getResources().getString(R.string.cancel), null);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    edittext.setLayoutParams(lp);
                    adb.setView(edittext);
                    adb.setPositiveButton("Ok", (dialog, which) -> {

                        datee = DateState.dateState;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        date = sdf.format(datee);

                        int user = SharedPrefManager.getInstance(this).getId();

                        String portionOfFood = edittext.getText().toString();
                        if (portionOfFood.matches("")) {
                            portionOfFood = "1";
                        }


                        String url_breakfast = Constants.URL_INSERT_BREAKFAST_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + 1 + "&portion=" + portionOfFood;
                        String url_dinner = Constants.URL_INSERT_DINNER_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + 2+ "&portion=" + portionOfFood;
                        String url_supper = Constants.URL_INSERT_SUPPER_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + 18 + "&portion=" + portionOfFood;
                        String url_lunch = Constants.URL_INSERT_LUNCH_DATA + "?date=" + date + "&id_users=" + user + "&id_products=" + 20 + "&portion=" + portionOfFood;

                        Log.d("FoodAdapter", url_breakfast);

                        if (MealState.meal_state <= Constants.BREAKFAST_STATE) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_breakfast, null, response -> {
                            }, error -> {
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == Constants.DINNER_STATE) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_dinner, null, response -> {
                            }, error -> {
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == Constants.SUPPER_STATE) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_supper, null, response -> {
                            }, error -> {
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == Constants.LUNCH_STATE) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_lunch, null, response -> {
                            }, error -> {
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
                        }

                        this.finish();

                    });
                    adb.show();

                });
        snackbar.setActionTextColor(ContextCompat.getColor(this, R.color.Red));
        snackbar.show();
    }

    private void initRV() {
        foodAdapter = new FoodAdapter(foodList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodAdapter);
    }

    private void initRVMeal() {
        foodAdapterMeal = new FoodMealAdapter(foodListMeal);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodAdapterMeal);

    }

    private void initRVMealSelect() {
        mealAdapter = new MealAdapter(mealList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager2);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mealAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_activity_menu, menu);
        saveItem = menu.findItem(R.id.saveMealItem);
        saveItem.setVisible(false);
        return true;
    }

    private List<Integer> analyzeData(String text) {
        ArrayList<Integer> list = new ArrayList<>();
        String[] couple = text.split(",");
        for (int i = 0; i < couple.length; i++) {
            list.add(Integer.parseInt(couple[i]));
        }
        return list;
    }

    private String getMealData(List<Food> foodList) {
        String s = "";
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getChecked())
                s += foodList.get(i).getId() + ",";
        }
        if (s.charAt(s.length() - 1) == ',') {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addProductItem:
                try {
                    saveItem.setVisible(false);
                    startActivity(new Intent(this, AddFoodActivity.class));
                    finish();
                } catch (Exception er) {
                    er.printStackTrace();
                }
                return true;
            case R.id.checkLastProductsItem:
                initRV();
                saveItem.setVisible(false);
                getDataOfLastMeals(user);
                return true;
            case R.id.backToSearchActivityItem:
                saveItem.setVisible(false);
                finish();
                return true;
            case R.id.favProductsItem:
                initRV();
                saveItem.setVisible(false);
                getFavourites(user);
                return true;
            case R.id.createMealItem:
                initRVMeal();
                getFavouritesMeal(user);
                saveItem.setVisible(true);
                return true;
            case R.id.saveMealItem:
                saveNewMeal(user);
                return true;
            case R.id.addMealItem:
                initRVMealSelect();
                addNewMeal(user);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewMeal(int user) {
        refreshList();
//        String url = "";
//        if (MealState.meal_state <= 1) {
//            url = Constants.URL_INSERT_BREAKFAST_MEAL;
//        } else if (MealState.meal_state == 2) {
//            url = Constants.URL_INSERT_DINNER_MEAL;
//        } else if (MealState.meal_state == 3) {
//            url = Constants.URL_INSERT_SUPPER_MEAL;
//        } else if (MealState.meal_state == 4) {
//            url = Constants.URL_INSERT_LUNCH_MEAL;
//        }

        String url = Constants.URL_GET_MEALS_DATA + "?id_users=" + user;
        Log.d(TAG, url);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String values = post.getString("products");
                    String mealName = post.getString("name");
                    String s = makeProductsString(analyzeData(values));
                    getProductData(s, mealName);
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> Log.d("ERROR", " " + error.toString()));
        queue.add(jsonObjectRequest);
    }

    private String makeProductsString(List<Integer> list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                s += list.get(i);
            } else {
                s += list.get(i) + ",";
            }
        }
        return s;
    }

    public void getProductData(String id, String mealName) {
        String url = Constants.URL_GET_PRODUCT_WITH_ID + "?id=" + id;
        Log.i(TAG, "Url: " + url);
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int j = 0; j < response.length(); j++) {
                    JSONObject post2 = response.getJSONObject(j);
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

                    Food f = new Food(Integer.valueOf(id2),
                            nameOFFood, reformatString(kcal),
                            reformatString(protein),
                            reformatString(carbohydrates),
                            reformatString(fat),
                            reformatString(saturated_fat),
                            reformatString(polyunsaturated_fat),
                            reformatString(monounsaturated_fat),
                            reformatString(sugar),
                            reformatString(fiber));

                    mealFoodsList.add(f);
                    Log.d("ID", f.toString());
                }
                mealList.add(new Meal(mealFoodsList, mealName));
                mealAdapter.notifyDataSetChanged();
                mealFoodsList = new ArrayList<>();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        queue.add(jsonObjectRequest);
    }

    private void initMealList() {
        //  Intent intent = new Intent(this, Meals.class);
        //intent.putExtra()
    }


    private void makeListWithSelectedProducts() {
        for (int i = 0; i < foodListMeal.size(); i++) {
            if (foodListMeal.get(i).getChecked())
                foodListToSaveMeals.add(foodListMeal.get(i));
        }
    }

    private void saveNewMeal(int id_users) {
        makeListWithSelectedProducts();

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setTitle(R.string.meal_name);
        adb.setMessage(R.string.nameMeal);
        adb.setNegativeButton(this.getResources().getString(R.string.cancel), (dialog, which) -> {
            return;
        });
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edittext.setLayoutParams(lp);
        adb.setView(edittext);
        adb.setPositiveButton("Ok", (dialog, which) -> {
            if (!edittext.getText().equals("")) {
                String name = edittext.getText().toString();

                String url = Constants.URL_SAVE_MEAL + id_users + "&products=" + getMealData(foodListToSaveMeals) + "&name=" + name;
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
                }, error -> {
                    Log.d("ERROR", " " + error.toString());

                });
                RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
                foodListToSaveMeals = new ArrayList<>();
            }
        });
        adb.show();


    }

    public void refreshList() {
        if (!foodList.isEmpty())
            foodList.removeAll(foodList);
        if (!foodListMeal.isEmpty())
            foodListMeal.removeAll(foodListMeal);
        if (!mealList.isEmpty())
            mealList.removeAll(mealList);

    }

    public Double reformatString(String text) {
        return Double.valueOf(text);
    }

    private void getData(int id_users, String name) {
        refreshList();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_PRODUCTDATA + name, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String protein = post.getString("protein");
                    String carbohydrates = post.getString("carbohydrates");
                    String fat = post.getString("fat");
                    String nameOFFood = post.getString("name");
                    String kcal = post.getString("kcal");
                    String fiber = post.getString("fiber");
                    String saturated_fat = post.getString("saturated_fat");
                    String polyunsaturated_fat = post.getString("polyunsaturated_fat");
                    String monounsaturated_fat = post.getString("monounsaturated_fat");
                    String sugar = post.getString("sugar");

                    Food f = new Food(Integer.valueOf(id),
                            nameOFFood, reformatString(kcal),
                            reformatString(protein),
                            reformatString(carbohydrates),
                            reformatString(fat),
                            reformatString(saturated_fat),
                            reformatString(polyunsaturated_fat),
                            reformatString(monounsaturated_fat),
                            reformatString(sugar),
                            reformatString(fiber));
                    foodList.add(f);

                }
                foodAdapter.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());

        });
        queue.add(jsonObjectRequest);
    }

    private void getDataOfLastMeals(int id_users) {
        refreshList();
        String url = Constants.URL_GET_LAST_MEALS + id_users;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);

                    String protein = post.getString("protein");
                    String id = post.getString("id");
                    String carbohydrates = post.getString("carbohydrates");
                    String fat = post.getString("fat");
                    String nameOFFood = post.getString("name");
                    String kcal = post.getString("kcal");
                    String fiber = post.getString("fiber");
                    String saturated_fat = post.getString("saturated_fat");
                    String polyunsaturated_fat = post.getString("polyunsaturated_fat");
                    String monounsaturated_fat = post.getString("monounsaturated_fat");
                    String sugar = post.getString("sugar");

                    Food f = new Food(Integer.valueOf(id),
                            nameOFFood, reformatString(kcal),
                            reformatString(protein),
                            reformatString(carbohydrates),
                            reformatString(fat),
                            reformatString(saturated_fat),
                            reformatString(polyunsaturated_fat),
                            reformatString(monounsaturated_fat),
                            reformatString(sugar),
                            reformatString(fiber));
                    foodList.add(f);
                }
                foodAdapter.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void getFavourites(int id_users) {
        refreshList();
        String url = Constants.URL_GET_FAVS + id_users;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);

                    String protein = post.getString("protein");
                    String id = post.getString("id");
                    String carbohydrates = post.getString("carbohydrates");
                    String fat = post.getString("fat");
                    String nameOFFood = post.getString("name");
                    String kcal = post.getString("kcal");
                    String fiber = post.getString("fiber");
                    String saturated_fat = post.getString("saturated_fat");
                    String polyunsaturated_fat = post.getString("polyunsaturated_fat");
                    String monounsaturated_fat = post.getString("monounsaturated_fat");
                    String sugar = post.getString("sugar");

                    Food f = new Food(Integer.valueOf(id),
                            nameOFFood, reformatString(kcal),
                            reformatString(protein),
                            reformatString(carbohydrates),
                            reformatString(fat),
                            reformatString(saturated_fat),
                            reformatString(polyunsaturated_fat),
                            reformatString(monounsaturated_fat),
                            reformatString(sugar),
                            reformatString(fiber));
                    foodList.add(f);
                }
                foodAdapter.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void getFavouritesMeal(int id_users) {
        refreshList();
        String url = Constants.URL_GET_FAVS + id_users;
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);

                    String protein = post.getString("protein");
                    String id = post.getString("id");
                    String carbohydrates = post.getString("carbohydrates");
                    String fat = post.getString("fat");
                    String nameOFFood = post.getString("name");
                    String kcal = post.getString("kcal");
                    String fiber = post.getString("fiber");
                    String saturated_fat = post.getString("saturated_fat");
                    String polyunsaturated_fat = post.getString("polyunsaturated_fat");
                    String monounsaturated_fat = post.getString("monounsaturated_fat");
                    String sugar = post.getString("sugar");

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
                            1.0,
                            false,
                            false);
                    foodListMeal.add(f);
                }
                foodAdapterMeal.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

}
