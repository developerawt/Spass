package offery.wizzo.in.offery.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.activity.ChangeNumberActivity;
import offery.wizzo.in.offery.activity.GetSearchSchoolActivity;
import offery.wizzo.in.offery.activity.MyAccountActivity;

/**
 * Created by Jauhar xlr on 4/18/2016.
 * mycreativecodes.in
 */
public class SettingFragment extends Fragment {
    TextView texk1;
    TextView texk2;
    TextView txtmyAccount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_setting, null);
        texk1 = (TextView) rootView.findViewById(R.id.txtSchoolChangeSettingId);
        texk2 = (TextView) rootView.findViewById(R.id.txtNumberChangeSettingId);
        txtmyAccount = (TextView) rootView.findViewById(R.id.txtMyAccountSettingId);
        txtmyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyAccountActivity.class);
                startActivity(intent);
                //getActivity().finish();
            }
        });

        texk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(getActivity(), ChangeNumberActivity.class);
                startActivity(intent1);
                //getActivity().finish();
            }
        });
        texk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GetSearchSchoolActivity.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });

        return rootView;

    }

}
