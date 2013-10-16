package com.qsoft.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.qsoft.OnlineDio.R;

/**
 * User: khiemvx
 * Date: 10/16/13
 */
public class ProgramActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_layout);
    }
}
