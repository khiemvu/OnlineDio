package com.qsoft.OnlineDio.Activity;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.qsoft.OnlineDio.Authenticate.AccountGeneral;
import com.qsoft.OnlineDio.Authenticate.User;
import com.qsoft.OnlineDio.R;
import com.qsoft.OnlineDio.Validate.Constant;
import com.qsoft.OnlineDio.Validate.EmailFormatValidator;
import com.qsoft.OnlineDio.Validate.NetworkUtil;

import static com.qsoft.OnlineDio.Authenticate.AccountGeneral.sServerAuthenticate;

public class LoginActivity extends AccountAuthenticatorActivity
{
    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_ACCOUNT_NAME = "ACCOUNT_NAME";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";
    public final static String PARAM_USER_PASS = "USER_PASS";
    public static final String KEY_ERROR_MESSAGE = "ERR_MSG";

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

    private AccountManager mAccountManager;
    private String mAuthTokenType;

    private Context context = LoginActivity.this;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        initComponent();
        mAccountManager = AccountManager.get(getBaseContext());

//        String accountName = getIntent().getStringExtra(ARG_ACCOUNT_NAME);
        mAuthTokenType = getIntent().getStringExtra(ARG_AUTH_TYPE);
        if (mAuthTokenType == null)
        {
            mAuthTokenType = AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS;
        }


        setUpOnclickListener();
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        if (!email.equals(" ") && !pass.equals(" "))
        {
            btLogin.setEnabled(true);
            btLogin.setClickable(true);
        }
    }

    private void initComponent()
    {
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btBack = (Button) findViewById(R.id.login_btBack);
        btLogin = (Button) findViewById(R.id.login_btLogin);
        tvResetPas = (TextView) findViewById(R.id.tvResetPass);
        ibtDelEmail = (ImageButton) findViewById(R.id.ibtDelEmail);
        ibtDelPass = (ImageButton) findViewById(R.id.ibtDelPass);
    }

    private void setUpOnclickListener()
    {
        btBack.setOnClickListener(onclickListener);
        btLogin.setOnClickListener(onclickListener);
        tvResetPas.setOnClickListener(onclickListener);
        ibtDelEmail.setOnClickListener(onclickListener);
        ibtDelPass.setOnClickListener(onclickListener);
        etEmail.addTextChangedListener(textChangeListener);
        etPass.addTextChangedListener(textChangeListener);
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
                case R.id.login_btBack:
                    Intent intentBack = new Intent(LoginActivity.this, FirstLaunchActivity.class);
                    startActivity(intentBack);
                    break;
                case R.id.login_btLogin:
                    if (!checkNetwork())
                    {
                        break;
                    }
                    else
                    {
                    checkLogin();
                    }
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
        email = etEmail.getText().toString();
        pass = etPass.getText().toString();
        final String accountType = getIntent().getStringExtra(ARG_ACCOUNT_TYPE);
        new AsyncTask<String, Void, Intent>()
        {

            @Override
            protected Intent doInBackground(String... params)
            {

                Log.d("udinic", TAG + "> Started authenticating");

                String authtoken = null;
                Bundle data = new Bundle();
                try
                {

                    User user = sServerAuthenticate.userSignIn(email, pass, mAuthTokenType);
                    if (user != null)
                    {
                        authtoken = user.getAccess_token();
                    }
                    data.putString(AccountManager.KEY_ACCOUNT_NAME, email);
                    data.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType);
                    data.putString(AccountManager.KEY_AUTHTOKEN, authtoken);
                    data.putString(PARAM_USER_PASS, pass);

                }
                catch (Exception e)
                {
                    data.putString(KEY_ERROR_MESSAGE, e.getMessage());
                }

                final Intent res = new Intent();
                res.putExtras(data);
                return res;
            }

            @Override
            protected void onPostExecute(Intent intent)
            {
                if (intent.hasExtra(KEY_ERROR_MESSAGE))
                {
                    Toast.makeText(getBaseContext(), intent.getStringExtra(KEY_ERROR_MESSAGE), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    finishLogin(intent);
                }
            }
        }.execute();
//        if (!email.equals(" ") && !pass.equals(" "))
//        {
//            btLogin.setEnabled(true);
//            btLogin.setClickable(true);
//        }
//
//        if ((emailFormatValidator.validate(email)) == false)
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
//            builder.setMessage(Constant.EMAIL_INVALID.getValue());
//            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
//            builder.show();
//        }
//        else
//        {
//            if (email.equals(Constant.EMAIL.getValue()) && pass.equals(Constant.PASSWORD.getValue()))
//            {
//                startActivity(new Intent(this, SlidebarActivity.class));
//            }
//            else
//            {
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle(Constant.TITLE_MESSAGE.getValue());
//                builder.setMessage(Constant.EMAIL_OR_PASSWORD_NOT_CORRECT.getValue());
//                builder.setNegativeButton(R.string.OK, new OkOnClickListener());
//                builder.show();
//            }
//        }
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

    private void finishLogin(Intent intent)
    {
        Log.d("udinic", TAG + "> finishLogin");

        String accountName = intent.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = intent.getStringExtra(PARAM_USER_PASS);
        final Account account = new Account(accountName, intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));

        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false))
        {
            Log.d("udinic", TAG + "> finishLogin > addAccountExplicitly");
            String authtoken = intent.getStringExtra(AccountManager.KEY_AUTHTOKEN);
            String authtokenType = mAuthTokenType;

            // Creating the account on the device and setting the auth token we got
            // (Not setting the auth token will cause another call to the server to authenticate the user)
            mAccountManager.addAccountExplicitly(account, accountPassword, null);
            mAccountManager.setAuthToken(account, authtokenType, authtoken);
        }
        else
        {
            Log.d("udinic", TAG + "> finishLogin > setPassword");
            mAccountManager.setPassword(account, accountPassword);
        }

        setAccountAuthenticatorResult(intent.getExtras());
        setResult(RESULT_OK, intent);
        finish();
    }
}
