package kamiltm.project_sm;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import kamiltm.project_sm.diet.food.Food;
import kamiltm.project_sm.diet.food.Meal;
import kamiltm.project_sm.training.Training;

/**
 * Created by Kamil on 15.12.2018.
 */

//        Food potatoes = new Food("Ziemniaki", 77.0, 2.02, 17.47, 0.09);
//        Food chicken = new Food("Kurczak", 239.0, 21.5, 0.0, 1.3);
//        foodList.add(potatoes);
//        foodList.add(chicken);


public class FoodTest {

    @Test(expected = NullPointerException.class)
    public void createMealWithNoElements() {
        List<Food> foodList = new ArrayList<>();
        new Meal(new ArrayList<Food>(), "Obiad");
    }


}
