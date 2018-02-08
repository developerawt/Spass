package offery.wizzo.in.offery.webApi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by WinSnit on 4/27/2017.
 */

public class AppUrl {

    public static final String BASE_URL = "http://ssappsnwebs.com/spass/admin2/api/Api_control/";

    public static final String URL_GET_STARRED = BASE_URL + "getStar";
    public static final String URL_GET_SCHOOL_LIST = BASE_URL + "schoolList";

    private static Retrofit retrofit = null;

    public final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}