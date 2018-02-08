package offery.wizzo.in.offery.model;

import java.util.ArrayList;

/**
 * Created by ist on 10/9/17.
 */

public class ClassSetResponse {

    ArrayList<ClassSetBean>response;

    public ArrayList<ClassSetBean> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<ClassSetBean> response) {
        this.response = response;
    }

 public    class ClassSetBean
    {


        String status;
        String info;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
