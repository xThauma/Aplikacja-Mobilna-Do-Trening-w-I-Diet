package kamiltm.project_sm.diet.food;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import kamiltm.project_sm.R;

public class MealsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    List<Food> foodList = new ArrayList<>();

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meal_info);
        initRV();
        getBundle();
    }

    private void initRV() {
        recyclerView = findViewById(R.id.mealInfoRV);

        foodAdapter = new FoodAdapter(foodList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(foodAdapter);
    }

    private void getBundle() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.getParcelable(MealAdapter.MEAL_OBJECT) != null) {
                Meal m = bundle.getParcelable(MealAdapter.MEAL_OBJECT);
                Log.d("Meals.class", m.toString());
                foodList.addAll(m.getFoodList());
                foodAdapter.notifyDataSetChanged();
            }
        }
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
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}