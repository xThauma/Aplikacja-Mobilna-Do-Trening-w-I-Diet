package kamiltm.project_sm.diet.food;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 2017-12-29.
 */

public class DietFragment extends Fragment {
    private ImageButton summaryIB;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_diet_fragment, container, false);


        summaryIB = view.findViewById(R.id.summaryIB);
        summaryIB.setOnClickListener(e -> startActivity(new Intent(getContext(), WholeInfoActivity.class)));

        setToBreakfastFragment();
        setFragmentKcal(view);


        return view;
    }

    public static void setFragmentKcal(View v) {
        FragmentActivity activity = (FragmentActivity) v.getContext();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        KcalFragment hello = new KcalFragment();
        fragmentTransaction.replace(R.id.fragmentKcal, hello, "HELLO");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void setToBreakfastFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MealFragment fragment_container = new MealFragment();
        fragmentTransaction.replace(R.id.layoutToReplace, fragment_container);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
