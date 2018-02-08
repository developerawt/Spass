package offery.wizzo.in.offery.webApi;/*
* Created by arpit on 31/05/16
* */

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;

import offery.wizzo.in.offery.model.DataBean;
import offery.wizzo.in.offery.model.LoggedUserBean;

public class AppSharedPrefs {
    private static final String PREFS_NAME = "SPASS";
    static SharedPreferences sp;
    static SharedPreferences.Editor prefEditor = null;
    private static Context mContext = null;
    public static AppSharedPrefs instance = null;

    private final String LOGGED_USER = "logged_user";

    public static AppSharedPrefs getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            instance = new AppSharedPrefs();
        }
        sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefEditor = sp.edit();
        return instance;
    }


    public String readPrefs(Context ctx, String key) {
        String value = sp.getString(key, "");
        return value;
    }

    public String readPrefs(String key) {
        String value = sp.getString(key, "");
        return value;
    }

    public boolean hasPrefs(String key) {
        return sp.contains(key);

    }

    public long readLongPrefs(String key) {
        long value = sp.getLong(key, 0);
        return value;
    }

    public void writeLongPrefs(String key, long value) {
        prefEditor.putLong(key, value);
        prefEditor.commit();
    }


    public void writePrefs(String key, String value) {
        prefEditor.putString(key, value);
        prefEditor.commit();
    }

    public boolean readBooleanPrefs(String pref_name) {
        return sp.getBoolean(pref_name, false);
    }

    public void writeBooleanPrefs(String pref_name, boolean pref_val) {
        prefEditor.putBoolean(pref_name, pref_val);
        prefEditor.commit();
    }

//    public String getValue(String value_name) {
//        return sp.getString(value_name,null);
//    }
//
//    public void setValue(String value_name, String value_val) {
//        prefEditor.putString(value_name, value_val);
//        prefEditor.commit();
//    }


    public static void clearSharedPref(Context context) {
        try {
            if (prefEditor != null) {
                prefEditor.clear();
                prefEditor.commit();
            }
        } catch (Exception e) {
        }
    }

    public static void setSharedPreferenceStringList(Context pContext, String pKey, ArrayList<DataBean> pData) {
        //SharedPreferences.Editor editor = pContext.getSharedPreferences(Constants.APP_PREFS, Activity.MODE_PRIVATE).edit();
        prefEditor.putInt(pKey + "size", pData.size());
        prefEditor.commit();

        for (int i = 0; i < pData.size(); i++) {
            //SharedPreferences.Editor editor1 = pContext.getSharedPreferences(Constants.APP_PREFS, Activity.MODE_PRIVATE).edit();
            prefEditor.putString(pKey + i, (pData.get(i).getName()));
            prefEditor.commit();
        }
    }
    public static ArrayList<String> getSharedPreferenceStringList(Context pContext, String pKey) {
        int size = sp.getInt(pKey + "size", 0);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(sp.getString(pKey + i, ""));
        }
        return list;
    }
    public int readIntPrefs(Context context, String key) {
        int value = sp.getInt(key, 0);
        return value;
    }

    public void writeIntPrefs(String key, int value) {
        prefEditor.putInt(key, value);
        prefEditor.commit();
    }

    public void clear() {
        try {
            prefEditor.clear();
            prefEditor.commit();

        } catch (Exception e) {
        }

    }

    public void saveLoggedUser(LoggedUserBean user) {
        writePrefs(LOGGED_USER, new Gson().toJson(user));
    }

    public LoggedUserBean getLoggedUser() {
        if (hasPrefs(LOGGED_USER))
            return new Gson().fromJson(readPrefs(LOGGED_USER), LoggedUserBean.class);
        else
            return null;
    }

}
