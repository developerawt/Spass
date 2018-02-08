package offery.wizzo.in.offery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import offery.wizzo.in.offery.activity.MainActivity;
import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.interfacefile.ApiCalls;
import offery.wizzo.in.offery.netcom.CheckNetwork;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;
import offery.wizzo.in.offery.webApi.UrlUtils;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Jauhar xlr on 4/18/2016.
 * mycreativecodes.in
 */
public class HomeFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 2;

    Fragment generalFragment, personalFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.fragment_home_tab, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        updateLastOnline();
        String type = "";
        try {
            type = getArguments().getString("type");
        } catch (Exception e) {
            type = "";
        }


        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        if (type != null || type.equalsIgnoreCase("general")) {
            viewPager.setCurrentItem(0);
        }
        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return x;

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
            generalFragment = new GeneralNewsFragment();
            personalFragment = new PeresonalNewsFragment();
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return generalFragment;
                case 1:
                    return personalFragment;

            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        /**
         * This method returns the title of the tab according to the position.
         */

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "General";
                case 1:
                    return "Class Specific";
//                case 2 :
//                    return "Expo";
            }
            return null;
        }
    }


    /**
     * Created by ankushpatel181 to implement search functionality from {@link MainActivity#searchView}
     */
    public void updateSearchText(String searchText) {
        if (generalFragment != null && generalFragment.isVisible())
            ((GeneralNewsFragment) generalFragment).updateSearchText(searchText);
        if (personalFragment != null && personalFragment.isVisible())
            ((PeresonalNewsFragment) personalFragment).updateSearchText(searchText);
    }


    private void updateLastOnline() {
        if (!CheckNetwork.checkConnection(getActivity())) {
            return;
        }
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(getResources().getString(R.string.base_url)) //Setting the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiCalls api = adapter.create(ApiCalls.class);
        String mobile = AppSharedPrefs.getInstance(getActivity()).readPrefs("mobile");
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy hh-mm-a").format(new Date());


        api.updateLastLogin(mobile,
                timeStamp,
                //Creating an anonymous callback
                new Callback<Response>() {
                    @Override
                    public void success(retrofit.client.Response response, retrofit.client.Response response2) {
                        BufferedReader reader = null;

                        //An string to ic_store output from the server
                        String output = "";
                        String result = "";

                        try {
                            //Initializing buffered reader
                            reader = new BufferedReader(new InputStreamReader(response.getBody().in()));
//
                            output = reader.readLine();
                            // JSONObject json= new JSONObject(output);  //your response
                            try {
                                JSONArray arrResponse = new JSONObject(output).getJSONArray("response");

                                for (int i = 0; i < arrResponse.length(); i++)

                                    if (arrResponse.getJSONObject(i).has("status") && arrResponse.getJSONObject(i).getString("status").trim().equals("1")) {
                                        Log.e("responsess", "Lasdt online updated");
                                    }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                UrlUtils.dismissDialog();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            UrlUtils.dismissDialog();
                        }

                        Log.e("response", output);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        // If any error occured displaying the error as toast
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                        UrlUtils.dismissDialog();
                    }
                }
        );
    }


}
