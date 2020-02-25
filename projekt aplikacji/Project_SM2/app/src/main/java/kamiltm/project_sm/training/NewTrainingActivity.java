package kamiltm.project_sm.training;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

public class NewTrainingActivity extends AppCompatActivity {

    private Button add_training_btn, go_back_btn;
    private String s_reps, s_series, s_name, date;
    private EditText name_et, reps_et, series_et;
    private Date datee;
    private final Integer USER_ID = SharedPrefManager.getInstance(this).getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_add_fragment);
        initViews();
        handleInput();
    }

    public void handleInput() {
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);

        go_back_btn.setOnClickListener(e -> {
            finish();
        });

        add_training_btn.setOnClickListener(e -> {
            s_name = name_et.getText().toString();
            s_reps = reps_et.getText().toString();
            s_series = series_et.getText().toString();

            checkString(s_name, name_et);
            checkString(s_reps, reps_et);
            checkString(s_series, series_et);

            postData(USER_ID, date);
        });

    }

    public void postData(Integer users_id, String date) {
        String url = Constants.URL_INSERT_TRAINING_DATA + "?id_users=" + users_id + "&date=" + date + "&name=" + s_name + "&reps=" + s_reps + "&series=" + s_series;

        Log.d("Url: ", url);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
        }, error -> {
            Log.d("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(getString(R.string.added));
        adb.setMessage(getString(R.string.exercise_with_name) + " " + s_name + " " + getString(R.string.has_been_added_to_the_database));
        adb.setPositiveButton("Ok", (dialog, which) -> {
            finish();
        });
        adb.show();
    }

    public void checkString(String name, EditText editText) {
        if (TextUtils.isEmpty(name)) {
            editText.setError(getResources().getString(R.string.emptySearchEditText));
            return;
        }
    }

    public void initViews() {
        add_training_btn = findViewById(R.id.add_training_btnADD);
        go_back_btn = findViewById(R.id.go_trainings_btn);
        name_et = findViewById(R.id.exercise_name_et);
        reps_et = findViewById(R.id.number_of_reps_et);
        series_et = findViewById(R.id.number_of_series_et);
    }

}
