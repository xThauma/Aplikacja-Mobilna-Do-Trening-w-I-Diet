package kamiltm.project_sm.diet.food;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Kamil Lenartowicz on 2018-01-02.
 */

public class CustomPercentFormatter  implements ValueFormatter {
    private DecimalFormat mFormat;

    public CustomPercentFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0");
    }

    public CustomPercentFormatter(DecimalFormat format) {
        this.mFormat = format;
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {

        if (value == 0.0f)
            return "";

        return mFormat.format(value) + " %";
    }

}
