package com.qsoft.OnlineDio.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.qsoft.OnlineDio.Activity.ArrayAdapterCustom;
import com.qsoft.OnlineDio.Activity.ProgramFragment;
import com.qsoft.OnlineDio.Model.HomeModel;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment
{
    private Button btNavigate;
    private ListView home_lvDetail;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.home_layout, null);
        setUpUI(view);
        ArrayList<HomeModel> feedList = new ArrayList<HomeModel>();
        setUpDataToHomeListView(getActivity(), feedList);
        setUpListenerController();
        return view;
    }

    private void setUpListenerController()
    {
        btNavigate.setOnClickListener(onClickListener);
        home_lvDetail.setOnItemClickListener(onItemClickListener);
    }

    private void setUpDataToHomeListView(Context context, ArrayList<HomeModel> feedList)
    {
        HomeModel item1 = new HomeModel("Soul of Silence", "khiemnt", "Likes: 3", "Comments: 2", "5 days ago");

        for (int i = 0; i < 10; i++)
        {
            feedList.add(item1);
        }

        home_lvDetail.setAdapter(new ArrayAdapterCustom(context, R.layout.home_items_layout, feedList));
    }

    private ListView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            doShowProgram();
        }
    };
    private View.OnClickListener onClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btNavigate:
                    showMenu();
                    break;
            }
        }
    };

    private void showMenu()
    {
        // do show home_bt_navigate
    }

    private void doShowProgram()
    {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.slidebar_homeFragment, new ProgramFragment(), "ProgramFragment");
        ft.addToBackStack("ProgramFragment");
        ft.commit();
    }

    private void setUpUI(View view)
    {
        btNavigate = (Button) view.findViewById(R.id.btNavigate);
        home_lvDetail = (ListView) view.findViewById(R.id.home_lvDetail);
    }
}

