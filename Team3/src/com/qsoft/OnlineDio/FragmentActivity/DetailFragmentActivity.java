package com.qsoft.OnlineDio.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/17/13
 */
public class DetailFragmentActivity extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View viewer = (View) inflater.inflate(R.layout.program_detail_fragment, container, false);
        return viewer;
    }
}
