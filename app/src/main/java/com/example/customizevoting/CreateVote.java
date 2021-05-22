package com.example.customizevoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class CreateVote extends AppCompatActivity {
    private static final String TAG = "CreateVote";

    // regx

    private static final Pattern NAME = Pattern.compile("^" +
            //"(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            //"(?=.*[a-zA-Z])" +      //any letter
            //"(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");

    EditText vname,vpassword,c1name,c1aadhaar,c2name,c2aadhaar,c3name,c3aadhaar;
    ProgressBar progressBar;
    Button btn_create,pos,neg;
    FirebaseFirestore firebaseFirestore;
    int count;
    TextView i_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote);

        firebaseFirestore=FirebaseFirestore.getInstance();

        vname=findViewById(R.id.vname);
        vpassword=findViewById(R.id.vpassword);
        c1name=findViewById(R.id.c1name);
        c1aadhaar=findViewById(R.id.c1aadhaar);
        c2name=findViewById(R.id.c2name);
        c2aadhaar=findViewById(R.id.c2aadhaar);
        c3name=findViewById(R.id.c3name);
        c3aadhaar=findViewById(R.id.c3aadhaar);
        btn_create=findViewById(R.id.btn_create);
        pos = findViewById(R.id.pos);
        neg = findViewById(R.id.neg);
        i_count = findViewById(R.id.i_count);

        progressBar=findViewById(R.id.progressbar);

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(i_count.getText().toString());
                count++;
                i_count.setText(String.valueOf(count));

                if (count==2){
                    c2name.setVisibility(View.VISIBLE);
                    c2aadhaar.setVisibility(View.VISIBLE);
                } else if (count==3){
                    c3name.setVisibility(View.VISIBLE);
                    c3aadhaar.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String votingname=vname.getText().toString();
                String password=vpassword.getText().toString();
                String candidate1name=c1name.getText().toString();
                String candidate1aadhaar=c1aadhaar.getText().toString();
                String candidate2name=c2name.getText().toString();
                String candidate2aadhaar=c2aadhaar.getText().toString();
                String candidate3name=c3name.getText().toString();
                String candidate3aadhaar=c3aadhaar.getText().toString();

                Random random=new Random();
                int n=1000000+random.nextInt(9999999);
                String code=String.valueOf(n);

                boolean result1 = Verhoeff.validateVerhoeff(candidate1aadhaar);
                String msg1 = String.valueOf(result1);

                boolean result2 = Verhoeff.validateVerhoeff(candidate2aadhaar);
                String msg2 = String.valueOf(result2);

                boolean result3 = Verhoeff.validateVerhoeff(candidate3aadhaar);
                String msg3 = String.valueOf(result3);

                if (votingname.isEmpty()) {
                    vname.setError("this field can't be empty");
                } else if (!NAME.matcher(votingname).matches()) {
                    vname.setError("No White Spaces Are Allowed");
                } else if (password.length() < 5) {
                    vpassword.setError("password is too weak");
                } else if (candidate1name.isEmpty()) {
                    c1name.setError("this field can't be empty");
                } else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(candidate1name, 0);
                    hashMap.put(candidate2name, 0);
                    hashMap.put(candidate3name, 0);
                    hashMap.put("votingname", votingname);
                    hashMap.put("votercode", code);
                    hashMap.put("password", password);
                    hashMap.put("candidate1name", candidate1name);
                    hashMap.put("candidate2name", candidate2name);
                    hashMap.put("candidate3name", candidate3name);

                    HashMap<String, Object> hashMap1 = new HashMap<>();
                    hashMap1.put("votingname", votingname);

                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put("votercode", code);

                    Query query = FirebaseDatabase.getInstance().getReference().child("Voting Details")
                            .orderByChild("votingname").equalTo(votingname);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.getChildrenCount() > 0) {
                                Toast.makeText(CreateVote.this, "this voting name already exist", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.setVisibility(View.VISIBLE);

                                FirebaseDatabase.getInstance().getReference("Voting Details")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(hashMap1)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "onComplete: details stored successfully");
                                                } else {
                                                    Log.d(TAG, "onComplete: error in storing password");
                                                }
                                            }
                                        });

                                firebaseFirestore.collection("votes")
                                        .document(votingname)
                                        .set(hashMap)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                startActivity(new Intent(getApplicationContext(), VoteSuccess.class));
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateVote.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                firebaseFirestore.collection("voting code")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .set(hashMap2)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(CreateVote.this, "Code Generated", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateVote.this, "error in generating code", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}