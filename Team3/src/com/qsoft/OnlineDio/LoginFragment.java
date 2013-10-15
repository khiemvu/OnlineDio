//package com.qsoft.OnlineDio;
//
//import android.app.AlertDialog;
//import android.app.Fragment;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import com.qsoft.Validate.Constant;
//import com.qsoft.Validate.EmailFormatValidator;
//import com.qsoft.Validate.NetworkUtil;
//
//public class LoginFragment extends Fragment
//{
//
//    EmailFormatValidator emailFormatValidator = new EmailFormatValidator();
//
//    private Button btLogin;
//    private Button btBack;
//    private TextView tvResetPas;
//    private EditText etEmail;
//    private EditText etPass;
//    private String email;
//    private String pass;
//
//    private Context context = getActivity();
//
//    @Override
//    public void onStart()
//    {
//        super.onStart();
//        initControl();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        return inflater.inflate(R.layout.login, container, false);
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.login);
////
//        initControl();
//
//        btBack.setOnClickListener(onclickListener);
//        btLogin.setOnClickListener(onclickListener);
//        tvResetPas.setOnClickListener(onclickListener);
//
//        getEmailAndPassword();
//    }
//
//    private void initControl()
//    {
//        etEmail = (EditText) getView().findViewById(R.id.etEmail);
//        etPass = (EditText) getView().findViewById(R.id.etPass);
//        btBack = (Button) getView().findViewById(R.id.btBack);
//        btLogin = (Button) getView().findViewById(R.id.login_btLogin);
//        tvResetPas = (TextView) getView().findViewById(R.id.tvResetPass);
//    }
//
//    private void getEmailAndPassword()
//    {
//        email = etEmail.getText().toString();
//        pass = etPass.getText().toString();
//        if (!email.equals(" ") && !pass.equals(" "))
//        {
//            btLogin.setEnabled(true);
//            btLogin.setClickable(true);
//        }
//    }
//
//    private final View.OnClickListener onclickListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View view)
//        {
//            switch (view.getId())
//            {
//                case R.id.btBack:
//                    Intent intentBack = new Intent(getActivity(), FirstLaunchActivity.class);
//                    startActivity(intentBack);
//                    break;
//                case R.id.login_btLogin:
//                    if (!checkNetwork())
//                    {
//                        break;
//                    }
//                    else
//                    {
//                        checkLogin();
//                    }
//                    break;
//                case R.id.tvResetPass:
//                    resetPassWord();
//                    break;
//            }
//        }
//    };
//
//    private boolean checkNetwork()
//    {
//        boolean result = true;
//        String status = NetworkUtil.getConnectivityStatusString(context);
//        if (status.equals(Constant.NOT_CONNECTED_TO_INTERNET.getValue()))
//        {
//            result = false;
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
//            builder.setMessage(Constant.MESSAGE_CONNECTION_INTERNET.getValue());
//            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
//            builder.show();
//        }
//        return result;
//    }
//
//    private void resetPassWord()
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Forgot Password");
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.dialog_password, null))
//                .setPositiveButton(R.string.reset, new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        //TODO sent password again
//                    }
//                });
//        builder.setView(inflater.inflate(R.layout.dialog_password, null))
//                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        //To change body of implemented methods use File | Settings | File Templates.
//                    }
//                });
//        builder.show();
//    }
//
//    private void checkLogin()
//    {
//        getEmailAndPassword();
//
//
//        if ((emailFormatValidator.validate(email)) == false)
//        {
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle(Constant.TITLE_MESSAGE.getValue());
//            builder.setMessage(Constant.EMAIL_INVALID.getValue());
//            builder.setNegativeButton(R.string.OK, new OkOnClickListener());
//            builder.show();
//        }
//        else
//        {
//            if (email.equals(Constant.EMAIL.getValue()) && pass.equals(Constant.PASSWORD.getValue()))
//            {
//                Intent intent2 = new Intent(getActivity(), HomeActivity.class);
//                startActivity(intent2);
//            }
//            else
//            {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle(Constant.TITLE_MESSAGE.getValue());
//                builder.setMessage(Constant.EMAIL_OR_PASSWORD_NOT_CORRECT.getValue());
//                builder.setNegativeButton(R.string.OK, new OkOnClickListener());
//                builder.show();
//            }
//        }
//    }
//
//    private final class OkOnClickListener implements
//            DialogInterface.OnClickListener
//    {
//        public void onClick(DialogInterface dialog, int which)
//        {
//            etEmail.setText(Constant.EMAIL.getValue());
//            etPass.setText(Constant.PASSWORD.getValue());
//        }
//    }
//
//}
