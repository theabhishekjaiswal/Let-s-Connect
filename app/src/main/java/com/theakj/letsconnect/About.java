package com.theakj.letsconnect;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class About extends Fragment {
    ImageView whatsapp,insta,linkedin,github;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about, container, false);

        whatsapp=view.findViewById(R.id.whatsapp);
        insta=view.findViewById(R.id.insta);
        linkedin=view.findViewById(R.id.linkedin);
        github=view.findViewById(R.id.github);

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://api.whatsapp.com/send/?phone=%2B919708061457&text&type=phone_number&app_absent=0");
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               gotoUrl("https://www.instagram.com/theabhishek_jaiswal/");
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.linkedin.com/in/theabhishekjaiswal12/");
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://github.com/theabhishekjaiswal");
            }
        });

        return view;
    }

    private void gotoUrl(String s) {
        try {
            Uri uri = Uri.parse(s);
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        } catch (Exception e) {
            Toast.makeText(getActivity(), "No data Found", Toast.LENGTH_SHORT).show();
        }

    }
}