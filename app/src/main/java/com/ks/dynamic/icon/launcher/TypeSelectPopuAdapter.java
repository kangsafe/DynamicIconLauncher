package com.ks.dynamic.icon.launcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 2017/9/25 0025 11:38.
 * Author: kang
 * Email: kangsafe@163.com
 */

public class TypeSelectPopuAdapter extends BaseAdapter {
    List<TypeSelect> datas;
    Context context;
    int reid;

    public TypeSelectPopuAdapter(Context context, List<TypeSelect> typeSelectlist, int item_operation_popu) {
        reid = item_operation_popu;
        this.context = context;
        datas = typeSelectlist;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TypeSelect m = datas.get(i);
        View v = LayoutInflater.from(context).inflate(reid, null);
        TextView tv = v.findViewById(R.id.name);
        tv.setText(m.getName());
//        viewGroup.addView(v);
        return v;
    }
}
