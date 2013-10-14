package com.qsoft.OnlineDio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity
{
    EmailFormatValidator emailFormatValidator = new EmailFormatValidator();

    private Button btLogin;
    private Button btBack;
    private TextView tvResetPas;
    private EditText etEmail;
    private EditText etPass;
    private String email;
    private String pass;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btBack = (Button) findViewById(R.id.btBack);
        btLogin = (Button) findViewById(R.id.login_btLogin);
        tvResetPas = (TextView) findViewById(R.id.tvResetPass);

        btBack.setOnClickListener(onclickListener);
        btLogin.setOnClickListener(onclickListener);
        tvResetPas.setOnClickListener(onclickListener);

        if (email != null && pass != null)
        {
            btLogin.setEnabled(true);
            btLogin.setClickable(true);
        }

        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btBack:
                    Intent intentBack = new Intent(LoginActivity.this, FirstLaunchActivity.class);
                    startActivity(intentBack);
                    break;
                case R.id.login_btLogin:
                    if ((emailFormatValidator.validate(email)) == false)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setTitle("Error Signing In");
                        builder.setMessage("Invalid email address");
                        builder.setNegativeButton(R.string.OK, new OkOnClickListener());
                        builder.show();
                    }
                    else
                    {
                        if (email != "test@gmail.com" && pass != "123456")
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("Error Signing In");
                            builder.setMessage("Email address or password is incorrect.");
                            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
                            builder.show();
                        }
                        else if (email == "test@gmail.com" && pass == "123456")
                        {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            //TODO home
                        }
                    }
                    break;
                case R.id.tvResetPass:
                    //Todo reset
                    break;
            }
        }
    };

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which)
        {
            etEmail.setText("");
            etPass.setText("");
        }
    }

}
