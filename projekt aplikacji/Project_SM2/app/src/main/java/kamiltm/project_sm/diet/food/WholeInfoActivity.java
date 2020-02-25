package kamiltm.project_sm.diet.food;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
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
import kamiltm.project_sm.user.SimpleDividerItemDecoration;
import kamiltm.project_sm.user.Content;

public class WholeInfoActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private Boolean isInSearchMode = false;
    private Bundle bundle;
    private EditText portionET;
    private TextView nameSingleFoodTV;
    private double portion;
    private Food singleFood;
    private Double totalKcals = 0.0, totalFiber = 0.0, totalSatur = 0.0, totalPoly = 0.0, totalMono = 0.0, totalSugar = 0.0, totalProteins = 0.0, totalCarbs = 0.0, totalFats = 0.0;
    private Button info_backBTN;
    private Date datee;
    private String date;
    private final String TAG = "WholeInfoActivity";
    private final Integer USER_ID = SharedPrefManager.getInstance(this).getId();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private List<Food> foodList = new ArrayList<>();

    private RecyclerView recyclerView;
    private FoodInfoAdapter adapter;
    private List<Content> macros;

    private Animation animationUp;
    private Animation animationDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        initDate();
        if (bundle != null) {
            if (bundle.getParcelable(FoodAdapter.FOOD_OBJECT) != null) {
                setContentView(R.layout.food_single_info_fragment);
                singleFood = bundle.getParcelable(FoodAdapter.FOOD_OBJECT);
                Log.d(TAG + "1", singleFood.toString());
                initSingleItem();
            } else {
                setContentView(R.layout.food_single_info_fragment);
                singleFood = bundle.getParcelable(FoodAdapter.FOOD_OBJECT2);
                Log.d(TAG + "2", singleFood.toString());
                initSingleItemWithoutEdit();
            }
        } else {
            setContentView(R.layout.food_info_fragment);
            initWholeInfo();
        }
    }

    private void initSingleItemWithoutEdit() {
        initRecyclerView(R.id.food_infoRV2);
        portionET = findViewById(R.id.portionSingleInfoET);
        portionET.setText("1");
        portionET.setKeyListener(null);
        nameSingleFoodTV = findViewById(R.id.nameSingleFoodTV);

        List<Content> extrasSugar = new ArrayList<>();
        List<Content> extrasFats = new ArrayList<>();
        List<Content> extrasVitamins = new ArrayList<>();
        List<Content> extrasMinerals = new ArrayList<>();

        nameSingleFoodTV.setText(singleFood.getName());
        extrasSugar.add(new Content(getResources().getString(R.string.sugar), stringFormat(singleFood.getSugar()) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.kcals), stringFormat(singleFood.getKcal())));
        macros.add(new Content(getResources().getString(R.string.proteins_add), stringFormat(singleFood.getProtein()) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.fiber), stringFormat(singleFood.getFiber()) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.carbs_add), stringFormat(singleFood.getCarbohydrates()) + inWhat(), extrasSugar));

        extrasFats.add(new Content(getResources().getString(R.string.saturated_fat), stringFormat(singleFood.getSaturated_fat()) + inWhat()));
        extrasFats.add(new Content(getResources().getString(R.string.polyunsaturated_fat), stringFormat(singleFood.getPolyunsaturated_fat()) + inWhat()));
        extrasFats.add(new Content(getResources().getString(R.string.monounsaturated_fat), stringFormat(singleFood.getMonounsaturated_fat()) + inWhat()));

        macros.add(new Content(getResources().getString(R.string.fat_add), stringFormat(singleFood.getFat()) + inWhat(), extrasFats));

        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminA), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminD), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminE), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminK), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminB6), "5%"));
        macros.add(new Content(getResources().getString(R.string.witamins), "", extrasVitamins));

        extrasMinerals.add(new Content(getResources().getString(R.string.calcium), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.iron), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.magnesium), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.phosphorus), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.zinc), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.copper), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.chrome), "5%"));
        macros.add(new Content(getResources().getString(R.string.minerals), "", extrasMinerals));


        adapter.notifyDataSetChanged();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_back_single_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.backSingleInfoItem:
                if (bundle != null) {
                    if (!String.valueOf(portion).equals(String.valueOf(Double.valueOf(portionET.getText().toString())))) {
                        updateProduct();
                    }
                }
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateProduct() {
        String url = "";
        if (MealState.meal_state <= 1) {
            url = Constants.URL_UPDATE_BREAKFAST_PRODUCT;
        } else if (MealState.meal_state == 2) {
            url = Constants.URL_UPDATE_DINNER_PRODUCT;
        } else if (MealState.meal_state == 3) {
            url = Constants.URL_UPDATE_SUPPER_PRODUCT;
        } else if (MealState.meal_state == 4) {
            url = Constants.URL_UPDATE_LUNCH_PRODUCT;
        }
        Double newPortion = Double.valueOf(portionET.getText().toString());
        if (newPortion > 0 && newPortion < 100) {
            url += "?id_users=" + USER_ID + "&date=" + date + "&id=" + singleFood.getId() + "&portion=" + newPortion;
        } else {
            //TODO some kind of alert dialog
            Log.i(TAG, "DUPA");
        }

        Log.i(TAG, String.format("id_users=%d, dateState=%s, id=%d, portion=%.2f", USER_ID, date, singleFood.getId(), newPortion));

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void initRecyclerView(int resource) {
        recyclerView = findViewById(resource);
        animationUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        macros = new ArrayList<>();
        adapter = new FoodInfoAdapter(macros, animationUp, animationDown);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getBaseContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initSingleItem() {
        initRecyclerView(R.id.food_infoRV2);
        portionET = findViewById(R.id.portionSingleInfoET);
        nameSingleFoodTV = findViewById(R.id.nameSingleFoodTV);

        List<Content> extrasSugar = new ArrayList<>();
        List<Content> extrasFats = new ArrayList<>();
        List<Content> extrasVitamins = new ArrayList<>();
        List<Content> extrasMinerals = new ArrayList<>();

        portion = singleFood.getPortion();
        portionET.setText(stringFormat(portion));
        nameSingleFoodTV.setText(singleFood.getName());
        extrasSugar.add(new Content(getResources().getString(R.string.sugar), stringFormat(singleFood.getSugar() * portion) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.kcals), stringFormat(singleFood.getKcal() * portion)));
        macros.add(new Content(getResources().getString(R.string.proteins_add), stringFormat(singleFood.getProtein() * portion) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.fiber), stringFormat(singleFood.getFiber() * portion) + inWhat()));
        macros.add(new Content(getResources().getString(R.string.carbs_add), stringFormat(singleFood.getCarbohydrates() * portion) + inWhat(), extrasSugar));

        extrasFats.add(new Content(getResources().getString(R.string.saturated_fat), stringFormat(singleFood.getSaturated_fat() * portion) + inWhat()));
        extrasFats.add(new Content(getResources().getString(R.string.polyunsaturated_fat), stringFormat(singleFood.getPolyunsaturated_fat() * portion) + inWhat()));
        extrasFats.add(new Content(getResources().getString(R.string.monounsaturated_fat), stringFormat(singleFood.getMonounsaturated_fat() * portion) + inWhat()));

        macros.add(new Content(getResources().getString(R.string.fat_add), stringFormat(singleFood.getFat() * portion) + inWhat(), extrasFats));

        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminA), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminD), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminE), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminK), "5%"));
        extrasVitamins.add(new Content(getResources().getString(R.string.vitaminB6), "5%"));
        macros.add(new Content(getResources().getString(R.string.witamins), "", extrasVitamins));

        extrasMinerals.add(new Content(getResources().getString(R.string.calcium), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.iron), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.magnesium), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.phosphorus), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.zinc), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.copper), "5%"));
        extrasMinerals.add(new Content(getResources().getString(R.string.chrome), "5%"));
        macros.add(new Content(getResources().getString(R.string.minerals), "", extrasMinerals));


        adapter.notifyDataSetChanged();


    }

    private void initDate() {
        datee = DateState.dateState;
        date = sdf.format(datee);
    }

    public void initWholeInfo() {
        initRecyclerView(R.id.food_infoRV);
        getData();


    }

    public void initPieChart() {
        GraphView graph = findViewById(R.id.food_infoGraph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, totalProteins),
                new DataPoint(2, totalCarbs),
                new DataPoint(3, totalFats),
        });
        series.setValueDependentColor(data -> Color.rgb((int) data.getX() * 255 / 3, (int) Math.abs(data.getY() * 255 / 6), 100));
        series.setSpacing(50);
        series.setAnimated(true);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(getResources().getColor(R.color.Black));

        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{"", getResources().getString(R.string.proteins_short), getResources().getString(R.string.carbs_short), getResources().getString(R.string.fats_short), ""});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(4);
    }


    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    private void getData() {
        String url = Constants.URL_GET_WHOLE_DATA + USER_ID + "&date=" + date;
        Log.i(TAG, "Url: " + url);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String portion = post.getString("portion");
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
                                reformatString(portion));
                        foodList.add(f);
                    }

                }
                calculateMacros();
                List<Content> extrasSugar = new ArrayList<>();
                List<Content> extrasFats = new ArrayList<>();
                List<Content> extrasVitamins = new ArrayList<>();
                List<Content> extrasMinerals = new ArrayList<>();

                extrasSugar.add(new Content(getResources().getString(R.string.sugar), stringFormat(totalSugar) + inWhat()));

                extrasFats.add(new Content(getResources().getString(R.string.saturated_fat), stringFormat(totalSatur) + inWhat()));
                extrasFats.add(new Content(getResources().getString(R.string.polyunsaturated_fat), stringFormat(totalPoly) + inWhat()));
                extrasFats.add(new Content(getResources().getString(R.string.monounsaturated_fat), stringFormat(totalMono) + inWhat()));

                extrasVitamins.add(new Content(getResources().getString(R.string.vitaminA), "10mg"));
                extrasVitamins.add(new Content(getResources().getString(R.string.vitaminD), "3mg"));
                extrasVitamins.add(new Content(getResources().getString(R.string.vitaminE), "0"));
                extrasVitamins.add(new Content(getResources().getString(R.string.vitaminK), "0"));
                extrasVitamins.add(new Content(getResources().getString(R.string.vitaminB6), "2mg"));

                extrasMinerals.add(new Content(getResources().getString(R.string.calcium), "1%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.iron), "5%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.magnesium), "3%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.phosphorus), "5%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.zinc), "0%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.copper), "0%"));
                extrasMinerals.add(new Content(getResources().getString(R.string.chrome), "0%"));

                macros.add(new Content(getResources().getString(R.string.kcals), stringFormat(totalKcals) + " / 2365"));
                macros.add(new Content(getResources().getString(R.string.proteins_add), stringFormat(totalProteins) + inWhat() + " / 176g"));
                macros.add(new Content(getResources().getString(R.string.fiber), stringFormat(totalFiber) + inWhat() + " / 31g"));
                macros.add(new Content(getResources().getString(R.string.carbs_add), stringFormat(totalCarbs) + inWhat() + " / 237g", extrasSugar));
                macros.add(new Content(getResources().getString(R.string.fat_add), stringFormat(totalFats) + inWhat() + " / 79g", extrasFats));
                macros.add(new Content(getResources().getString(R.string.witamins), "", extrasVitamins));
                macros.add(new Content(getResources().getString(R.string.minerals), "", extrasMinerals));


                adapter.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.e("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public String inWhat() {
        return "" + getResources().getString(R.string.grams);
    }

    public String stringFormat(Double value) {
        String pattern = "##0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(value);
        return format;
    }

    public Double reformatString(String text) {
        return Double.valueOf(text);
    }

    public void calculateMacros() {
        for (Food f : foodList) {
            double multiplier = f.getPortion();
            totalProteins += f.getProtein() * multiplier;
            totalCarbs += f.getCarbohydrates() * multiplier;
            totalFats += f.getFat() * multiplier;
            totalFiber += f.getFiber() * multiplier;
            totalMono += f.getMonounsaturated_fat() * multiplier;
            totalPoly += f.getPolyunsaturated_fat() * multiplier;
            totalSatur += f.getSaturated_fat() * multiplier;
            totalKcals += f.getKcal() * multiplier;
            totalSugar += f.getSugar() * multiplier;
        }
        initPieChart();
    }
}