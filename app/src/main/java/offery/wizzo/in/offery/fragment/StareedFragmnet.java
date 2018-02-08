package offery.wizzo.in.offery.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.adapter.StarredListAdapter;
import offery.wizzo.in.offery.interfacefile.ApiInterface;
import offery.wizzo.in.offery.webApi.AppUrl;
import offery.wizzo.in.offery.webApi.StarredList;

/**
 * Created by Jauhar xlr on 4/18/2016.
 * mycreativecodes.in
 */
public class StareedFragmnet extends Fragment {
    public static RecyclerView rv_list;
    TextView tv_noData;
    View view;

    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stareed, null);
        view = rootView;
        getStarredList();
        return rootView;
    }

    void getStarredList() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiService = AppUrl.getClient().create(ApiInterface.class);
        retrofit2.Call<ArrayList<StarredList>> call = apiService.getStarredList();

        call.enqueue(new retrofit2.Callback<ArrayList<StarredList>>() {
            @Override
            public void onResponse(retrofit2.Call<ArrayList<StarredList>> call, retrofit2.Response<ArrayList<StarredList>> response) {
                progressDialog.dismiss();
                initView(response.body());
            }

            @Override
            public void onFailure(retrofit2.Call<ArrayList<StarredList>> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();
            }
        });
    }

    void initView(ArrayList<StarredList> starredLists) {
        rv_list = (RecyclerView) view.findViewById(R.id.rvNewsListStareedId);
        tv_noData = (TextView) view.findViewById(R.id.txtNoDataStareedId);

        if (starredLists != null && starredLists.size() > 0) {
            tv_noData.setVisibility(View.GONE);
            rv_list.setVisibility(View.VISIBLE);
            rv_list.setHasFixedSize(true);
            rv_list.setItemAnimator(new DefaultItemAnimator());
            rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
            StarredListAdapter campaignListAdapter = new StarredListAdapter(getActivity(), starredLists);
            rv_list.setAdapter(campaignListAdapter);

        } else {
            tv_noData.setVisibility(View.VISIBLE);
            rv_list.setVisibility(View.GONE);
        }
    }
}
