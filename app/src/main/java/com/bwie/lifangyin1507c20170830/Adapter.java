package com.bwie.lifangyin1507c20170830;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 木子 on 2017/8/30.
 */

public class Adapter extends BaseAdapter {

    private Context context;
    private List<NewsBean> list = new ArrayList<>();
    private final int one = 0;
    private final int two = 1;
    private int num = 2;


    public Adapter(Context context, List<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return one;
        } else {
            return two;
        }
    }

    @Override
    public int getViewTypeCount() {
        return num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        ViewHolderb holderb = null;
        int type = getItemViewType(i);
        if (view == null) {
            switch (type) {
                case one:
                    holder = new ViewHolder();
                    view = View.inflate(context, R.layout.itema, null);
                    holder.iv = view.findViewById(R.id.iv);
                    holder.time = view.findViewById(R.id.time);
                    holder.title = view.findViewById(R.id.title);
                    holder.name = view.findViewById(R.id.name);
                    view.setTag(holder);

                    break;
                case two:
                    holderb = new ViewHolderb();
                    view = View.inflate(context, R.layout.itemb, null);
                    holderb.iv = view.findViewById(R.id.iv);
                    holderb.time = view.findViewById(R.id.time);
                    holderb.title = view.findViewById(R.id.title);
                    holderb.name = view.findViewById(R.id.name);
                    view.setTag(holderb);

                    break;


            }
        } else {
            switch (type) {
                case one:

                    holder = (ViewHolder) view.getTag();

                    break;
                case two:

                    holderb = (ViewHolderb) view.getTag();

                    break;


            }
        }
        switch (type) {

            case one:
                holder.name.setText(list.get(i).author_name);
                holder.title.setText(list.get(i).title);
                holder.time.setText(list.get(i).data);
                ImageLoader.getInstance().displayImage(list.get(i).img, holder.iv);
                break;
            case two:
                holderb.name.setText(list.get(i).author_name);
                holderb.title.setText(list.get(i).title);
                holderb.time.setText(list.get(i).data);
                ImageLoader.getInstance().displayImage(list.get(i).img, holderb.iv);
                break;

        }

        return view;
    }


    class ViewHolder {
        TextView name, time, title;
        ImageView iv;
    }

    class ViewHolderb {
        TextView name, time, title;
        ImageView iv;
    }
}
