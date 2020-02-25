package kamiltm.project_sm.diet.food;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.user.SimpleDividerItemDecoration;
import kamiltm.project_sm.user.Content;

/**
 * Created by Kamil Lenartowicz on 2018-01-17.
 */

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.MyViewHolder> {

    private List<Content> items;
    private int mExpandedPosition = -1;
    private Animation animationUp, animationDown;

    public FoodInfoAdapter(List<Content> items, Animation animationUp, Animation animationDown) {
        this.items = items;
        this.animationDown = animationDown;
        this.animationUp = animationUp;
    }

    public FoodInfoAdapter(List<Content> items) {
        this.items = items;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startTV, endTV;
        private LinearLayout mainLayout;

        public MyViewHolder(View view) {
            super(view);
            startTV = view.findViewById(R.id.startTV);
            endTV = view.findViewById(R.id.endTV);
            //   imageButton = view.findViewById(R.id.show_more_foodInfoIB);
            //   contentLayout = view.findViewById(R.id.content_food_info);
            mainLayout = view.findViewById(R.id.lljakisnatest);
        }
    }

    @Override
    public FoodInfoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_info_layout_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FoodInfoAdapter.MyViewHolder holder, int position) {
        Content item = items.get(position);
        holder.startTV.setText(item.getTitle());
        holder.endTV.setText(item.getContent());

        if (holder.startTV.getText().toString().equals(holder.itemView.getResources().getString(R.string.witamins)) ||
                holder.startTV.getText().toString().equals(holder.itemView.getResources().getString(R.string.fat_add)) ||
                holder.startTV.getText().toString().equals(holder.itemView.getResources().getString(R.string.minerals)) ||
                holder.startTV.getText().toString().equals(holder.itemView.getResources().getString(R.string.carbs_add))) {
            Drawable imgAdd = holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_add);
            Drawable imgUp = holder.itemView.getContext().getResources().getDrawable(R.drawable.ic_expand_less);
            imgUp.setBounds(0, 0, 60, 60);
            imgAdd.setBounds(0, 0, 60, 60);
            holder.startTV.setCompoundDrawables(imgAdd, null, null, null);
            holder.itemView.setOnClickListener(view -> {
                final TextView[] myTextViews = new TextView[item.getItems().size()];
                if ((holder.mainLayout).getChildCount() > 0)
                    (holder.mainLayout).removeAllViews();
                List<Content> listWithITems = new ArrayList<>();
                listWithITems.clear();
                RecyclerView rv = new RecyclerView(holder.itemView.getContext());
                FoodInfoAdapter adapter = new FoodInfoAdapter(item.getItems());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                rv.setLayoutManager(mLayoutManager);
                rv.setPadding(80, 0, 0, 0);
                rv.addItemDecoration(new SimpleDividerItemDecoration(holder.itemView.getContext()));
                holder.mainLayout.addView(rv);
                rv.setAdapter(adapter);
                if (holder.mainLayout.isShown()) {
                    holder.mainLayout.setVisibility(View.GONE);
                    //    holder.mainLayout.startAnimation(animationUp);
                    holder.startTV.setCompoundDrawables(imgAdd, null, null, null);
                } else {
                    holder.mainLayout.setVisibility(View.VISIBLE);
                    //     holder.mainLayout.startAnimation(animationDown);
                    holder.startTV.setCompoundDrawables(imgUp, null, null, null);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
