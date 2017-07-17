package com.example.katguz.android.chembase.ui.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.katguz.android.chembase.R;
import com.example.katguz.android.chembase.model.events.HideProgress;
import com.example.katguz.android.chembase.model.events.ShowProgress;
import com.example.katguz.android.chembase.ui.chembase.ChembaseFragment;
import com.example.katguz.android.chembase.ui.chemicals.ChemicalsFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.dashboard_drawer)
    DrawerLayout drawer;
    @BindView(R.id.dashboard_toolbar)
    Toolbar toolbar;
    @BindView(R.id.fragment_container)
    FrameLayout container;
    @BindView(R.id.dashboard_progress)
    ProgressBar progress;
    @BindView(R.id.dashboard_nav)
    NavigationView navigationView;
   /* @BindView(R.id.cidNumber)
    EditText cidNumberInput;*/
  /*  @BindView(R.id.dataDownload)
    Button getData;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        setupDraverAndToolbar();
        //cidNumberInput = (EditText) view.findViewById(R.id.cidNumber);

        if (savedInstanceState == null) {
            showFragment(new ChemicalsFragment());
            showFragment(new ChembaseFragment());

        }


    }

/*    @OnClick(R.id.dataDownload)
    public void getInputData (Button button) {
        getMyData();
    }


    public String getMyData() {
        cidNumberInput.getText().toString();
        return String.valueOf(cidNumberInput);
    }*/

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(Gravity.START))
            drawer.closeDrawer(Gravity.START);
        else if (getSupportFragmentManager().getBackStackEntryCount() > 1)
            getSupportFragmentManager().popBackStack();
        else if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else super.onBackPressed();
        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    private void setupDraverAndToolbar() {
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Handle clicks on drawer
        drawer.addDrawerListener(toggle);
        // Sync state to have a hamburger menu icon
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_chemicals: {
                        showFragment(new ChemicalsFragment());
                        return true;
                    }
                    case R.id.menu_chembase: {
                        showFragment(new ChembaseFragment());
                        return true;
                    }

                }
                return false;
            }
        });

    }

    @Subscribe
    public void onShowProgress(ShowProgress showProgress) {
        container.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onHideProgress(HideProgress hideProgress) {
        progress.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);
    }



/*    private void setupTextFieldsWithCid() {

        if (cidNumberInput == null) {
            cidValue = cidNumberInput.getText();
        } else {
            Toast.makeText(getApplication(), "Enter a cid value",
                    Toast.LENGTH_LONG).show();

        }


    }*/

    private void showFragment(Fragment fragment) {

        String tag = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();

        if (manager.findFragmentByTag(tag) == null) {
            manager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, tag)
                    .addToBackStack(tag)
                    .commit();


        } else {
            manager.popBackStackImmediate(tag, 0);
        }
        drawer.closeDrawer(Gravity.START);
    }


}
