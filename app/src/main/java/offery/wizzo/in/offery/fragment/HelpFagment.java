package offery.wizzo.in.offery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import offery.wizzo.in.offery.R;

/**
 * Created by jauhar xlr on 18/4/2016.
 */
public class HelpFagment extends Fragment {
    String text;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_help, null);
        WebView view = (WebView) rootView.findViewById(R.id.wbHelpId);

        text = "<html><body><p align=\"justify\">";
        text += "SPASS is An Easy To Use Mobile App That Enables You Get All Relevant Information From The schools You Have Interests In Download Spass From Google Play Store Or"+ "<html><body><p align=\"justify\">"+ "www.spassonline.com/download"+ "<html><body><p align=\"justify\">" +
                "Account creation: Launch the app and select your country, followed by your mobile phone number, you will receive a one time password " +
                "in an SMS to enable you complete this step." + "" + "<html><body><p align=\"justify\">" + "School Model selection: Search for the schools you wish to get ic_messages and alerts from choose class if you also wish to receive class specific alerts for your child then click submit." +
                "<html><body><p align=\"center\">" + "Voila you are all set." + "<html><body><p align=\"center\">";
        text += "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");
        return rootView;
    }


}
