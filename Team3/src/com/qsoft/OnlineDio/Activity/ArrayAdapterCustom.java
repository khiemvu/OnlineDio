package com.qsoft.OnlineDio.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.qsoft.OnlineDio.Model.HomeModel;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;

/**
 * User: Dell 3360
 * Date: 10/20/13
 * Time: 10:01 AM
 */
public class ArrayAdapterCustom extends ArrayAdapter<HomeModel>
{
    private TextView tvTitle;
    private TextView tvUserName;
    private TextView tvLike;
    private TextView tvComment;
    private TextView tvTime;
    private final ArrayList<HomeModel> feeds;
    private final Context context;

    public ArrayAdapterCustom(Context context, int tvResourceId, ArrayList<HomeModel> feeds)
    {
        super(context, tvResourceId, feeds);
        this.feeds = feeds;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.home_items_layout, null);
        }
        HomeModel feed = feeds.get(position);
        setUpViewFindByID(v);
        if (feed != null)
        {
            tvTitle.setText(feed.getTitle());
            tvUserName.setText(feed.getUserName());
            tvLike.setText(feed.getLike());
            tvComment.setText(feed.getComment());
            tvTime.setText(feed.getTime());
        }
        return v;
    }

    private void setUpViewFindByID(View v)
    {
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvUserName = (TextView) v.findViewById(R.id.tvName);
        tvLike = (TextView) v.findViewById(R.id.tvLike);
        tvComment = (TextView) v.findViewById(R.id.tvComment);
        tvTime = (TextView) v.findViewById(R.id.tvTime);
    }
}
