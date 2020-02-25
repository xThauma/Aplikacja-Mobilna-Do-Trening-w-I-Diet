package kamiltm.project_sm.constants;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class Constants {
    public static final String ROOT_URL = "http://192.168.12.1/sm/v1/";
    public static final int BREAKFAST_STATE = 1;
    public static final int DINNER_STATE = 2;
    public static final int SUPPER_STATE = 3;
    public static final int LUNCH_STATE = 4;

    public static final String URL_REGISTER = ROOT_URL + "registerUser.php";
    public static final String URL_LOGIN = ROOT_URL + "userLogin.php";
    public static final String URL_POSTDATA = ROOT_URL + "postData.php?id_users=";
    public static final String URL_POSTDATAINSERT = ROOT_URL + "postDataInsert.php";
    public static final String URL_GETUSERDAILYKCAL = ROOT_URL + "kcalAmount.php?id=";
    public static final String URL_GETUSERBREAKFAST = ROOT_URL + "breakfastData.php?id_users=";
    public static final String URL_GETUSERDINNER = ROOT_URL + "dinnerData.php?id_users=";
    public static final String URL_GETUSERSUPPER = ROOT_URL + "supperData.php?id_users=";
    public static final String URL_GETUSERLUNCH = ROOT_URL + "lunchData.php?id_users=";
    public static final String URL_UPDATEKCAL = ROOT_URL + "updateKcal.php?id=";

    public static final String URL_PRODUCTDATA = ROOT_URL + "productData.php?name=";
    public static final String URL_DELETEPRODUCT = ROOT_URL + "deleteBreakfastData.php?id=";
    public static final String URL_DELETEPRODUCTDINNER = ROOT_URL + "deleteDinnerData.php?id=";
    public static final String URL_DELETEPRODUCTSUPPER = ROOT_URL + "deleteSupperData.php?id=";
    public static final String URL_DELETEPRODUCTLUNCH = ROOT_URL + "deleteLunchData.php?id=";

    public static final String URL_INSERT_PRODUCT_TO_DATABASE = ROOT_URL + "productDataInsert.php";

    public static final String URL_INSERT_BREAKFAST_DATA = ROOT_URL + "breakfastDataInsert.php";
    public static final String URL_INSERT_DINNER_DATA = ROOT_URL + "dinnerDataInsert.php";
    public static final String URL_INSERT_SUPPER_DATA = ROOT_URL + "supperDataInsert.php";
    public static final String URL_INSERT_LUNCH_DATA = ROOT_URL + "lunchDataInsert.php";

    public static final String URL_INSERT_BREAKFAST_FAVOURITE_DATA = ROOT_URL + "breakfastDataInsertFavourite.php";
    public static final String URL_INSERT_LUNCH_FAVOURITE_DATA = ROOT_URL + "lunchDataInsertFavourite.php";
    public static final String URL_INSERT_DINNER_FAVOURITE_DATA = ROOT_URL + "dinnertDataInsertFavourite.php";
    public static final String URL_INSERT_SUPPER_FAVOURITE_DATA = ROOT_URL + "supperDataInsertFavourite.php";

    public static final String URL_GET_USER_INFO = ROOT_URL + "userInfo.php?id=";

    public static final String URL_DELETE_TAINING = ROOT_URL + "deleteTraining.php";

    public static final String URL_USER_TRAININGS = ROOT_URL + "trainingInfo.php";

    public static final String URL_INSERT_TRAINING_DATA = ROOT_URL + "trainingDataInsert.php";

    public static final String URL_INSERT_PLACE_GYM = ROOT_URL + "gymDataInsert.php";

    public static final String URL_GET_PLACE_GYM_DATA = ROOT_URL + "gymInfo.php";

    public static final String URL_DELETE_POST = ROOT_URL + "deletePost.php?id=";

    public static final String URL_PRODUCT_DATA_WITH_BARCODE = ROOT_URL + "productDataWithBarcode.php";

    public static final String URL_INSERT_PRODUCT_TO_DATABASE_WITH_BARCODE = ROOT_URL + "productDataInsertWithBarcode.php";

    public static final String URL_GET_WHOLE_DATA = ROOT_URL + "wholeData.php?id_users=";

    public static final String URL_GET_LAST_MEALS = ROOT_URL + "lastAddedProducts.php?id_users=";

    public static final String URL_GET_FAVS = ROOT_URL + "favProducts.php?id_users=";

    public static final String URL_SAVE_MEAL = ROOT_URL + "saveMeal.php?id_users=";

    public static final String URL_UPDATE_BREAKFAST_PRODUCT = ROOT_URL + "updateBreakfastProduct.php";
    public static final String URL_UPDATE_DINNER_PRODUCT = ROOT_URL + "updateDinnerProduct.php";
    public static final String URL_UPDATE_SUPPER_PRODUCT = ROOT_URL + "updateSupperProduct.php";
    public static final String URL_UPDATE_LUNCH_PRODUCT = ROOT_URL + "updateLunchProduct.php";

    public static final String URL_INSERT_BREAKFAST_MEAL = ROOT_URL + "insertBreakfastMeal.php";
    public static final String URL_INSERT_DINNER_MEAL = ROOT_URL + "isnertDinnerMeal.php";
    public static final String URL_INSERT_SUPPER_MEAL = ROOT_URL + "insertSupperMeal.php";
    public static final String URL_INSERT_LUNCH_MEAL = ROOT_URL + "insertLunchMeal.php";

    public static final String URL_GET_MEALS_DATA = ROOT_URL + "getMealsData.php";

    public static final String URL_GET_PRODUCT_WITH_ID = ROOT_URL + "getProduct.php";

}
