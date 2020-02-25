package kamiltm.project_sm.Supplement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kamiltm.project_sm.R;

public class SupplementFragment extends Fragment {
    List<Supplement> supplementList;
    SupplementAdapter supplementAdapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.supplement_list_fragment, null);
        initViews(view);
        addElementsToList();
        return view;
    }

    private void initViews(View view) {
        supplementList = new ArrayList<>();
        supplementAdapter = new SupplementAdapter(supplementList);
        recyclerView = view.findViewById(R.id.supplementRV);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(supplementAdapter);
    }

    private void addElementsToList() {
        if (!supplementList.isEmpty()) {
            supplementList.removeAll(supplementList);
        }
        supplementList.add(new Supplement(getString(R.string.creatine), getString(R.string.creatineDesc), R.drawable.kreatyna));
        supplementList.add(new Supplement(getString(R.string.bcaa), getString(R.string.bcaaDesc), R.drawable.bcaa));
        supplementList.add(new Supplement(getString(R.string.zma), getString(R.string.zmaDesc), R.drawable.zma));
        supplementAdapter.notifyDataSetChanged();
    }


}
