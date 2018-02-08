package offery.wizzo.in.offery.interfacefile;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Belal on 11/5/2015.
 */
public interface ApiCalls {


    @FormUrlEncoded
    @POST("/Api_control/changeMobile")
    public void updateMobileNumber(
            @Field("existing") String mobile,
            @Field("new") String number,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/Api_control/makeNewsStarred")
    public void insertstatus(
            @Field("news_id") String id,
            @Field("givenBy") String mobile,
            @Field("status") String status,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/Api_control/getMobileStatus")
    public void insertUser(
            @Field("mobile") String mobile,
            @Field("deviceno") String deviceid,
            @Field("regId") String token,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/Api_control/userFeedback")
    public void insertfeed(
            @Field("mobile") String mobile,
            @Field("activity_feed_back") String otp,
            Callback<Response> callback);


    @FormUrlEncoded
    @POST("/Api_control/getOtpStatus")
    public void sendOTP(
            @Field("mobile") String mobile,
            @Field("otp") String otp,
            Callback<Response> callback);


    //	28.10.17%204:55pm dateFormat
    @GET("/Api_control/last_login")
    public void updateLastLogin(
            @Query("mobile") String mobile,
            @Query("date_time") String dateTime,
            Callback<Response> callback);


}