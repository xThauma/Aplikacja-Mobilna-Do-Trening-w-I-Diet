package kamiltm.project_sm.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import kamiltm.project_sm.R;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameET, passwordET;
    private Button registerButton, loginButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_start_activity);
        checkIfLogged();
        initViews();
        handleInputs();

    }

    public void handleInputs() {
        ImageButton plButton = (ImageButton) findViewById(R.id.poland_flag_ib);
        plButton.setOnClickListener(e -> {
            changeLanguage("pl");
        });

        ImageButton enButton = (ImageButton) findViewById(R.id.england_flag_ib);
        enButton.setOnClickListener(e -> {
            changeLanguage("en");
        });


        loginButton.setOnClickListener(v -> {
            final String username = usernameET.getText().toString().trim();
            final String password = passwordET.getText().toString().trim();
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_LOGIN,
                    response -> {
                        progressDialog.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                SharedPrefManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                obj.getInt("id"),
                                                obj.getString("username"),
                                                obj.getString("email")
                                        );
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }

            };

            RequestHandler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(e -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    public void initViews() {
        usernameET = (EditText) findViewById(R.id.usernameLoginEt);
        passwordET = (EditText) findViewById(R.id.passwordLoginEt);
        loginButton = (Button) findViewById(R.id.loginBT);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
    }

    public void checkIfLogged() {
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
            return;
        }
    }



    public void changeLanguage(String language) {
        String languageToLoad = language;
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getBaseContext().getResources().getDisplayMetrics());
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
