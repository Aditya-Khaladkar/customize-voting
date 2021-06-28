package com.example.customizevoting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

public class Candidate extends AppCompatActivity {
    TextView txtvotingname,c1,c2,c3,c4,c5,c6,txtresult;
    LinearLayout c1vote,c2vote,c3vote,c4vote,c5vote,c6vote;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        txtvotingname=findViewById(R.id.txtvotingname);

        // text

        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);

        // layout

        c1vote=findViewById(R.id.c1vote);
        c2vote=findViewById(R.id.c2vote);
        c3vote=findViewById(R.id.c3vote);
        c4vote=findViewById(R.id.c4vote);
        c5vote=findViewById(R.id.c5vote);
        c6vote=findViewById(R.id.c6vote);

        String votingname=getIntent().getStringExtra("votingname");
        String name1=getIntent().getStringExtra("name1");
        String name2=getIntent().getStringExtra("name2");
        String name3=getIntent().getStringExtra("name3");
        String name4=getIntent().getStringExtra("name4");
        String name5=getIntent().getStringExtra("name5");
        String name6=getIntent().getStringExtra("name6");

        txtvotingname.setText(votingname);
        c1.setText(name1);
        c2.setText(name2);
        c3.setText(name3);
        c4.setText(name4);
        c5.setText(name5);
        c6.setText(name6);
        
        // creating instance of firebase cloud firestore
        firebaseFirestore=FirebaseFirestore.getInstance();

        // on click
        txtresult=findViewById(R.id.txtresult);
        txtresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Result.class);
                intent.putExtra("votingname",votingname);
                intent.putExtra("name1",name1);
                intent.putExtra("name2",name2);
                intent.putExtra("name3",name3);
                intent.putExtra("name4",name4);
                intent.putExtra("name5",name5);
                intent.putExtra("name6",name6);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        
        // store vote into database
        
        c1vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name1) + 1;
                        transaction.update(documentReference, name1, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                        "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);
            }
        });

        c2vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name2) + 1;
                        transaction.update(documentReference, name2, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                                "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);

            }
        });

        c3vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name3) + 1;
                        transaction.update(documentReference, name3, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                                "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);
            }
        });

        c4vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name4) + 1;
                        transaction.update(documentReference, name4, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                                "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);
            }
        });

        c5vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name5) + 1;
                        transaction.update(documentReference, name5, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                                "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);
            }
        });

        c6vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseFirestore.runTransaction(new Transaction.Function<Object>() {
                    @Nullable
                    @Override
                    public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {
                        DocumentReference documentReference=firebaseFirestore.collection("votes")
                                .document(votingname);
                        DocumentSnapshot documentSnapshot = transaction.get(documentReference);
                        long newVote = documentSnapshot.getLong(name6) + 1;
                        transaction.update(documentReference, name6, newVote);
                        return newVote;
                    }
                });

                Toast.makeText(Candidate.this, "vote registered successfully "+
                                "you cannot vote twice",
                        Toast.LENGTH_SHORT).show();

                c1vote.setEnabled(false);
                c2vote.setEnabled(false);
                c3vote.setEnabled(false);
                c4vote.setEnabled(false);
                c5vote.setEnabled(false);
                c6vote.setEnabled(false);
            }
        });

    }
}