package com.example.customizevoting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class UpdateProfile extends AppCompatActivity {
    EditText updatename,updatebio;
    Button btn_update;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        firebaseFirestore=FirebaseFirestore.getInstance();

        updatebio=findViewById(R.id.updatebio);
        updatename=findViewById(R.id.updatename);
        btn_update=findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String uname=updatename.getText().toString();
                String ubio=updatebio.getText().toString();


                    HashMap<String,Object> hashMap=new HashMap<>();
                    hashMap.put("name",uname);
                    hashMap.put("bio",ubio);

                    firebaseFirestore.collection("User Profile")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .set(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startActivity(new Intent(getApplicationContext(),Profile.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(UpdateProfile.this, "There Was Some Error While Updating Profile", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

        });
    }
}