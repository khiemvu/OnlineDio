package com.qsoft.OnlineDio.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import com.qsoft.OnlineDio.Model.CommentModel;
import com.qsoft.OnlineDio.R;

import java.util.ArrayList;
import java.util.List;

/**
 * User: khiemvx
 * Date: 10/17/13
 */
public class CommentFragmentActivity extends Fragment
{
    public static final String[] names = new String[]{
            "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx", "Khiemvx"};
    public static final String[] comments = new String[]{"Test comment", "Test comment",
            "Test comment", "Test comment", "Test comment", "Test comment",};
    public static final Integer[] images = new Integer[]{R.drawable.content_comment_image, R.drawable.content_comment_image,
            R.drawable.content_comment_image, R.drawable.content_comment_image,
            R.drawable.content_comment_image, R.drawable.content_comment_image,};
    public static final String[] times = new String[]{
            "a day ago", "a day ago", "a day ago", "a day ago", "a day ago", "a day ago"};


    private EditText etComment;
    private ListView listView;
    private List<CommentModel> rowItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        View viewer = (View) inflater.inflate(R.layout.program_comment_layout, container, false);

        initComponent(viewer);

        etComment.setOnClickListener(onclickListener);

        //add data into list
        rowItems = new ArrayList<CommentModel>();
        for (int i = 0; i < names.length; i++)
        {
            CommentModel item = new CommentModel(images[i], names[i], comments[i], times[i]);
            rowItems.add(item);
        }
        ListCommentArrayAdapter adapter = new ListCommentArrayAdapter(getActivity(),
                R.layout.program_comment_item_layout, rowItems);
        listView.setAdapter(adapter);

        return viewer;
    }

    private void initComponent(View viewer)
    {
        listView = (ListView) viewer.findViewById(R.id.comment_lvCommentList);
        etComment = (EditText) viewer.findViewById(R.id.comment_etComment);
    }

    private final View.OnClickListener onclickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.comment_etComment:
                    Intent intent = new Intent(getActivity().getBaseContext(), AddCommentFragmentActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
