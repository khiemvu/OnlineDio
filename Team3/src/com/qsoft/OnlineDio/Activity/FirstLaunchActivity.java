package com.qsoft.OnlineDio.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.qsoft.OnlineDio.R;

/**
 * Created with IntelliJ IDEA.
 * User: Dell 3360
 * Date: 10/14/13
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class FirstLaunchActivity extends Activity
{
    private Button launch_btLogin;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_launch_layout);
        launch_btLogin = (Button) findViewById(R.id.launch_btLogin);
        launch_btLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(FirstLaunchActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}