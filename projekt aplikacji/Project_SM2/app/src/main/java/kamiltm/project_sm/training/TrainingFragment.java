package kamiltm.project_sm.training;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 01.01.2018.
 */

public class TrainingFragment extends Fragment {

    private Button trainingListBTN, stopWatchBTN;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_fragment, container, false);
        initViews(view);
        handleInput();
        return view;
    }

    public void handleInput() {
        trainingListBTN.setOnClickListener(e -> {
            setToTrainingListFragment();
        });

        stopWatchBTN.setOnClickListener(e -> {
            setToStopWatchFragment();
        });
    }

    public void initViews(View view) {
        trainingListBTN = (Button) view.findViewById(R.id.start_training_plan_fragmentBTN);
        stopWatchBTN = (Button) view.findViewById(R.id.start_stopwatch_fragmentBTN);

        setToTrainingListFragment();
    }

    private void setToTrainingListFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TrainingListFragment fragment_container = new TrainingListFragment();
        fragmentTransaction.replace(R.id.layoutInTrainingToReplace, fragment_container);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void setToStopWatchFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        StopWatchFragment fragment_container = new StopWatchFragment();
        fragmentTransaction.replace(R.id.layoutInTrainingToReplace, fragment_container);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
