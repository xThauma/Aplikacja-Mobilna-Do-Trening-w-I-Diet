package kamiltm.project_sm.diet.food;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import kamiltm.project_sm.R;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;

public class AddFoodActivity extends AppCompatActivity {
    private Button addProductBTN;
    private String name;
    private Double kcals = 0.0, proteins = 0.0, fats = 0.0, carbs = 0.0, fibers = 0.0, satur = 0.0, poly = 0.0, mono = 0.0, sugar = 0.0;
    private String s_kcals, s_proteins, s_fats, s_carbs, s_fibers, s_satur, s_poly, s_mono, s_sugar;
    private EditText nameET, kcalsET, proteinsET, fatsET, carbsET, fiberET, saturET, polyET, monoET, sugarET;
    private EditText vitaminAET;
    private EditText vitaminDET;
    private EditText vitaminEET;
    private EditText vitaminKET;
    private EditText vitaminB6ET;
    private EditText calciumET;
    private EditText ironET;
    private EditText magnesiumET;
    private EditText phosphorusET;
    private EditText zincET;
    private EditText copperET;
    private EditText chromiumET;
    private final String TAG = "AddFood";
    private final String BARCODE = "barcode";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_add_fragment);
        initView();

        addProductBTN.setOnClickListener(e -> {
            checkFieldsAndInsert();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_product_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backToSearchActivityItem:
                finish();
                startActivity(new Intent(this, SarchProductActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initView() {
        addProductBTN = (Button) findViewById(R.id.addProductInAddBTN);

        nameET = (EditText) findViewById(R.id.nameAddET);
        kcalsET = (EditText) findViewById(R.id.kcalsAddET);
        proteinsET = (EditText) findViewById(R.id.proteinsAddET);
        fatsET = (EditText) findViewById(R.id.fatAddET);
        carbsET = (EditText) findViewById(R.id.carbohydratesAddET);
        fiberET = (EditText) findViewById(R.id.fiberAddET);
        saturET = (EditText) findViewById(R.id.saturated_fatAddET);
        polyET = (EditText) findViewById(R.id.polyunsaturated_fatAddET);
        monoET = (EditText) findViewById(R.id.monounsaturated_fatAddET);
        sugarET = (EditText) findViewById(R.id.sugarAddET);
    }

    public String changeToDouble(EditText editText) {
        Double aDouble = 0.0;
        String data = editText.getText().toString();
        try {
            aDouble = Double.parseDouble(data);
        } catch (NumberFormatException nfe) {
            editText.setError(getResources().getString(R.string.badFormat));
        } catch (NullPointerException npe) {
            editText.setError(getResources().getString(R.string.emptySearchEditText));
        }
        return data;
    }

    public void checkFieldsAndInsert() {
        name = nameET.getText().toString();
        if (TextUtils.isEmpty(name)) {
            nameET.setError(getResources().getString(R.string.emptySearchEditText));
            return;
        }

        s_kcals = changeToDouble(kcalsET);
        if (s_kcals.matches(""))
            return;

        s_carbs = changeToDouble(carbsET);
        if (s_carbs.matches(""))
            return;

        s_fats = changeToDouble(fatsET);
        if (s_fats.matches(""))
            return;

        s_proteins = changeToDouble(proteinsET);
        if (s_proteins.matches(""))
            return;

        s_fibers = fiberET.getText().toString();
        if (TextUtils.isEmpty(s_fibers)) {
            s_fibers = String.valueOf(fibers);
        }


        s_satur = saturET.getText().toString();
        if (TextUtils.isEmpty(s_satur)) {
            s_satur = String.valueOf(satur);
        }

        s_poly = polyET.getText().toString();
        if (TextUtils.isEmpty(s_poly)) {
            s_poly = String.valueOf(poly);
        }

        s_mono = monoET.getText().toString();
        if (TextUtils.isEmpty(s_mono)) {
            s_mono = String.valueOf(mono);
        }

        s_sugar = sugarET.getText().toString();
        if (TextUtils.isEmpty(s_sugar)) {
            s_sugar = String.valueOf(sugar);
        }

        String url = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String barcodeValue = extras.getString(BARCODE);
            url = Constants.URL_INSERT_PRODUCT_TO_DATABASE_WITH_BARCODE + "?protein=" + s_proteins + "&carbohydrates=" + s_carbs + "&fiber=" + s_fibers + "&fat=" + s_fats + "&saturated_fat=" + s_satur + "&polyunsaturated_fat=" + s_poly + "&monounsaturated_fat=" + s_mono + "&sugar=" + s_sugar + "&kcal=" + s_kcals + "&name=" + name + "&barcode=" + barcodeValue;
        } else {
            url = Constants.URL_INSERT_PRODUCT_TO_DATABASE + "?protein=" + s_proteins + "&carbohydrates=" + s_carbs + "&fiber=" + s_fibers + "&fat=" + s_fats + "&saturated_fat=" + s_satur + "&polyunsaturated_fat=" + s_poly + "&monounsaturated_fat=" + s_mono + "&sugar=" + s_sugar + "&kcal=" + s_kcals + "&name=" + name;
        }
        Log.i("Url: ", url);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
        }, error -> {
            Log.e("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getString(R.string.added));
        adb.setMessage(getString(R.string.product_with_name) + " " + name + " " + getString(R.string.has_been_added_to_the_database));
        adb.setPositiveButton("Ok", (dialog, which) -> {
            finish();
        });
        adb.show();

    }

}
