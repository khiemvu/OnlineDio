package com.qsoft.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/17/13
 */
public class CommentFragmentActivity extends Fragment
{
    private TextView tvDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View viewer = (View) inflater.inflate(R.layout.program_detail_fragment, container, false);
        tvDetail = (TextView) viewer.findViewById(R.id.program_tvDetails);
        return viewer;
    }
}
