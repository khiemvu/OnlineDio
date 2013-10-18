package com.qsoft.OnlineDio.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.qsoft.OnlineDio.Model.HomeModel;
import com.qsoft.OnlineDio.Model.NsMenuItemModel;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity implements AdapterView.OnItemClickListener
{

    private ListView mDrawerList;
    private DrawerLayout mDrawer;
    private CustomActionBarDrawerToggle mDrawerToggle;
    private String[] menuItems;
    private View myStationReLayout;
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
    private List<HomeModel> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        fillDataToListView();
        // enable ActionBar app icon to behave as action to toggle nav drawer

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // set a custom shadow that overlays the main content when the drawer
        // opens
        mDrawer.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        initMenu();
        mDrawerToggle = new CustomActionBarDrawerToggle(this, mDrawer);
        mDrawer.setDrawerListener(mDrawerToggle);
        Button btNavigate = (Button) findViewById(R.id.btNavigate);
        btNavigate.setOnClickListener(onclickListener);


    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {

                case R.id.myStationReLayout:

                    //Toast.makeText(getApplicationContext(), "fafaf", Toast.LENGTH_LONG).show();
                    break;
                case R.id.btNavigate:
                    mDrawer.openDrawer(mDrawerList);

                    myStationReLayout = findViewById(R.id.myStationReLayout);
                    myStationReLayout.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            Toast.makeText(getApplicationContext(), "fafaf", Toast.LENGTH_LONG).show();
                        }
                    });
                    break;
            }
        }
    };

    private void fillDataToListView()
    {
        rowItems = new ArrayList<HomeModel>();
        for (int i = 0; i < titles.length; i++)
        {
            HomeModel item = new HomeModel(avatars[i], titles[i], names[i], likes[i], comments[i], times[i]);
            rowItems.add(item);
        }
        listView = (ListView) findViewById(R.id.home_lvDetail);
        ListViewArrayAdapter adapter = new ListViewArrayAdapter(this,
                R.layout.home_items_layout, rowItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void initMenu()
    {
        NsMenuAdapter mAdapter = new NsMenuAdapter(this);

        // Add Header
        mAdapter.addHeader(R.string.ns_menu_main_header);

        // Add first block

        menuItems = getResources().getStringArray(
                R.array.ns_menu_items);
        String[] menuItemsIcon = getResources().getStringArray(
                R.array.ns_menu_items_icon);

        int res = 0;
        for (String item : menuItems)
        {

            int id_title = getResources().getIdentifier(item, "string",
                    this.getPackageName());
            int id_icon = getResources().getIdentifier(menuItemsIcon[res],
                    "drawable", this.getPackageName());

            NsMenuItemModel mItem = new NsMenuItemModel(id_title, id_icon);
            if (res == 6)
            {
                mItem.isButton = true;
            }

            mAdapter.addItem(mItem);
            res++;
        }


        mDrawerList = (ListView) findViewById(R.id.sideBar_lvDrawer);
        if (mDrawerList != null)
        {
            mDrawerList.setAdapter(mAdapter);
        }

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        /*
         * The action bar home_layout/up should open or close the drawer.
		 * ActionBarDrawerToggle will take care of this.
		 */
        if (mDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    private class CustomActionBarDrawerToggle extends ActionBarDrawerToggle
    {
        public CustomActionBarDrawerToggle(Activity mActivity, DrawerLayout mDrawerLayout)
        {
            super(
                    mActivity,
                    mDrawerLayout,
                    R.drawable.ic_drawer,
                    0, 0);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id)
        {
            // Highlight the selected item, update the title, and close the drawer
            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            String text = "menu click... should be implemented";
            Toast.makeText(HomeActivity.this, text, Toast.LENGTH_LONG).show();
            if (position == 2)
            {
                Intent intent = new Intent(HomeActivity.this, ProgramActivity.class);
                startActivity(intent);
            }
            mDrawer.closeDrawer(mDrawerList);
        }

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
