package kamiltm.project_sm.maps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.text.SimpleDateFormat;
import java.util.Date;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

public class MapsActivity extends Activity {
    /**
     * Request code for the autocomplete activity. This will be used to identify results from the
     * autocomplete activity in onActivityResult.
     */
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private final Integer USER_ID = SharedPrefManager.getInstance(this).getId();
    private TextView mPlaceDetailsText;
    private TextView mPlaceAttribution;
    private Button add_gym_place_BTN;
    private Date datee;
    private String name;
    private String date;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.maps_activity);

        // Open the autocomplete activity when the button is clicked.
        Button openButton = (Button) findViewById(R.id.open_button);
        add_gym_place_BTN = (Button) findViewById(R.id.add_gym_place_BTN);
        add_gym_place_BTN.setVisibility(View.GONE);
        add_gym_place_BTN.setOnClickListener(e -> {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getResources().getString(R.string.please_wait));
            datee = DateState.dateState;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.format(datee);
            postData(USER_ID, date, name);
        });
        openButton.setOnClickListener(view -> openAutocompleteActivity());

        // Retrieve the TextViews that will display details about the selected place.
        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);
    }

    private void postData(Integer users_id, String date, String name) {
        String url = Constants.URL_INSERT_PLACE_GYM + "?id_users=" + users_id + "&date=" + date + "&name=" + name;

        progressDialog.show();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            progressDialog.dismiss();
        }, error -> {
            progressDialog.dismiss();
            Log.d("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(this).addToRequestQueue(jsonObjectRequest);
        finish();
    }

    private void openAutocompleteActivity() {
        try {
            // The autocomplete activity requires Google Play Services to be available. The intent
            // builder checks this and throws an exception if it is not the case.
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            // Indicates that Google Play Services is either not installed or not up to dateState. Prompt
            // the user to correct the issue.
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            // Indicates that Google Play Services is not available and the problem is not easily
            // resolvable.
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e("qwe", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called after the autocomplete activity has finished to return its result.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result was from the autocomplete widget.
        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                // Get the user's selected place from the Intent.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("qwe", "Place Selected: " + place.getName());

                // Format the place's details and display them in the TextView.
                mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getAddress(), place.getPhoneNumber(),
                        place.getWebsiteUri()));
                name = (String) place.getName();
                add_gym_place_BTN.setVisibility(View.VISIBLE);

                // Display attributions if required.
                CharSequence attributions = place.getAttributions();
                if (!TextUtils.isEmpty(attributions)) {
                    mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
                } else {
                    mPlaceAttribution.setText("");
                }
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("qwe", "Error: Status = " + status.toString());
            } else if (resultCode == RESULT_CANCELED) {
                // Indicates that the activity closed before a selection was made. For example if
                // the user pressed the back button.
            }
        }
    }

    /**
     * Helper method to format information about a place nicely.
     */
    private static Spanned formatPlaceDetails(Resources res, CharSequence name,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e("qwe", res.getString(R.string.place_details, name, address, phoneNumber,
                websiteUri));
        if (websiteUri == null && phoneNumber.equals("")) {
            return Html.fromHtml(res.getString(R.string.place_details_no_website_no_phone, name, address));
        } else if (phoneNumber == null) {
            return Html.fromHtml(res.getString(R.string.place_details_no_phone, name, address, websiteUri));
        } else if (websiteUri == null) {
            return Html.fromHtml(res.getString(R.string.place_details_no_website, name, address, phoneNumber));
        }

        return Html.fromHtml(res.getString(R.string.place_details, name, address, phoneNumber,
                websiteUri));

    }
}
