package com.qsoft.OnlineDio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/14/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class FirstLaunchActivity extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_launch);
        Button btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(FirstLaunchActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}