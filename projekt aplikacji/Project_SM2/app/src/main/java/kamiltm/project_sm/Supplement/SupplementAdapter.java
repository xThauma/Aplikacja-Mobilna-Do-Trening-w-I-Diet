package kamiltm.project_sm.Supplement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class SupplementAdapter extends RecyclerView.Adapter<SupplementAdapter.MyViewHolder> {

    private List<Supplement> supplementList;
    public static final String TAG = "SupplementAdapter.class";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public ImageView image;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.suplementNameTV);
            desc = view.findViewById(R.id.suplementDescTV);
            image = view.findViewById(R.id.suplementIV);
        }
    }


    public SupplementAdapter(List<Supplement> supplementList) {
        this.supplementList = supplementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suplement_row_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Supplement supplement = supplementList.get(position);
        holder.title.setText(supplement.getTitle());
        holder.desc.setText(supplement.getDesc());
        holder.image.setImageResource(supplement.getResource());
    }


    @Override
    public int getItemCount() {
        return supplementList.size();
    }


}
