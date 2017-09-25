package com.ats.sap_recruitment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ats.sap_recruitment.R;
import com.ats.sap_recruitment.fragment.ABAPFragment;
import com.ats.sap_recruitment.fragment.AssesmentHistoryFragment;
import com.ats.sap_recruitment.fragment.AssesmentStartFragment;
import com.ats.sap_recruitment.fragment.AssesmentSummaryFragment;
import com.ats.sap_recruitment.fragment.BasisFragment;
import com.ats.sap_recruitment.fragment.EducationalProfileFragment;
import com.ats.sap_recruitment.fragment.FunctionalFragment;
import com.ats.sap_recruitment.fragment.HomeEmployerFragment;
import com.ats.sap_recruitment.fragment.HomeFragment;
import com.ats.sap_recruitment.fragment.PersonalProfileFragment;
import com.ats.sap_recruitment.fragment.PreparationFragment;
import com.ats.sap_recruitment.fragment.TermsAndConditionsFragment;
import com.ats.sap_recruitment.fragment.TestFeedbackFragment;
import com.ats.sap_recruitment.fragment.TestResultFragment;
import com.ats.sap_recruitment.fragment.UpdateProfileFragment;
import com.ats.sap_recruitment.fragment.ViewJobFragment;
import com.ats.sap_recruitment.utils.CounterClass;
import com.ats.sap_recruitment.utils.PermissionsUtil;
import com.ats.sap_recruitment.utils.TimerCounter;

import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // public static boolean isAtEmployerHome = true, isAtHome = false, isAtUpdateProfile = false, isAtBasis = false, isAtAssesmentHistory = false, isAtTermsCond = false, isAtPreparatio = false, isAtAssesmentStart = false, isAtAssesmentSummary = false, isAtTestResult = false, isAtAbap = false, isAtFunctional = false, isAtTestFeedback = false, isAtPersonalProfile = false, isAtEduProfile = false, isAtViewJob = false;
    public static TextView tvTitle;
    Timer mTimer;
    TimerTask mTimerTask;
    int count = 0;
    public static TimerCounter counter;
    public static CounterClass counterClass;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTitle = (TextView) toolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("SAP Recruitment");

        if (PermissionsUtil.checkAndRequestPermissions(HomeActivity.this)) {

        }

        counter = new TimerCounter(HomeActivity.this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.mipmap.icon_menu);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, new HomeFragment(), "Home");
//            ft.commit();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeEmployerFragment(), "HomeEmployer");
            ft.commit();
        }

    }

    @Override
    public void onBackPressed() {

        Fragment home = getSupportFragmentManager().findFragmentByTag("Home");
        Fragment homeEmployer = getSupportFragmentManager().findFragmentByTag("HomeEmployer");
        Fragment assStart = getSupportFragmentManager().findFragmentByTag("AssessmentStart");
        Fragment assSummary = getSupportFragmentManager().findFragmentByTag("AssessmentSummary");
        Fragment updateFragment = getSupportFragmentManager().findFragmentByTag("UpdateProfile");
        Fragment homeFragmnet = getSupportFragmentManager().findFragmentByTag("HomeFragment");
        Fragment termAndCond = getSupportFragmentManager().findFragmentByTag("TermsAndCond");
        Fragment testResult = getSupportFragmentManager().findFragmentByTag("TestResult");
        Fragment homeEmplr = getSupportFragmentManager().findFragmentByTag("HomeEmplr");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (home instanceof HomeFragment && home.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Confirm Action");
            builder.setMessage("Do you really want to exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (homeEmployer instanceof HomeEmployerFragment && homeEmployer.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Confirm Action");
            builder.setMessage("Do you really want to exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else if (assStart instanceof AssesmentSummaryFragment && assStart.isVisible()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle("Alert");
            builder.setMessage("Are you sure to go back, test will be ended?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    counter.stop();
                    Fragment fragment = new TestResultFragment();
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment, "HomeFragment");
                    ft.commit();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            /*    Fragment fragment = new PreparationFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.commit();*/
        } else if (assSummary instanceof AssesmentSummaryFragment && assSummary.isVisible()) {
            Fragment fragment = new AssesmentStartFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "AssessmentStart");
            ft.commit();
        } else if (updateFragment instanceof BasisFragment && updateFragment.isVisible() || updateFragment instanceof ABAPFragment && updateFragment.isVisible() || updateFragment instanceof FunctionalFragment && updateFragment.isVisible() || updateFragment instanceof PersonalProfileFragment && updateFragment.isVisible() || updateFragment instanceof EducationalProfileFragment && updateFragment.isVisible()) {
            Fragment fragment = new UpdateProfileFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "HomeFragment");
            ft.commit();
        } else if (homeFragmnet instanceof TermsAndConditionsFragment && homeFragmnet.isVisible() || homeFragmnet instanceof UpdateProfileFragment && homeFragmnet.isVisible() || homeFragmnet instanceof AssesmentHistoryFragment && homeFragmnet.isVisible() || homeFragmnet instanceof TestResultFragment && homeFragmnet.isVisible()) {
            Fragment fragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "Home");
            ft.commit();
        } else if (termAndCond instanceof PreparationFragment && termAndCond.isVisible()) {
            Fragment fragment = new TermsAndConditionsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "HomeFragment");
            ft.commit();
        } else if (testResult instanceof TestFeedbackFragment && testResult.isVisible()) {
            Fragment fragment = new TestResultFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "HomeFragment");
            ft.commit();
        } else if (homeEmplr instanceof ViewJobFragment && homeEmplr.isVisible()) {
            Fragment fragment = new HomeEmployerFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "HomeEmployer");
            ft.commit();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       /* //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
//            Fragment fragment = new HomeFragment();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.content_frame, fragment);
//            ft.commit();

            Fragment fragment = new HomeEmployerFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment, "HomeEmployer");
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //Countdown Timer


}
