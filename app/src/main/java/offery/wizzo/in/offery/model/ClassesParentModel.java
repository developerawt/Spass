package offery.wizzo.in.offery.model;

import java.util.ArrayList;

/**
 * Created by ist on 10/9/17.
 */

public class ClassesParentModel {


    public  NewClassData newdata;

    public NewClassData getNewdata() {
        return newdata;
    }

    public void setNewdata(NewClassData newdata) {
        this.newdata = newdata;
    }


public class NewClassData
{


    ArrayList<ClassesModel>response;

    public ArrayList<ClassesModel> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<ClassesModel> response) {
        this.response = response;
    }

}

}
