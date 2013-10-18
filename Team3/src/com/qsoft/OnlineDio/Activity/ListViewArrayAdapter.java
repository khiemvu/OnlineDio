package com.qsoft.OnlineDio.Activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.qsoft.OnlineDio.Model.HomeModel;
import com.qsoft.OnlineDio.R;

import java.util.List;

/**
 * User: Khiemvx
 * Date: 10/14/13
 */
public class ListViewArrayAdapter extends ArrayAdapter<HomeModel>
{
    private Context context;

    public ListViewArrayAdapter(Context context, int resourceID, List<HomeModel> objects)
    {
        super(context, resourceID, objects);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder
    {
        ImageView ivAvatar;
        TextView tvTitle;
        TextView tvName;
        TextView tvLike;
        TextView tvComment;
        TextView tvTime;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        HomeModel rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.home_items_layout, null);
            holder = new ViewHolder();

            holder.ivAvatar = (ImageView) convertView.findViewById(R.id.ivAvatar);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvLike = (TextView) convertView.findViewById(R.id.tvLike);
            holder.tvComment = (TextView) convertView.findViewById(R.id.tvComment);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tvTime);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ivAvatar.setImageResource(rowItem.getIdImage());
        holder.tvTitle.setText(rowItem.getTitle());
        holder.tvName.setText(rowItem.getPerson());
        holder.tvLike.setText("Like: " + Integer.toString(rowItem.getLike()));
        holder.tvComment.setText("Comment: " + Integer.toString(rowItem.getComment()));
        holder.tvTime.setText(rowItem.getTime());
        return convertView;
    }
}
