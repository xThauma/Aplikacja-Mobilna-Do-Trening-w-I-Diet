package kamiltm.project_sm.training;

import java.util.GregorianCalendar;

/**
 * Created by Kamil Lenartowicz on 01.01.2018.
 */

public class Training {

    private Integer id;
    private String name;
    private Integer number_of_reps;
    private Integer number_of_series;
    private GregorianCalendar date;

    public Training(Integer id, String name, Integer number_of_reps, Integer number_of_series, GregorianCalendar date) {
        this.id = id;
        this.name = name;
        this.number_of_reps = number_of_reps;
        this.number_of_series = number_of_series;
        this.date = date;
    }

    public Training(String name, Integer number_of_reps, Integer number_of_series, GregorianCalendar date) {
        this.name = name;
        this.number_of_reps = number_of_reps;
        this.number_of_series = number_of_series;
        this.date = date;
    }

    public Training(String name, Integer number_of_reps, Integer number_of_series) {
        this.name = name;
        this.number_of_reps = number_of_reps;
        this.number_of_series = number_of_series;
    }

    @Override
    public String toString() {
        return "Training{" +
                "name='" + name + '\'' +
                ", number_of_reps=" + number_of_reps +
                ", number_of_series=" + number_of_series +
                ", date=" + date +
                '}';
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber_of_reps() {
        return number_of_reps;
    }

    public void setNumber_of_reps(Integer number_of_reps) {
        this.number_of_reps = number_of_reps;
    }

    public Integer getNumber_of_series() {
        return number_of_series;
    }

    public void setNumber_of_series(Integer number_of_series) {
        this.number_of_series = number_of_series;
    }
}
