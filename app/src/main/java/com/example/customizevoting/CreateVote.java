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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

    EditText vname,vpassword,c1name,c2name,c3name,c4name,c5name,c6name;
    Button btn_create;
    FirebaseFirestore firebaseFirestore;
    FloatingActionButton pos;
    int count;
    TextView i_count, name1, passowrd1;
    String votingname, password;
    LinearLayout l1,l2,l3,l4,l5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vote);

        firebaseFirestore=FirebaseFirestore.getInstance();


        votingname = getIntent().getStringExtra("votingname");
        password = getIntent().getStringExtra("password");

        name1 = findViewById(R.id.name1);
        passowrd1 = findViewById(R.id.password1);

        name1.setText(votingname);
        passowrd1.setText(password);

        // initializing layout

        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);
        l4 = findViewById(R.id.l4);
        l5 = findViewById(R.id.l5);

        vname=findViewById(R.id.vname);
        vpassword=findViewById(R.id.vpassword);
        c1name=findViewById(R.id.c1name);
        c2name=findViewById(R.id.c2name);
        c3name=findViewById(R.id.c3name);
        c4name = findViewById(R.id.c4name);
        c5name = findViewById(R.id.c5name);
        c6name = findViewById(R.id.c6name);

        btn_create=findViewById(R.id.btn_create);
        pos = findViewById(R.id.pos);
        i_count = findViewById(R.id.i_count);

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(i_count.getText().toString());
                count++;
                i_count.setText(String.valueOf(count));

                if (count==1){
                    l1.setVisibility(View.VISIBLE);
                } else if (count==2){
                    l2.setVisibility(View.VISIBLE);
                } else if (count == 3) {
                    l3.setVisibility(View.VISIBLE);
                } else if (count == 4) {
                    l4.setVisibility(View.VISIBLE);
                } else if (count == 5) {
                    l5.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String candidate1name = c1name.getText().toString();
                String candidate2name = c2name.getText().toString();
                String candidate3name = c3name.getText().toString();
                String candidate4name = c4name.getText().toString();
                String candidate5name = c5name.getText().toString();
                String candidate6name = c6name.getText().toString();

                String c2 = " ";
                String c3 = " ";
                String c4 = " ";
                String c5 = " ";
                String c6 = " ";

                c2 += candidate2name;
                c3 += candidate3name;
                c4 += candidate4name;
                c5 += candidate5name;
                c6 += candidate6name;

                Random random=new Random();
                int n=1000000+random.nextInt(9999999);
                String code=String.valueOf(n);

                if (candidate1name.isEmpty()) {
                    c1name.setError("this field can't be empty");
                } else {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put(candidate1name, 0);
                    hashMap.put(c2, 0);
                    hashMap.put(c3, 0);
                    hashMap.put(c4, 0);
                    hashMap.put(c5, 0);
                    hashMap.put(c6, 0);
                    hashMap.put("votingname", votingname);
                    hashMap.put("votercode", code);
                    hashMap.put("password", password);
                    hashMap.put("candidate1name", candidate1name);
                    hashMap.put("candidate2name", c2);
                    hashMap.put("candidate3name", c3);
                    hashMap.put("candidate4name", c4);
                    hashMap.put("candidate5name", c5);
                    hashMap.put("candidate6name", c5);

                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put("votercode", code);

                    firebaseFirestore.collection("votes")
                            .document(votingname).set(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(getApplicationContext(),VoteSuccess.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "onFailure: there was some error"+e);
                        }
                    });

                    firebaseFirestore.collection("voting code")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .set(hashMap2)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Log.d(TAG, "onFailure: "+e);
                        }
                    });
                }
            }
        });
    }
}