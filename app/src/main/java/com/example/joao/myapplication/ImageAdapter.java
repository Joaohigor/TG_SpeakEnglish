package com.example.joao.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;


public class ImageAdapter extends PagerAdapter {
    ArrayList<Image> drawables = new ArrayList<>();
    private Context context;

    public ImageAdapter(Context context, ArrayList<Image> imgs) {
        super();
        this.drawables = imgs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return drawables.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        ll.setLayoutParams(lp);
        container.addView(ll);
        ImageView iv = new ImageView(context);
        iv.setImageResource(drawables.get(position).id);
        ll.addView(iv);
        return (ll);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) ((View) view).getParent());
    }


}