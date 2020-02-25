package kamiltm.project_sm.diet.food;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import info.androidhive.barcode.BarcodeReader;
import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.MealState;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private ProgressDialog progressDialog;
    private BarcodeReader barcodeReader;
    private final String BARCODE = "barcode";
    private Date datee;
    private String date;
    private final Integer USER_ID = SharedPrefManager.getInstance(this).getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_scan_activity);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {
        barcodeReader.playBeep();
        getData(barcode);
        Log.d("Code", " " + barcode.displayValue);
    }


    public void getData(Barcode barcode) {
        String url = Constants.URL_PRODUCT_DATA_WITH_BARCODE + "?barcode=" + barcode.displayValue;
        Log.d("Url", url);
        progressDialog.show();
        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, url, null, (JSONArray response) -> {
            progressDialog.dismiss();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    if (id != null) {
                        productFound(id);
                    } else {
                        productNotFound(barcode);
                    }
                }
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> progressDialog.dismiss());
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void productFound(String id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(this);
        adb.setTitle(getResources().getString(R.string.add_question));
        adb.setMessage(getResources().getString(R.string.add_product_to_the_database));
        adb.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> {
            finish();
        });
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        edittext.setLayoutParams(lp);
        adb.setView(edittext);
        adb.setPositiveButton("Ok", (DialogInterface dialog, int which) -> {
            datee = DateState.dateState;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(datee);

            String portionOfFood = edittext.getText().toString();
            if (portionOfFood.matches("")) {
                portionOfFood = "1";
            }
            String url_breakfast = Constants.URL_INSERT_BREAKFAST_DATA + "?date=" + date + "&id_users=" + USER_ID + "&id_products=" + id + "&portion=" + portionOfFood;
            String url_dinner = Constants.URL_INSERT_DINNER_DATA + "?date=" + date + "&id_users=" + USER_ID + "&id_products=" + id + "&portion=" + portionOfFood;
            String url_supper = Constants.URL_INSERT_SUPPER_DATA + "?date=" + date + "&id_users=" + USER_ID + "&id_products=" + id + "&portion=" + portionOfFood;

            progressDialog.show();
            if (MealState.meal_state == 1 || MealState.meal_state == 0) {
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_breakfast, null, response -> {
                    progressDialog.dismiss();
                }, error -> {
                    progressDialog.dismiss();
                    Log.e("ERROR", " " + error.toString());
                });
                RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
            } else if (MealState.meal_state == 2) {
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_dinner, null, response -> {
                    progressDialog.dismiss();
                }, error -> {
                    progressDialog.dismiss();
                    Log.e("ERROR", " " + error.toString());
                });
                RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
            } else if (MealState.meal_state == 3) {
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url_supper, null, response -> {
                    progressDialog.dismiss();
                }, error -> {
                    progressDialog.dismiss();
                    Log.e("ERROR", " " + error.toString());
                });
                RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
            }
            progressDialog.dismiss();

            finish();

        });
        adb.show();
    }

    public void productNotFound(Barcode barcode) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getResources().getString(R.string.add_question));
        adb.setMessage(getResources().getString(R.string.not_found));
        adb.setNegativeButton(getResources().getString(R.string.cancel), (dialog, which) -> {
            finish();
        });

        adb.setPositiveButton("Ok", (dialog, which) -> {
            Intent intent = new Intent(this, AddFoodActivity.class);
            intent.putExtra(BARCODE, String.valueOf(barcode.displayValue));
            startActivity(intent);
        });
        adb.show();
    }


    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onCameraPermissionDenied() {
        finish();
    }

    @Override
    public void onScanError(String s) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
    }
}