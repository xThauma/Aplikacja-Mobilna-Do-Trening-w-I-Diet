package kamiltm.project_sm.user;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.constants.Constants;

/**
 * Created by Master on 01.01.2018.
 */

public class UserInformationsFragment extends Fragment {

    private TextView usernameTV;
    private String username, email, sex, weight, height, weight_lose, target_weight, daily_kcals;
    private final Integer USER_ID = SharedPrefManager.getInstance(getContext()).getId();
    private ProgressDialog progressDialog;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<Content> itemsWithUserInfo;

    private RecyclerView recyclerViewMacros;
    private UserMacrosAdapter userAdapterMacros;
    private List<Content> itemsWithUserMacros;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_layout, container, false);
        initViews(view);
        getData(USER_ID);
        return view;
    }

    public void getData(Integer id) {
        String url = Constants.URL_GET_USER_INFO + id.toString();

        progressDialog.show();
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            progressDialog.dismiss();
            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject post = response.getJSONObject(i);
                    username = post.getString("username");
                    email = post.getString("email");
                    sex = post.getString("sex");
                    weight = post.getString("weight");
                    height = post.getString("height");
                    weight_lose = post.getString("weight_lose");
                    target_weight = post.getString("target_weight");
                    daily_kcals = post.getString("daily_kcal");
                }
            } catch (JSONException je) {
                progressDialog.dismiss();
                je.printStackTrace();
            }
            itemsWithUserInfo.add(new Content("E-mail", email));
            itemsWithUserInfo.add(new Content("Płeć", "Mężczyzna"));
            itemsWithUserInfo.add(new Content("Waga", weight + " kg"));
            itemsWithUserInfo.add(new Content("Wzrost", height + " cm"));
            itemsWithUserInfo.add(new Content("Utrata wagi", weight_lose + " kg na tydzień"));
            itemsWithUserInfo.add(new Content("Waga docelowa", target_weight + " kg"));
            itemsWithUserMacros.add(new Content("Dzienne kalorie", String.valueOf(String.format("%.0f", Double.valueOf(daily_kcals)))));
            itemsWithUserMacros.add(new Content("Dzienne białko", "176g"));
            itemsWithUserMacros.add(new Content("Dzienne tłuszcze", "79g"));
            itemsWithUserMacros.add(new Content("Dzienne węglowodany", "237g"));
            ;
            usernameTV.setText(username);
            userAdapterMacros.notifyDataSetChanged();
            userAdapter.notifyDataSetChanged();
        }, error -> {
            Log.d("ERROR", " " + error.toString());
        });
        RequestHandler.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);


    }


    public void initViews(View view) {
        usernameTV = view.findViewById(R.id.user_info_usernameTV);

        recyclerView = view.findViewById(R.id.user_infoRV);
        itemsWithUserInfo = new ArrayList<>();
        userAdapter = new UserAdapter(itemsWithUserInfo);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setAdapter(userAdapter);

        recyclerViewMacros = view.findViewById(R.id.user_infoMacrosRV);
        itemsWithUserMacros = new ArrayList<>();
        userAdapterMacros = new UserMacrosAdapter(itemsWithUserMacros);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getContext());
        recyclerViewMacros.setLayoutManager(mLayoutManager2);
        recyclerViewMacros.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerViewMacros.setAdapter(userAdapterMacros);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getResources().getString(R.string.please_wait));
    }


}
