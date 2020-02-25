package kamiltm.project_sm.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 2018-01-17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<Content> items;

    public UserAdapter(List<Content> items) {
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startTV, endTV;

        public MyViewHolder(View view) {
            super(view);
            startTV = view.findViewById(R.id.startTV);
            endTV = view.findViewById(R.id.endTV);
        }
    }

    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_info_layout_row, parent, false);
        return new MyViewHolder(itemView);
    }

    public String stringFormat(Double value) {
        String pattern = "##0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(value);
        return format;
    }

    @Override
    public void onBindViewHolder(UserAdapter.MyViewHolder holder, int position) {
        Content item = items.get(position);
        holder.startTV.setText(item.getTitle());
        holder.endTV.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
