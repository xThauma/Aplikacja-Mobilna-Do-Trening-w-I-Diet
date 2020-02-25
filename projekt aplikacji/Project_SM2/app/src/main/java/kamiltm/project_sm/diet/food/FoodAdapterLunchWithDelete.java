package kamiltm.project_sm.diet.food;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import java.text.DecimalFormat;
import java.util.List;

import kamiltm.project_sm.R;
import kamiltm.project_sm.connectors.MealState;
import kamiltm.project_sm.constants.Constants;
import kamiltm.project_sm.user.RequestHandler;
import kamiltm.project_sm.user.SharedPrefManager;

/**
 * Created by Kamil Lenartowicz on 2017-12-27.
 */

public class FoodAdapterLunchWithDelete extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Food> foodList;
    private ProgressDialog progressDialog;
    private static final String FOOD_OBJECT = "food";
    private static final int FOOTER_VIEW = 1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(int position) {
        }
    }

    public class MyViewHolder extends ViewHolder {
        public TextView title, calories, proteins, carbs, fats;
        public ImageButton delete_item, likeFoodBTN;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.foodNameTV);
            calories = view.findViewById(R.id.foodContentCalories);
            proteins = view.findViewById(R.id.foodContentProteins);
            fats = view.findViewById(R.id.foodContentFats);
            carbs = view.findViewById(R.id.foodContentCarbs);
            delete_item = view.findViewById(R.id.deleteFoodBTN);
            likeFoodBTN = view.findViewById(R.id.likeFoodBTN);
        }
    }

    public class FooterViewHolder extends ViewHolder {
        public ImageButton addProductBTN;
        public TextView footerRvTV;

        public FooterViewHolder(View view) {
            super(view);

            addProductBTN = view.findViewById(R.id.addProdcutToRvBTN);
            footerRvTV = view.findViewById(R.id.footerRvTV);
        }
    }


    public FoodAdapterLunchWithDelete(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == FOOTER_VIEW) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_info_row_add_button, parent, false);
            return new FooterViewHolder(itemView);
        }
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_row_with_delete, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == foodList.size()) {
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    public String stringFormat(Double value, Double portion) {
        if (portion != 0) {
            value *= portion;
        }
        String pattern = "##0.##";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        String format = decimalFormat.format(value);
        return format;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder instanceof MyViewHolder) {
                MyViewHolder vh = (MyViewHolder) holder;
                vh.bindView(position);


                Food food = foodList.get(position);
                Double multiplier = food.getPortion();
                if (multiplier == null) {
                    multiplier = 1.0;
                }

                Double portion = multiplier * 100;
                vh.title.setText(food.getName());
                vh.carbs.setText(stringFormat(food.getCarbohydrates(), food.getPortion()));
                vh.fats.setText(stringFormat(food.getFat(), food.getPortion()));
                vh.proteins.setText(stringFormat(food.getProtein(), food.getPortion()));

                String kcals = holder.itemView.getResources().getString(R.string.kcals);
                String proteins = holder.itemView.getResources().getString(R.string.proteins);
                String carbs = holder.itemView.getResources().getString(R.string.carbohydrates);
                String fats = holder.itemView.getResources().getString(R.string.fats);

                Double d_kcals = food.getKcal() * multiplier;
                Double d_proteins = food.getProtein() * multiplier;
                Double d_carbs = food.getCarbohydrates() * multiplier;
                Double d_fats = food.getFat() * multiplier;

                String message = kcals + " " + d_kcals + "g, " + proteins + " " + d_proteins + "g, " + carbs + " " + d_carbs + "g, " + fats + " " + d_fats + "g";

                vh.calories.setText(stringFormat(food.getKcal(), food.getPortion()));

                if (food.getFavourite()) {
                    vh.likeFoodBTN.setImageResource(R.drawable.ic_favorite_black);
                } else {
                    vh.likeFoodBTN.setImageResource(R.drawable.ic_favorite_border_black);
                }

                vh.title.setOnClickListener(e -> {
                    Intent intent = new Intent(holder.itemView.getContext(), WholeInfoActivity.class);
                    intent.putExtra(FOOD_OBJECT, food);
                    ((Activity) holder.itemView.getContext()).startActivity(intent);
                });

                vh.likeFoodBTN.setOnClickListener(e -> {
                    int fav = 0;
                    if (!food.getFavourite()) {
                        fav = 1;
                        vh.likeFoodBTN.setImageResource(R.drawable.ic_favorite_black);
                        Toast.makeText(holder.itemView.getContext(), "Produkt " + food.getName() + " został dodany do ulubionych!", Toast.LENGTH_SHORT).show();
                    } else {
                        fav = 0;
                        vh.likeFoodBTN.setImageResource(R.drawable.ic_favorite_border_black);
                        Toast.makeText(holder.itemView.getContext(), "Produkt " + food.getName() + " został usunięty z ulubionych!", Toast.LENGTH_SHORT).show();
                    }
                    food.setFavourite(!food.getFavourite());

                    JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_INSERT_LUNCH_FAVOURITE_DATA + "?id=" + food.getId() + "&favourite=" + fav, null, response -> {
                    }, error -> {
                        Log.e("ERROR", " " + error.toString());
                    });
                    RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                });

                vh.delete_item.setOnClickListener(e -> {
                    MealState.meal_state = 4;
                    AlertDialog.Builder adb = new AlertDialog.Builder(holder.itemView.getContext());
                    adb.setTitle(holder.itemView.getResources().getString(R.string.delete_question));
                    adb.setMessage(holder.itemView.getResources().getString(R.string.are_you_sure_to_delate) + " " + food.getName());
                    adb.setNegativeButton(holder.itemView.getResources().getString(R.string.cancel), null);
                    adb.setPositiveButton("Ok", (dialog, which) -> {
                        progressDialog = new ProgressDialog(holder.itemView.getContext());
                        progressDialog.setMessage(holder.itemView.getResources().getString(R.string.please_wait));

                        int user = SharedPrefManager.getInstance(holder.itemView.getContext()).getId();


                        progressDialog.show();
                        if (MealState.meal_state <= 1) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_DELETEPRODUCT + food.getId(), null, response -> {
                                progressDialog.dismiss();
                            }, error -> {
                                progressDialog.dismiss();
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == 2) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_DELETEPRODUCTDINNER + food.getId(), null, response -> {
                                progressDialog.dismiss();
                            }, error -> {
                                progressDialog.dismiss();
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == 3) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_DELETEPRODUCTSUPPER + food.getId(), null, response -> {
                                progressDialog.dismiss();
                            }, error -> {
                                progressDialog.dismiss();
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                        } else if (MealState.meal_state == 4) {
                            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL_DELETEPRODUCTLUNCH + food.getId(), null, response -> {
                                progressDialog.dismiss();
                            }, error -> {
                                progressDialog.dismiss();
                                Log.e("ERROR", " " + error.toString());
                            });
                            RequestHandler.getInstance(holder.itemView.getContext()).addToRequestQueue(jsonObjectRequest);
                        }
                        progressDialog.dismiss();

                        try {
                            Fragment fragment = null;
                            Class fragmentClass = null;
                            fragmentClass = DietFragment.class;
                            fragment = (Fragment) fragmentClass.newInstance();
                            FragmentManager fragmentManager = ((FragmentActivity) holder.itemView.getContext()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                        } catch (Exception er) {
                            er.printStackTrace();
                        }

                    });
                    adb.show();
                });
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder vh = (FooterViewHolder) holder;
                vh.addProductBTN.setOnClickListener(e -> {
                    MealState.meal_state = 4;
                    vh.itemView.getContext().startActivity(new Intent(vh.itemView.getContext(), SarchProductActivity.class));
                });
                vh.footerRvTV.setOnClickListener(e -> {
                    MealState.meal_state = 4;
                    vh.itemView.getContext().startActivity(new Intent(vh.itemView.getContext(), SarchProductActivity.class));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        if (foodList == null) {
            return 0;
        }

        if (foodList.size() == 0) {
            return 1;
        }

        return foodList.size() + 1;
    }


}
