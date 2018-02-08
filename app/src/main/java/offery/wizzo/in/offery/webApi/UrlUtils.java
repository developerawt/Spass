package offery.wizzo.in.offery.webApi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import offery.wizzo.in.offery.R;

public class UrlUtils {
    public static String BASE_URL = "http://localhost/spass/";
    public static String GETSCHOOL_LIST = "http://ssappsnwebs.com/spass/admin2/api/Api_control/getSchoollist";

    public static String GENERALNEWS = "http://ssappsnwebs.com/spass/admin2/api/Api_control/generalNews";
    public static String GETCLASSES= "http://ssappsnwebs.com/spass/admin2/api/Api_control/getClassFromSchool";


    public static String SETCLASSES= "http://ssappsnwebs.com/spass/admin2/api/Api_control/addParentToClass";

    static ProgressDialog progressDialog;

    public static void showProgress(Context con) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(con, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.show();
    }


    public static void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }


    public static void hideSoftKeyboRD( Activity activity)
    {
        try {InputMethodManager keyboard = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            keyboard.hideSoftInputFromWindow(activity.getWindow().getAttributes().token, 0);

        }catch (Exception e)
        {

        }

    }


    public static void showRedeemDailog(Context context,String text) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        Window windo = dialog.getWindow();
        windo.setDimAmount(0.6f);
        WindowManager.LayoutParams wlp = windo.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        windo.setAttributes(wlp);
        TextView date_textview = (TextView) dialog.findViewById(R.id.text);

        ImageView cancel_dailoge_btn= (ImageView) dialog.findViewById(R.id.imgCancleDialogBoxId);

        date_textview.setText(text);
        cancel_dailoge_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialogInterface) {

            }
        });
        dialog.show();
    }

}
