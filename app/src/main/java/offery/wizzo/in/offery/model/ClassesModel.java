package offery.wizzo.in.offery.model;

import java.util.ArrayList;

/**
 * Created by ist on 10/9/17.
 */

public class ClassesModel {

public  String school_id;
    public ArrayList<ClassModel>classes;

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public ArrayList<ClassModel> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<ClassModel> classes) {
        this.classes = classes;
    }
}
