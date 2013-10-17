package com.qsoft.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.qsoft.OnlineDio.R;
import com.qsoft.Validate.Constant;
import com.qsoft.Validate.EmailFormatValidator;
import com.qsoft.Validate.NetworkUtil;

public class LoginActivity extends Activity
{

    EmailFormatValidator emailFormatValidator = new EmailFormatValidator();

    private Button btLogin;
    private Button btBack;
    private TextView tvResetPas;
    private EditText etEmail;
    private EditText etPass;
    private ImageButton ibtDelEmail;
    private ImageButton ibtDelPass;
    private String email;
    private String pass;

    private Context context = LoginActivity.this;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        initComponent();

        btBack.setOnClickListener(onclickListener);
        btLogin.setOnClickListener(onclickListener);
        tvResetPas.setOnClickListener(onclickListener);
        ibtDelEmail.setOnClickListener(onclickListener);
        ibtDelPass.setOnClickListener(onclickListener);
        etEmail.addTextChangedListener(textChangeListener);
        etPass.addTextChangedListener(textChangeListener);
        getEmailAndPassword();
    }

    private final TextWatcher textChangeListener = new TextWatcher()
    {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count)
        {
            if (!etEmail.getText().toString().isEmpty())
            {
                ibtDelEmail.setVisibility(View.VISIBLE);
                ibtDelPass.setVisibility(View.INVISIBLE);
            }
            if (!etPass.getText().toString().isEmpty())
            {
                ibtDelPass.setVisibility(View.VISIBLE);
                ibtDelEmail.setVisibility(View.INVISIBLE);
            }

            if (!etEmail.getText().toString().isEmpty() && !etPass.getText().toString().isEmpty())
            {
                btLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_bt_active));
            }
            else
            {
                btLogin.setBackgroundDrawable(getResources().getDrawable(R.drawable.login_bt_normal));
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after)
        {
        }

        @Override
        public void afterTextChanged(Editable s)
        {
            etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus && !etEmail.getText().toString().isEmpty())
                    {
                        ibtDelEmail.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        ibtDelEmail.setVisibility(View.INVISIBLE);
                    }
                }
            });
            etPass.setOnFocusChangeListener(new View.OnFocusChangeListener()
            {
                public void onFocusChange(View v, boolean hasFocus)
                {
                    if (hasFocus && !etPass.getText().toString().isEmpty())
                    {
                        ibtDelPass.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        ibtDelPass.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    };

    private void initComponent()
    {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btBack = (Button) findViewById(R.id.btBack);
        btLogin = (Button) findViewById(R.id.login_btLogin);
        tvResetPas = (TextView) findViewById(R.id.tvResetPass);
        ibtDelEmail = (ImageButton) findViewById(R.id.ibtDelEmail);
        ibtDelPass = (ImageButton) findViewById(R.id.ibtDelPass);
    }

    private void getEmailAndPassword()
    {
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        if (!email.equals(" ") && !pass.equals(" "))
        {
            btLogin.setEnabled(true);
            btLogin.setClickable(true);
        }
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
//                    if (!checkNetwork())
//                    {
//                        break;
//                    }
//                    else
//                    {
                    checkLogin();
//                    }
                    break;
                case R.id.tvResetPass:
                    resetPassWord();
                    break;
                case R.id.ibtDelEmail:
                    etEmail.setText("");
                    break;
                case R.id.ibtDelPass:
                    etPass.setText("");
                    break;
            }
        }
    };

    private boolean checkNetwork()
    {
        boolean result = true;
        String status = NetworkUtil.getConnectivityStatusString(context);
        if (status.equals(Constant.NOT_CONNECTED_TO_INTERNET.getValue()))
        {
            result = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
            builder.setMessage(Constant.MESSAGE_CONNECTION_INTERNET.getValue());
            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
            builder.show();
        }
        return result;
    }

    private void resetPassWord()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("Forgot Password");
        LayoutInflater inflater = LoginActivity.this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.login_reset_password_layout, null))
                .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //TODO sent password again
                    }
                });
        builder.setView(inflater.inflate(R.layout.login_reset_password_layout, null))
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
        builder.show();
    }

    private void checkLogin()
    {
        getEmailAndPassword();


        if ((emailFormatValidator.validate(email)) == false)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
            builder.setMessage(Constant.EMAIL_INVALID.getValue());
            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
            builder.show();
        }
        else
        {
            if (email.equals(Constant.EMAIL.getValue()) && pass.equals(Constant.PASSWORD.getValue()))
            {
                Intent intent2 = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent2);
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(Constant.TITLE_MESSAGE.getValue());
                builder.setMessage(Constant.EMAIL_OR_PASSWORD_NOT_CORRECT.getValue());
                builder.setNegativeButton(R.string.OK, new OkOnClickListener());
                builder.show();
            }
        }
    }

    private final class OkOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which)
        {
            etEmail.setText(Constant.EMAIL.getValue());
            etPass.setText(Constant.PASSWORD.getValue());
        }
    }
}
