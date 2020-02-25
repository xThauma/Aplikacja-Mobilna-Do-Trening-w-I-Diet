package kamiltm.project_sm.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 2018-01-17.
 */

public class UserMacrosAdapter extends RecyclerView.Adapter<UserMacrosAdapter.MyViewHolder> {

    private List<Content> items;

    public UserMacrosAdapter(List<Content> items) {
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startTV;
        public EditText endTV;

        public MyViewHolder(View view) {
            super(view);
            startTV = view.findViewById(R.id.start_macrosTV);
            endTV = view.findViewById(R.id.end_macroTV);
        }
    }

    @Override
    public UserMacrosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info_layout_row_macros, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserMacrosAdapter.MyViewHolder holder, int position) {
        Content item = items.get(position);
        holder.startTV.setText(item.getTitle());
        holder.endTV.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
