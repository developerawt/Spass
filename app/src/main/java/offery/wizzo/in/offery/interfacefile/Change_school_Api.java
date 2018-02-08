package offery.wizzo.in.offery.interfacefile;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Belal on 11/5/2015.
 */
public interface Change_school_Api {
	@FormUrlEncoded
	@POST("/Api_control/changeScool")
	public void insert_mobile_no(
            @Field("mobile") String mobile,
            Callback<Response> callback);


}