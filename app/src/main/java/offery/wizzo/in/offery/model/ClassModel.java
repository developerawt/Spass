package offery.wizzo.in.offery.model;

/**
 * Created by ist on 10/9/17.
 */

public class ClassModel {

    String class_id;
    String class_name;
    String shool_id;
boolean isChecked=false;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getShool_id() {
        return shool_id;
    }

    public void setShool_id(String shool_id) {
        this.shool_id = shool_id;
    }
}
