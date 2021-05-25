package com.example.customizevoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SetNamePassword extends AppCompatActivity {
    private static final String TAG = "SetNamePassword";
    EditText vname, vpassword;
    Button nxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_name_password);

        vname = findViewById(R.id.vname);
        vpassword = findViewById(R.id.vpassword);
        nxt = findViewById(R.id.nxt);

        nxt.setOnClickListener(v -> {
            String votingname = vname.getText().toString();
            String password = vpassword.getText().toString();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("votingname", votingname);

            Query query = FirebaseDatabase.getInstance().getReference().child("Voting Details")
                    .orderByChild("votingname").equalTo(votingname);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    if (snapshot.getChildrenCount() > 0) {
                        Toast.makeText(SetNamePassword.this, "this voting name exist", Toast.LENGTH_SHORT).show();
                    } else {
                        FirebaseDatabase.getInstance().getReference("Voting Details")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(),CreateVote.class);
                                            intent.putExtra("votingname",votingname);
                                            intent.putExtra("password",password);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                            Log.d(TAG, "onComplete: details stored successfully");
                                        } else {
                                            Log.d(TAG, "onComplete: error in storing password");
                                        }
                                    }
                                });
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });

        });
    }
}