package kamiltm.project_sm.training;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kamiltm.project_sm.R;

/**
 * Created by Kamil Lenartowicz on 01.01.2018.
 */

public class StopWatchFragment extends Fragment {

    private TextView textView;
    private Button start, pause, reset, lap;
    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    private Handler handler;
    private int Seconds, Minutes, MilliSeconds;
    private ListView listView;
    private String[] ListElements = new String[]{};
    private List<String> ListElementsArrayList;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.training_stopwatch_fragment, container, false);
        initViews(view);
        initList(view);
        handleInput();
        return view;
    }

    public void initViews(View view) {
        textView = view.findViewById(R.id.textView);
        start = view.findViewById(R.id.button);
        pause = view.findViewById(R.id.button2);
        reset = view.findViewById(R.id.button3);
        lap = view.findViewById(R.id.button4);
        listView = view.findViewById(R.id.listview1);
    }

    public void handleInput() {
        start.setOnClickListener(view -> {
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);
            reset.setEnabled(false);

        });

        pause.setOnClickListener(view -> {
            TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);
            reset.setEnabled(true);
        });

        reset.setOnClickListener(view -> {
            MillisecondTime = 0L;
            StartTime = 0L;
            TimeBuff = 0L;
            UpdateTime = 0L;
            Seconds = 0;
            Minutes = 0;
            MilliSeconds = 0;

            textView.setText("00:00:00");
            ListElementsArrayList.clear();
            adapter.notifyDataSetChanged();
        });

        lap.setOnClickListener(view -> {
            ListElementsArrayList.add(textView.getText().toString());
            adapter.notifyDataSetChanged();

        });

    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }
    };

    public void initList(View view) {
        handler = new Handler();

        ListElementsArrayList = new ArrayList<>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );

        listView.setAdapter(adapter);
    }

}
