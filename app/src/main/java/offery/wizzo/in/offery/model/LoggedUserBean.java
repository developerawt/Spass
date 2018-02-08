package offery.wizzo.in.offery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ankushpatel18 28/10/17.
 */

public class LoggedUserBean implements Serializable{

    @SerializedName("mobile_no")
   private String mobileNo;

    @SerializedName("auth_token")
    private String authToken;

    @SerializedName("device_id")
    private String deviceId;


    public LoggedUserBean() {
    }

    public LoggedUserBean(String mobileNo, String authToken, String deviceId) {
        this.mobileNo = mobileNo;
        this.authToken = authToken;
        this.deviceId = deviceId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
