package com.qsoft.OnlineDio.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import com.qsoft.OnlineDio.FragmentActivity.CommentFragmentActivity;
import com.qsoft.OnlineDio.FragmentActivity.DetailFragmentActivity;
import com.qsoft.OnlineDio.FragmentActivity.ThumnailFragmentActivity;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/16/13
 */
public class ProgramActivity extends FragmentActivity
{
    private RadioButton rbtThumnail;
    private RadioButton rbtDetail;
    private RadioButton rbtComment;
    private Button btStar;
    private Button btLike;
    private Button btList;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);

        initComponent();

        rbtThumnail.setOnClickListener(onclickListener);
        rbtDetail.setOnClickListener(onclickListener);
        rbtComment.setOnClickListener(onclickListener);
    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.program_rbtThumnail:
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    ThumnailFragmentActivity thumnailFragmentActivity = new ThumnailFragmentActivity();
                    fragmentTransaction.replace(R.id.program_fl_generic, thumnailFragmentActivity);
                    fragmentTransaction.commit();
                    break;
                case R.id.program_rbtDetail:
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    DetailFragmentActivity detailFragmentActivity = new DetailFragmentActivity();
                    transaction.replace(R.id.program_fl_generic, detailFragmentActivity);
                    transaction.commit();
                    break;
                case R.id.program_rbtComment:
                    FragmentTransaction transactionComment = getSupportFragmentManager().beginTransaction();
                    CommentFragmentActivity commentFragmentActivity = new CommentFragmentActivity();
                    transactionComment.replace(R.id.program_fl_generic, commentFragmentActivity);
                    transactionComment.commit();
                    break;
            }
        }
    };

    private void initComponent()
    {
        rbtDetail = (RadioButton) findViewById(R.id.program_rbtDetail);
        rbtThumnail = (RadioButton) findViewById(R.id.program_rbtThumnail);
        btStar = (Button) findViewById(R.id.program_btFavorite);
        btLike = (Button) findViewById(R.id.program_btLike);
        btList = (Button) findViewById(R.id.program_btList);
        rbtComment = (RadioButton) findViewById(R.id.program_rbtComment);
    }
}
