package com.qsoft.OnlineDio.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import com.qsoft.OnlineDio.FragmentActivity.ThumbnailFragment;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/16/13
 */
public class ProgramFragment extends Fragment
{
    private RadioButton rbtThumnail;
    private RadioButton rbtDetail;
    private RadioButton rbtComment;
    private Button btStar;
    private Button btLike;
    private Button btList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.program_layout, null);
        setUpUI(view);
        doShowThumbnail();
        setUpListenerController();
        return view;
    }


    private void doShowThumbnail()
    {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.program_fl_generic, new ThumbnailFragment(), "ThumbnailFragment");
        ft.commit();
    }

    private void setUpUI(View view)
    {
//        rbtDetail = (RadioButton) view.findViewById(R.id.program_rbtDetail);
        rbtThumnail = (RadioButton) view.findViewById(R.id.program_rbtThumnail);
//        btStar = (Button)view. findViewById(R.id.program_btFavorite);
//        btLike = (Button)view. findViewById(R.id.program_btLike);
//        btList = (Button)view.findViewById(R.id.program_btList);
//        rbtComment = (RadioButton)view. findViewById(R.id.program_rbtComment);
    }

    private void setUpListenerController()
    {
        rbtThumnail.setOnClickListener(onclickListener);
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.program_layout);
//
//        initComponent();
//
//        rbtThumnail.setOnClickListener(onclickListener);
//        rbtDetail.setOnClickListener(onclickListener);
//        rbtComment.setOnClickListener(onclickListener);
//    }
//
    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.program_rbtThumnail:
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.program_rbtThumnail, new ThumbnailFragment(), "ThumbnailFragment");
                    ft.commit();
                    break;
//                case R.id.program_rbtDetail:
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    DetailFragment detailFragmentActivity = new DetailFragment();
//                    transaction.replace(R.id.program_fl_generic, detailFragmentActivity);
//                    transaction.commit();
//                    break;
//                case R.id.program_rbtComment:
//                    FragmentTransaction transactionComment = getSupportFragmentManager().beginTransaction();
//                    CommentFragment commentFragmentActivity = new CommentFragment();
//                    transactionComment.replace(R.id.program_fl_generic, commentFragmentActivity);
//                    transactionComment.commit();
//                    break;
            }
        }
    };


}
