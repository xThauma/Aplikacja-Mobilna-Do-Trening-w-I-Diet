package kamiltm.project_sm.home;

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
import java.util.GregorianCalendar;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private List<Post> postList;
    private ProgressDialog progressDialog;
    private Date datee;
    private String date;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, content;
        public ImageButton delete_button;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
            delete_button = (ImageButton) view.findViewById(R.id.delete_postBTN);
        }
    }


    public HomeAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public static String format(GregorianCalendar calendar){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MMM-dd");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.content.setText(post.getContent());

        holder.delete_button.setOnClickListener(e -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(holder.itemView.getContext());
            final EditText edittext = new EditText(holder.itemView.getContext());
            adb.setTitle(holder.itemView.getResources().getString(R.string.delete_question));
            adb.setMessage(holder.itemView.getResources().getString(R.string.delete_post_with_name) + " \"" + post.getTitle() + "\"?");
            adb.setNegativeButton(holder.itemView.getResources().getString(R.string.cancel), null);
            adb.setPositiveButton("Ok", (dialog, which) -> {
                progressDialog = new ProgressDialog(holder.itemView.getContext());
                progressDialog.setMessage(holder.itemView.getResources().getString(R.string.please_wait));

                Long postId = post.getId();

                String url = Constants.URL_DELETE_POST + postId;
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
                    fragmentClass = HomeFragment.class;
                    fragment = (Fragment) fragmentClass.newInstance();
                    FragmentManager fragmentManager = ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                } catch (Exception er) {
                    er.printStackTrace();
                }
            });
            adb.show();

        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }





}
