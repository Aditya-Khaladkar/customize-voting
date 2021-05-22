package com.example.customizevoting;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Result extends AppCompatActivity {
    TextView cv1,cv2,cv3,votename,count1,count2,count3;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        votename=findViewById(R.id.votename);
        cv1=findViewById(R.id.cv1);
        cv2=findViewById(R.id.cv2);
        cv3=findViewById(R.id.cv3);

        firebaseFirestore=FirebaseFirestore.getInstance();

        count1=findViewById(R.id.count1);
        count2=findViewById(R.id.count2);
        count3=findViewById(R.id.count3);

        String votingname=getIntent().getStringExtra("votingname");
        String name1=getIntent().getStringExtra("name1");
        String name2=getIntent().getStringExtra("name2");
        String name3=getIntent().getStringExtra("name3");

        votename.setText(votingname);
        cv1.setText(name1);
        cv2.setText(name2);
        cv3.setText(name3);

        // result

        documentReference=firebaseFirestore.collection("votes")
                .document(votingname);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    Object count=value.get(name1);
                    count1.setText(""+count);
                }
            }
        });

        documentReference=firebaseFirestore.collection("votes")
                .document(votingname);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    Object count=value.get(name2);
                    count2.setText(""+count);
                }
            }
        });

        documentReference=firebaseFirestore.collection("votes")
                .document(votingname);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    Object count=value.get(name3);
                    count3.setText(""+count);
                }
            }
        });
    }
}