package com.qsoft.OnlineDio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * User: khiemvx
 * Date: 10/14/13
 */
public class HomeActivity extends Activity implements AdapterView.OnItemClickListener
{
    public static final String[] titles = new String[]{"Sound Of Silence", "Sound Of Silence", "Sound Of Silence",
            "Sound Of Silence", "Sound Of Silence", "Sound Of Silence"};

    public static final String[] names = new String[]{
            "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx"};
    public static final Integer[] likes = new Integer[]{122, 246, 246, 246, 246, 246};
    public static final Integer[] comments = new Integer[]{132, 246, 246, 246, 246, 246};
    public static final String[] times = new String[]{
            "a day ago", "a day ago", "a day ago", "a day ago", "a day ago", "a day ago"};
    public static final Integer[] avatars = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
            R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};

    private ListView listView;
    private List<RowsInfo> rowItems;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        rowItems = new ArrayList<RowsInfo>();
        for (int i = 0; i < titles.length; i++)
        {
            RowsInfo item = new RowsInfo(avatars[i], titles[i], names[i], likes[i], comments[i], times[i]);
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.home_lvDetail);
        ListViewArrayAdapter adapter = new ListViewArrayAdapter(this,
                R.layout.items_detail, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
    {
        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + rowItems.get(position),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }
}
