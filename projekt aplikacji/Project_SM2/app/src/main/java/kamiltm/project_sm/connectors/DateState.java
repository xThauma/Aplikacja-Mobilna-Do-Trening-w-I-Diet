package kamiltm.project_sm.connectors;

import java.util.Date;

/**
 * Created by Kamil Lenartowicz on 2017-12-30.
 */

public class DateState {

    public static Date dateState = new Date();

    public static Date getDateState() {
        return dateState;
    }

    public static void setDateState(Date dateState) {
        DateState.dateState = dateState;
    }
}
