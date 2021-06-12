package com.example.customizevoting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterPassword extends AppCompatActivity {
    TextView verify_pass,cname1,cname2,cname3
            ,cname4,cname5,cname6,verify_name;
    Button verify_btn;
    EditText ed_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        verify_pass=findViewById(R.id.verify_pass);
        verify_name=findViewById(R.id.verify_name);
        cname1=findViewById(R.id.cname1);
        cname2=findViewById(R.id.cname2);
        cname3=findViewById(R.id.cname3);
        cname4 = findViewById(R.id.cname4);
        cname5 = findViewById(R.id.cname5);
        cname6 = findViewById(R.id.cname6);
        verify_btn=findViewById(R.id.verify_btn);
        ed_pass=findViewById(R.id.ed_pass);

        String password=getIntent().getStringExtra("password");
        String votingname=getIntent().getStringExtra("votingname");
        String name1=getIntent().getStringExtra("name1");
        String name2=getIntent().getStringExtra("name2");
        String name3=getIntent().getStringExtra("name3");
        String name4=getIntent().getStringExtra("name4");
        String name5=getIntent().getStringExtra("name5");
        String name6=getIntent().getStringExtra("name6");

        verify_pass.setText(password);
        verify_name.setText(votingname);
        cname1.setText(name1);
        cname2.setText(name2);
        cname3.setText(name3);
        cname4.setText(name4);
        cname5.setText(name5);
        cname6.setText(name6);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_pass.getText().toString().equals(verify_pass.getText().toString())){
                    Intent intent=new Intent(getApplicationContext(),Candidate.class);
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
                else{
                    Toast.makeText(EnterPassword.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}