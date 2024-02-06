package com.theakj.letsconnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Profile extends Fragment {

    ImageView pp;
    TextView emailid,contactno,name;
    Button logout;

    FirebaseFirestore fs;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        pp=view.findViewById(R.id.pp);
        emailid=view.findViewById(R.id.emailid);
        contactno=view.findViewById(R.id.contactNumber);
        logout=view.findViewById(R.id.logoutbtn);
        name=view.findViewById(R.id.name);

        auth=FirebaseAuth.getInstance();
        fs=FirebaseFirestore.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        String xyz=user.getEmail();

        DocumentReference document=fs.collection("users").document(xyz + "");
        document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    name.setText(documentSnapshot.getString("name"));
                    contactno.setText(documentSnapshot.getString("contact"));
                    emailid.setText(documentSnapshot.getString("email"));
                }else{
                    Toast.makeText(getActivity(), "No Data Found", Toast.LENGTH_SHORT).show();
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Unable to Fetch Data", Toast.LENGTH_SHORT).show();
                    }
                });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(getActivity(),loginPage.class));
                requireActivity().finish();
            }
        });


        return view;
    }

}