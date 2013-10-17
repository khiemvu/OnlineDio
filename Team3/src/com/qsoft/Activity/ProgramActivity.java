package com.qsoft.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import com.qsoft.FragmentActivity.DetailFragmentActivity;
import com.qsoft.FragmentActivity.ThumnailFragmentActivity;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/16/13
 */
public class ProgramActivity extends FragmentActivity
{
    private RadioButton btThumnail;
    private RadioButton btDetail;
    private Button btStar;
    private Button btLike;
    private Button btList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        initComponent();

        btThumnail.setOnClickListener(onclickListener);
        btDetail.setOnClickListener(onclickListener);
    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.program_btThumnail:
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    ThumnailFragmentActivity thumnailFragmentActivity = new ThumnailFragmentActivity();
                    fragmentTransaction.replace(R.id.program_fl_generic, thumnailFragmentActivity);
                    fragmentTransaction.commit();
                    break;
                case R.id.program_btDetail:
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    DetailFragmentActivity detailFragmentActivity = new DetailFragmentActivity();
                    transaction.replace(R.id.program_fl_generic, detailFragmentActivity);
                    transaction.commit();
                    break;
            }
        }
    };

    private void initComponent()
    {
        btDetail = (RadioButton) findViewById(R.id.program_btDetail);
        btThumnail = (RadioButton) findViewById(R.id.program_btThumnail);
        btStar = (Button) findViewById(R.id.program_btFavorite);
        btLike = (Button) findViewById(R.id.program_btLike);
        btList = (Button) findViewById(R.id.program_btList);
    }
}
