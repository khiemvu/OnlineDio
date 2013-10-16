/*******************************************************************************
 * Copyright 2013 Gabriele Mariotti
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qsoft.Activity;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.qsoft.Model.NsMenuItemModel;
import com.qsoft.OnlineDio.R;

public class NsMenuAdapter extends ArrayAdapter<NsMenuItemModel>
{

    public NsMenuAdapter(Context context)
    {
        super(context, 0);
    }

    public void addHeader(int title)
    {
        add(new NsMenuItemModel(title, -1, true));
    }

    public void addItem(int title, int icon)
    {
        add(new NsMenuItemModel(title, icon, false));
    }

    public void addItem(NsMenuItemModel itemModel)
    {
        add(itemModel);
    }

    @Override
    public int getViewTypeCount()
    {
        return 2;
    }

    @Override
    public int getItemViewType(int position)
    {
        return getItem(position).isHeader ? 0 : 1;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return !getItem(position).isHeader;
    }

    public static class ViewHolder
    {
        public final TextView textHolder;
        public final ImageView imageHolder;
        public final Button buttonHolder;

        public ViewHolder(TextView text1, ImageView image1, Button button1)
        {
            this.textHolder = text1;
            this.imageHolder = image1;
            buttonHolder = button1;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        NsMenuItemModel item = getItem(position);
        ViewHolder holder = null;
        View view = convertView;

        if (view == null)
        {
            int layout = R.layout.menu_row_layout;
            if (item.isHeader)
            {
                layout = R.layout.menu_row_header_layout;
            }

            view = LayoutInflater.from(getContext()).inflate(layout, null);

            TextView text1 = (TextView) view.findViewById(R.id.sideBar_menu_tvTitle);
            ImageView image1 = (ImageView) view.findViewById(R.id.sideBar_menu_ivIcon);
            Button btNote = (Button) view.findViewById(R.id.sideBar_menu_btSupport);
            view.setTag(new ViewHolder(text1, image1, btNote));
        }

        if (holder == null && view != null)
        {
            Object tag = view.getTag();
            if (tag instanceof ViewHolder)
            {
                holder = (ViewHolder) tag;
            }
        }


        if (item != null && holder != null)
        {
            if (holder.textHolder != null)
            {
                holder.textHolder.setText(item.title);
            }

            if (holder.buttonHolder != null)
            {
                if (item.isButton == true)
                {
                    holder.buttonHolder.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.buttonHolder.setVisibility(View.GONE);
                }
            }

            if (holder.imageHolder != null)
            {
                if (item.iconRes > 0)
                {
                    holder.imageHolder.setVisibility(View.VISIBLE);
                    holder.imageHolder.setImageResource(item.iconRes);
                }
                else
                {
                    holder.imageHolder.setVisibility(View.GONE);
                }
            }
        }

        return view;
    }

}
