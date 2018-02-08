package offery.wizzo.in.offery.interfacefile;

import java.util.ArrayList;

import offery.wizzo.in.offery.webApi.AppUrl;
import offery.wizzo.in.offery.webApi.SchoolList;
import offery.wizzo.in.offery.webApi.StarredList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by WinSnit on 4/27/2017.
 */

public interface ApiInterface {

    /// Request date formate is 1 Jan 2017
    @GET(AppUrl.URL_GET_STARRED)
    Call<ArrayList<StarredList>> getStarredList();

    @GET(AppUrl.URL_GET_SCHOOL_LIST)
    Call<ArrayList<SchoolList>> getSchoolList();
}