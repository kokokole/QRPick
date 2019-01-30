package com.ldcc.eleven.qrpick.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.activities.customer.CustomerActivity;
import com.ldcc.eleven.qrpick.activities.manager.ManagerActivity;

public class LoginActivity extends AppCompatActivity {
    EditText id, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);


        id = findViewById(R.id.id);
        pw = findViewById(R.id.pw);

        Button button = findViewById(R.id.sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //관리자 계정이면 flag 0, 일반 계정이면 flag 1
                if(id.getText().toString().equals("")
                        || pw.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();

                }
                else if(id.getText().toString().equals("admin") &&
                        pw.getText().toString().equals("1234")) //관리자계정
                {
                    Toast.makeText(LoginActivity.this, "관리자로그인", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ManagerActivity.class).putExtra("flag",0));
                }
                else{ //고객
                    startActivity(new Intent(getApplicationContext(), CustomerActivity.class).putExtra("flag",1));
                }
            }
        });




      /*  id = findViewById(R.id.id);
        Button button = findViewById(R.id.sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //관리자 계정이면 flag 0, 일반 계정이면 flag 1
                if(id.getText().toString().equals("")){
                    startActivity(new Intent(getApplicationContext(), ManagerActivity.class).putExtra("flag",0));
                }
                else{
                    startActivity(new Intent(getApplicationContext(), CustomerActivity.class).putExtra("flag",1));
                }
            }
        });*/

    }
}
