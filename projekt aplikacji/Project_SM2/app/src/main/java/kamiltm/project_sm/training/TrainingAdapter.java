package kamiltm.project_sm.training;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 01.01.2018.
 */

public class TrainingAdapter extends RecyclerView.Adapter<TrainingAdapter.MyViewHolder> {

    private List<Training> trainingList;
    private ProgressDialog progressDialog;
    private Date datee;
    private String date;
    private String portionOfFood;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, contentNoS, contentNoP;
        public ImageButton delete_button;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name_of_exerciseTV);
            contentNoS = (TextView) view.findViewById(R.id.training_contentTVNoS);
            delete_button = (ImageButton) view.findViewById(R.id.delete_trainingBTN);
            contentNoP = view.findViewById(R.id.training_contentTVNoP);
        }
    }


    public TrainingAdapter(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    @Override
    public TrainingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_plan_row, parent, false);

        return new TrainingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrainingAdapter.MyViewHolder holder, int position) {
        Training training = trainingList.get(position);

        holder.title.setText(training.getName());
        holder.contentNoP.setText(String.valueOf(training.getNumber_of_reps()));
        holder.contentNoS.setText(String.valueOf(training.getNumber_of_series()));

        holder.delete_button.setOnClickListener(e -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(holder.itemView.getContext());
            final EditText edittext = new EditText(holder.itemView.getContext());
            adb.setTitle(holder.itemView.getResources().getString(R.string.delete_question));
            adb.setMessage(holder.itemView.getResources().getString(R.string.delete_training_with_name) + " \"" + training.getName() + "\"?");
            adb.setNegativeButton(holder.itemView.getResources().getString(R.string.cancel), null);
            adb.setPositiveButton("Ok", (dialog, which) -> {
                progressDialog = new ProgressDialog(holder.itemView.getContext());
                progressDialog.setMessage(holder.itemView.getResources().getString(R.string.please_wait));

                datee = DateState.dateState;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.format(datee);

                int users_id = SharedPrefManager.getInstance(holder.itemView.getContext()).getId();

                String url = Constants.URL_DELETE_TAINING + "?id_users=" + users_id + "&name=" + training.getName() + "&date=" + date;
                Log.d("URL: ", " " + url.toString());

                progressDialog.show();
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
                    progressDialog.dismiss();
                }, error -> {
                    progressDialog.dismiss();
                    Log.d("ERROR", " " + error.toString());
                });
                RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);


                try {
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    fragmentClass = TrainingFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.layoutInTrainingToReplace, fragment).commit();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            });
            adb.show();

        });
    }

    @Override
    public int getItemCount() {
        return trainingList.size();
    }

}
