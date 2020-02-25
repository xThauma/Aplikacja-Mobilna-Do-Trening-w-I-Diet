package kamiltm.project_sm.training;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.maps.MapsActivity;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 01.01.2018.
 */

public class TrainingListFragment extends Fragment {

    private List<Training> trainingList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TrainingAdapter trainingAdapter;
    private final Integer USER_ID = SharedPrefManager.getInstance(getContext()).getId();
    private ProgressDialog progressDialog;
    private Date datee;
    private String date, gym_name = "";
    private Button changeDateBTNleft, changeDateBTNright, changeDateBTN, addTrainingBTN, placeOfTrainingBTN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_plan_fragment, container, false);
        initViews(view);
        handleButtons(view);
        return view;
    }

    public void getData(Integer id, String date) {
        clearList();
        String url = Constants.URL_USER_TRAININGS + "?id_users=" + id + "&date=" + date;

        progressDialog.show();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            progressDialog.dismiss();
            try {
                for (int i = 0; i < response.length(); i++) {
                    Log.d("RESPONSE: ", response.toString());
                    JSONObject post = response.getJSONObject(i);
                    String idTraining = post.getString("id");
                    String name = post.getString("name");
                    String reps = post.getString("reps");
                    String series = post.getString("series");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));

                    Training training = new Training(Integer.valueOf(idTraining), name, Integer.valueOf(reps), Integer.valueOf(series), cal);
                    Log.d("Training: ", training.toString());
                    trainingList.add(training);
                }
                trainingAdapter.notifyDataSetChanged();
            } catch (JSONException je) {
                progressDialog.dismiss();
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    public void clearList() {
        if (!trainingList.isEmpty()) {
            trainingList.removeAll(trainingList);
            trainingAdapter.notifyDataSetChanged();
        }
    }

    public void handleButtons(View view) {
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
        changeDateBTN.setText(date);

        addTrainingBTN.setOnClickListener(e -> {
            startActivity(new Intent(getContext(), NewTrainingActivity.class));
        });

        changeDateBTNleft.setOnClickListener(e -> {
            Date newDate = new DateTime(datee).minusDays(1).toDate();
            datee = newDate;
            DateState.dateState = datee;
            date = sdf.format(newDate);
            changeDateBTN.setText(date);
            clearList();
            getData(USER_ID, date);
            getGymData(USER_ID, date);
        });

        changeDateBTNright.setOnClickListener(e -> {
            Date newDate = new DateTime(datee).plusDays(1).toDate();
            datee = newDate;
            DateState.dateState = datee;
            date = sdf.format(newDate);
            changeDateBTN.setText(date);
            clearList();
            getData(USER_ID, date);
            getGymData(USER_ID, date);
        });

        placeOfTrainingBTN.setOnClickListener(view1 -> {
            if (placeOfTrainingBTN.getText().toString().equals(getResources().getString(R.string.add_gym))) {
                Intent n = new Intent(getActivity(), MapsActivity.class);
                startActivity(n);
            } else {
                return;
            }

        });

    }

    public void getGymData(Integer id_users, String date) {
        String url = Constants.URL_GET_PLACE_GYM_DATA + "?id_users=" + id_users + "&date=" + date;
        Log.d("URL: ", url.toString());

        gym_name = getResources().getString(R.string.add_gym);
        placeOfTrainingBTN.setText(gym_name);
        progressDialog.show();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            progressDialog.dismiss();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    gym_name = post.getString("name");
                    placeOfTrainingBTN.setText(gym_name);
                }
            } catch (JSONException je) {
                progressDialog.dismiss();
                je.printStackTrace();
            }
        }, error -> {
            progressDialog.dismiss();
            Log.d("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
        getGymData(USER_ID, date);
        getData(USER_ID, date);
    }

    public void initViews(View view) {
        JodaTimeAndroid.init(getContext());

        addTrainingBTN = (Button) view.findViewById(R.id.add_trainingBTN);
        changeDateBTNleft = (Button) view.findViewById(R.id.changeDateBTNleftTraining);
        changeDateBTNright = (Button) view.findViewById(R.id.changeDateBTNrightTraining);
        changeDateBTN = (Button) view.findViewById(R.id.changeDateBTNTraining);
        recyclerView = (RecyclerView) view.findViewById(R.id.training_planRV);
        placeOfTrainingBTN = (Button) view.findViewById(R.id.gym_place);

        trainingAdapter = new TrainingAdapter(trainingList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(trainingAdapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
    }

}
