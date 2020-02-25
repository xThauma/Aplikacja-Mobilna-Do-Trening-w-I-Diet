package kamiltm.project_sm.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

import kamiltm.project_sm.R;
import kamiltm.project_sm.Supplement.SupplementFragment;
import kamiltm.project_sm.connectors.DateState;
import kamiltm.project_sm.diet.food.DietFragment;
import kamiltm.project_sm.home.HomeFragment;
import kamiltm.project_sm.training.TrainingFragment;
import kamiltm.project_sm.user.LoginActivity;
import kamiltm.project_sm.user.SharedPrefManager;
import kamiltm.project_sm.user.UserInformationsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView username, email;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        View header = navigationView.getHeaderView(0);
        username = (TextView) header.findViewById(R.id.userNameTV);
        email = (TextView) header.findViewById(R.id.userEmailTV);
        username.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getUserEmail());

        Fragment fragment = null;
        Class fragmentClass = DietFragment.class;
        // Handle navigation view item clicks here.
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        if (id == R.id.logout) {
            SharedPrefManager.getInstance(this).logout();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            return false;
        } else if (id == R.id.home) {
            DateState.dateState = new Date();
            fragmentClass = HomeFragment.class;
        } else if (id == R.id.diet) {
            DateState.dateState = new Date();
            fragmentClass = DietFragment.class;
        } else if (id == R.id.user_info) {
            fragmentClass = UserInformationsFragment.class;
        } else if (id == R.id.training) {
            DateState.dateState = new Date();
            fragmentClass = TrainingFragment.class;
        }else if (id == R.id.suplementItem) {
            fragmentClass = SupplementFragment.class;
        } else if (id == R.id.exit) {
            finish();
            return false;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
