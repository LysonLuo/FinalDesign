package me.lyson.design.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.lyson.design.R;
import me.lyson.design.ui.common.BaseActivity;
import me.lyson.design.ui.main.MainActivity;

/**
 * Created by Lyson on 15/4/7.
 */
public class LoginActivity extends BaseActivity {
    @InjectView(R.id.button_login)
    Button loginButton;
    @InjectView(R.id.edittext_phone_login)
    EditText phoneEditText;
    @InjectView(R.id.edittext_password_login)
    EditText passwordEditText;
    @InjectView(R.id.button_send_password)
    Button sendPasswordButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowToolbar(false);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        sendPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(60100, 1000) {
                    @TargetApi(16)
                    @Override
                    public void onTick(long millisUntilFinished) {
                        sendPasswordButton.setText(millisUntilFinished / 1000 + "秒后重发");
                        sendPasswordButton.setEnabled(false);

                    }

                    @Override
                    public void onFinish() {
                        sendPasswordButton.setText("发送动态密码");
                        sendPasswordButton.setEnabled(true);
                    }
                }.start();
            }
        });
    }
}
