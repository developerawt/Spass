package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.iid.FirebaseInstanceId;

import io.fabric.sdk.android.Fabric;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.fcm.Config;
import offery.wizzo.in.offery.webApi.AppSharedPrefs;


public class WelcomeSplashActivity extends Activity {

    String data = "{\"response\":[{\"school_id\":\"10\",\"classes\":[{\"class_id\":\"1\",\"class_name\":\"class1\",\"school_id\":\"10\"},{\"class_id\":\"2\",\"class_name\":\"class2\",\"school_id\":\"10\"}]},{\"school_id\":\"17\",\"classes\":[{\"class_id\":\"7\",\"class_name\":\"class7\",\"school_id\":\"17\"},{\"class_id\":\"8\",\"class_name\":\"class8\",\"school_id\":\"17\"}]}]}";

    Thread splashTread;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

        setContentView(R.layout.activity_splash_welcome);
        displayFirebaseRegId();
        StartAnimations();

    }

    private void displayFirebaseRegId() {
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            Log.w("notification", token);
        //    Toast.makeText(WelcomeSplashActivity.this,""+token,Toast.LENGTH_SHORT).show();
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);
       // token=regId;
        Log.e("Firebase", "Firebase reg id: " + regId);

        if (!TextUtils.isEmpty(regId))
            Log.e("Firebase Reg Id: ", regId);
        else
            Log.e("Firebase Reg Id: ", " ");
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.ll_main);
        mainLinearLayout.clearAnimation();
        mainLinearLayout.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        TextView iv = (TextView) findViewById(R.id.text);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    String value = AppSharedPrefs.getInstance(WelcomeSplashActivity.this).readPrefs(WelcomeSplashActivity.this, "ishome");
                    if (value.equalsIgnoreCase("1")) {
                        Intent intent = new Intent(WelcomeSplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        WelcomeSplashActivity.this.finish();
                    } else {
                        Intent intent = new Intent(WelcomeSplashActivity.this, UserRegisterActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent);
                        WelcomeSplashActivity.this.finish();
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    WelcomeSplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }

}
