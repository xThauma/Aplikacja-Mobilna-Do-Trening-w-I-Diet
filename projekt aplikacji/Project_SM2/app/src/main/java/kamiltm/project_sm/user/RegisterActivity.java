package kamiltm.project_sm.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameET, passwordET, repeatPasswordET, mailET, heightET, weightET, targetWeightET;
    private RadioGroup sexRG, weightRG;
    private ProgressDialog progressDialog;

    private String username, password, repeatPassword, email, height, weight, target_weight, weight_lose, sex, daily_kcal;
    private int counter = 0;
    private double kcal;
    private ViewPager viewPager;
    private int[] layouts;
    private TextView[] dots;
    private LinearLayout dotsLayout;
    private ViewPageAdapter viewPageAdapter;
    private Button next, skip;
    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.split_dots_layout);

        initViews();
        addBottomDots(0);
        viewPageAdapter = new ViewPageAdapter();
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        progressDialog = new ProgressDialog(this);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    usernameET = (EditText) findViewById(R.id.usernameET);
                    passwordET = (EditText) findViewById(R.id.password1);
                    repeatPasswordET = (EditText) findViewById(R.id.password2);
                    mailET = (EditText) findViewById(R.id.emailET);

                    username = usernameET.getText().toString();
                    password = passwordET.getText().toString();
                    repeatPassword = repeatPasswordET.getText().toString();
                    email = mailET.getText().toString();

                    if (TextUtils.isEmpty(username)) {
                        usernameET.setError(getResources().getString(R.string.username_failed));
                        return;
                    }
                    if (password.compareTo(repeatPassword) > 0) {
                        repeatPasswordET.setError(getResources().getString(R.string.bottom_password_failed));

                        return;
                    } else if (TextUtils.isEmpty(password) && TextUtils.isEmpty(repeatPassword)) {
                        passwordET.setError(getResources().getString(R.string.password_failed));
                        repeatPasswordET.setError(getResources().getString(R.string.password_failed));
                        return;
                    } else if (TextUtils.isEmpty(password)) {
                        passwordET.setError(getResources().getString(R.string.password_failed));
                        return;
                    }
                    if (TextUtils.isEmpty(email)) {
                        mailET.setError(getResources().getString(R.string.mail_failed));
                        return;
                    } else if (!isValidEmail(email)) {
                        mailET.setError(getResources().getString(R.string.mail_failed));
                        return;
                    }
                    viewPager.setCurrentItem(current);
                } else {
                    viewPager.setCurrentItem(current);
                    heightET = (EditText) findViewById(R.id.heightET);
                    weightET = (EditText) findViewById(R.id.weightET);
                    targetWeightET = (EditText) findViewById(R.id.targetWeightET);
                    sexRG = (RadioGroup) findViewById(R.id.rbGroupSex);
                    weightRG = (RadioGroup) findViewById(R.id.rbGroupLoseWeight);

                    height = heightET.getText().toString();
                    weight = weightET.getText().toString();
                    target_weight = targetWeightET.getText().toString();

                    if (TextUtils.isEmpty(height)) {
                        heightET.setError(getResources().getString(R.string.height_failed));
                        return;
                    }
                    if (TextUtils.isEmpty(weight)) {
                        weightET.setError(getResources().getString(R.string.weight_failed));
                        return;
                    }

                    switch (sexRG.getCheckedRadioButtonId()) {
                        case R.id.maleRB:
                            sex = getResources().getString(R.string.male);
                            kcal = 6.5 + (13.7 * Double.valueOf(weight)) + (5 * Double.valueOf(height)) + 800;
                            break;
                        default:
                            sex = getResources().getString(R.string.female);
                            kcal = 665 + (9.6 * Double.valueOf(weight)) + (1.85 * Double.valueOf(height)) + 600;
                    }

                    switch (weightRG.getCheckedRadioButtonId()) {
                        case R.id.quarterRB:
                            weight_lose = "0.25";
                            kcal -= 150;
                            break;
                        case R.id.halfRB:
                            weight_lose = "0.5";
                            kcal -= 300;
                            break;
                        case R.id.halfAndQuarterRB:
                            weight_lose = "0.75";
                            kcal -= 450;
                            break;
                        case R.id.oneRB:
                            weight_lose = "1";
                            kcal -= 600;
                        default:
                            weight_lose = "0";
                    }

                    daily_kcal = String.valueOf(kcal);

                    registerUser();
                }
            }
        });
    }

    private void registerUser() {
        progressDialog.setMessage(getString(R.string.registering_user));
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER,
                response -> {
                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    progressDialog.hide();
                    Log.d("TAG error: ", "" + error.getMessage());
                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("email", email);
                params.put("password", password);
                params.put("sex", sex);
                params.put("height", height);
                params.put("weight", weight);
                params.put("target_weight", target_weight);
                params.put("weight_lose", weight_lose);
                params.put("daily_kcal", daily_kcal);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        launchHomeScreen();

    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private void launchHomeScreen() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + 1;
    }

    private void initViews() {
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        next = (Button) findViewById(R.id.btn_next);
        skip = (Button) findViewById(R.id.btn_skip);
        layouts = new int[]{R.layout.split_1_layout, R.layout.split_2_layout};
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to FINISH
                next.setText(getString(R.string.start));
                skip.setVisibility(View.GONE);
            } else {
                // still pages are left
                next.setText(getString(R.string.next));
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class ViewPageAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public ViewPageAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


}
