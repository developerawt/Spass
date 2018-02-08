package offery.wizzo.in.offery.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.fragment.BookMarkFragment;
import offery.wizzo.in.offery.fragment.HelpFagment;
import offery.wizzo.in.offery.fragment.HomeFragment;
import offery.wizzo.in.offery.fragment.SettingFragment;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.database.RealmController;

/**
 * Created by Jauhar xlr on 4/18/2016.
 * mycreativecodes.in
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout myDrawerLayout;
    NavigationView myNavigationView;
    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    public static Toolbar toolbar, toolbar1;
    ImageView srch;
    ImageView back_button;
    public static String data;
    private EditText searchBox;
    SearchView searchView;
    LinearLayout lnr;
    private Realm realm;
    Fragment currentFragment;

    TextView toolbarTitle, text_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("Spass");
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        myNavigationView = (NavigationView) findViewById(R.id.nav_drawer);
        searchView = (SearchView) findViewById(R.id.search_view);
        back_button = (ImageView) findViewById(R.id.backks);


        this.realm = RealmController.with(this).getRealm();
        // ic_refresh the realm instance
        RealmController.with(this).refresh();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (currentFragment instanceof HomeFragment)
                    ((HomeFragment) currentFragment).updateSearchText(newText);
                return false;
            }
        });
        String type = "";
        try {
            type = getIntent().getExtras().getString("type");
            if (type == null) {
                type = "";
            }
        } catch (Exception e) {
            type = "";
        }
        currentFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        currentFragment.setArguments(bundle);
        myFragmentManager = getSupportFragmentManager();
        myFragmentTransaction = myFragmentManager.beginTransaction();
        myFragmentTransaction.replace(R.id.containerView, currentFragment).commit();
        /**
         * Setup click events on the Navigation View Items.
         */

        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem selectedMenuItem) {
                myDrawerLayout.closeDrawers();
                if (selectedMenuItem.getItemId() == 0) {
                    toolbar.setTitle("Spass");
                }
                //need to show sewarchView for HomeFragment only lese invisible it
                searchView.setVisibility(View.GONE);

                if (selectedMenuItem.getItemId() == R.id.nav_item_home) {
                    currentFragment = new HomeFragment();
                    FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.containerView, currentFragment).addToBackStack(null).commit();
                    toolbar.setTitle("Spass");

                    searchView.setVisibility(View.VISIBLE);

                }

                if (selectedMenuItem.getItemId() == R.id.nav_item_bookmark) {
                    currentFragment = new BookMarkFragment();
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, currentFragment).addToBackStack(null).commit();
                   // layout_toolbar.setTitle("Directory");
                    //Toast.makeText(MainActivity.this, "In progress", Toast.LENGTH_LONG).show();
                }
                if (selectedMenuItem.getItemId() == R.id.nav_item_newp) {
                    currentFragment = new SettingFragment();
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, currentFragment).addToBackStack(null).commit();
                    toolbar.setTitle("Setting");
                    //Toast.makeText(MainActivity.this, "In progress", Toast.LENGTH_LONG).show();
                }

                if (selectedMenuItem.getItemId() == R.id.contactid) {
                    Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                    startActivity(intent);
                   // layout_toolbar.setTitle("Contact");
//                    Toast.makeText(MainActivity.this, "In progress", Toast.LENGTH_LONG).show();
                }
                if (selectedMenuItem.getItemId() == R.id.fdkk) {
                    Intent intent1 = new Intent(MainActivity.this, FeedBackActivity.class);
                    startActivity(intent1);
                    finish();

                    toolbar.setTitle("Feedback");

                }

                if (selectedMenuItem.getItemId() == R.id.invite_frndz) {
                    Intent share = new Intent(android.content.Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                    // Add data to the intent, the receiving app will decide
                    // what to do with it.
                    share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
                    share.putExtra(Intent.EXTRA_TEXT, "Join SPASS and never miss an event or buzz on what is happening in your child's school go to www.spassonline.com/download");

                    startActivity(Intent.createChooser(share, "Share link!"));
                }


                if (selectedMenuItem.getItemId() == R.id.help) {
                    currentFragment = new HelpFagment();
                    FragmentTransaction xfragmentTransaction = myFragmentManager.beginTransaction();
                    xfragmentTransaction.replace(R.id.containerView, currentFragment).addToBackStack(null).commit();
                    toolbar.setTitle("Help");

                }
                return false;
            }

        });

        /**
         * Setup Drawer Toggle of the Toolbar
         */

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, myDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        myDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

    }

//    public void logoutAlert(final Context mContext) {
//        new AlertDialog.Builder(mContext, R.style.DialogTheme)
//                .setTitle(R.string.dialog_title_quit)
//                .setMessage(R.string.dialog_message_quit)
//                .setPositiveButton(R.string.dialog_btn_yes, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        AppSharedPrefs.getInstance(MainActivity.this).clear();
//                        Intent intent = new Intent(MainActivity.this, UserRegisterActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//
//                })
//                .setNegativeButton(R.string.dialog_btn_no, null)
//                .show();
//    }


    boolean doubleBackToExitPressedOnce = false;
//

    @Override
    public void onBackPressed() {
        toolbar.setTitle("Spass");
        super.onBackPressed();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_header_title:
                Animation zoomout = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim_slide_in_left);
                lnr.setAnimation(zoomout);
                lnr.startAnimation(zoomout);
                toolbar.setVisibility(View.GONE);
                toolbar1.setVisibility(View.VISIBLE);
                lnr.setVisibility(View.VISIBLE);
                srch.setVisibility(View.GONE);
                break;
        }

    }
}
