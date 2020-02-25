package kamiltm.project_sm.home;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

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
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class HomeFragment extends Fragment {

    private List<Post> postsList = new ArrayList<>();
    protected RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private EditText title, content;
    private ImageButton save_post;
    private Button changeDateBTNleft, changeDateBTNright, changeDateBTN;
    private Date datee;
    private String date;
    private final Integer USER_ID = SharedPrefManager.getInstance(getContext()).getId();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initViews(view);
        handleInput();
        return view;
    }

    public void handleInput() {
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
        changeDateBTN.setText(date);

        changeDateBTNleft.setOnClickListener(e -> {
            Date newDate = new DateTime(datee).minusDays(1).toDate();
            datee = newDate;
            DateState.dateState = datee;
            date = sdf.format(newDate);
            changeDateBTN.setText(date);
            getData(USER_ID, date);
        });

        changeDateBTNright.setOnClickListener(e -> {
            Date newDate = new DateTime(datee).plusDays(1).toDate();
            datee = newDate;
            DateState.dateState = datee;
            date = sdf.format(newDate);
            changeDateBTN.setText(date);
            getData(USER_ID, date);
        });

        save_post.setOnClickListener((View e) -> {
            savePostData(title.getText().toString(), content.getText().toString(), USER_ID, date);
            Toast.makeText(getContext(), "Dodano nową wiadomość o tytule: \"" + title.getText().toString()+"\"", Toast.LENGTH_LONG).show();
        });
    }

    public void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycler_view);
        title = (EditText) view.findViewById(R.id.titleHomeFragment);
        content = (EditText) view.findViewById(R.id.contentHomeFragment);
        save_post = (ImageButton) view.findViewById(R.id.save_post_image_button);
        changeDateBTNleft = (Button) view.findViewById(R.id.changeDateBTNleftHome);
        changeDateBTNright = (Button) view.findViewById(R.id.changeDateBTNrightHome);
        changeDateBTN = (Button) view.findViewById(R.id.changeDateBTNHome);

        homeAdapter = new HomeAdapter(postsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(homeAdapter);

    }

    private void refreshList() {
        if (!postsList.isEmpty()) {
            postsList.removeAll(postsList);
            homeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setDate();
        changeDateBTN.setText(date);
        getData(USER_ID, date);
    }

    public void setDate() {
        datee = DateState.dateState;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(datee);
    }

    private void savePostData(String title, String content, Integer id_users, String date) {
        String url = Constants.URL_POSTDATAINSERT + "?title=" + title + "&date=" + date + "&content=" + content + "&id_users=" + id_users;
        Log.d("url: ", url);

        StringRequest sr = new StringRequest(Request.Method.POST, url, response -> {
            Log.d("Response: ", response.toString());
            getData(USER_ID, date);
        }, error -> {
            Log.d("Error: ", error.toString());
        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(sr);
    }

    private void getData(int id_users, String date) {
        String url = Constants.URL_POSTDATA + id_users + "&date=" + date;
        refreshList();

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    String id = post.getString("id");
                    String title = post.getString("title");
                    String dateString = post.getString("date");
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.set(Integer.valueOf(dateString.substring(0, 4)), Integer.valueOf(dateString.substring(5, 7)) - 1, Integer.valueOf(dateString.substring(8, 10)));
                    String content = post.getString("content");
                    String id_users1 = post.getString("id_users");
                    postsList.add(new Post(Long.valueOf(id), title, cal, content, Long.valueOf(id_users)));
                }
                homeAdapter.notifyDataSetChanged();
            } catch (JSONException je) {
                je.printStackTrace();
            }
        }, error -> {
            Log.d("ERROR", " " + error.toString());

        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }


}




