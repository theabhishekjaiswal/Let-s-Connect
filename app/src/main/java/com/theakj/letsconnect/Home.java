package com.theakj.letsconnect;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;


public class Home extends Fragment {

    Button joinBtn;
    EditText secretCodeBox;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        joinBtn= view.findViewById(R.id.joinBtn);
        secretCodeBox= view.findViewById(R.id.secretCode);

        URL serverURL;
        try {
            serverURL=new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions=
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setFeatureFlag("invite.enabled", false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((secretCodeBox.getText().toString()).isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter Secret Code", Toast.LENGTH_SHORT).show();
                }else{
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setRoom(secretCodeBox.getText().toString())
                            .setFeatureFlag("invite.enabled", false)
                            .build();
                    JitsiMeetActivity.launch(requireActivity(),options);
                }

            }
        });

        return view;
    }

}