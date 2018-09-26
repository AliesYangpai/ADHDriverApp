package com.adhdriver.work.ui.adapter.forlistview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstHz;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.constant.ConstTag;
import com.adhdriver.work.entity.driver.orders.AdditionService;
import com.adhdriver.work.test.TestPassPint;
import com.adhdriver.work.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 * 类描述
 * 版本
 */

public class ChildPassPointAdapter extends BaseAdapter {


    private List<TestPassPint> list;

    private Context context;

    private LayoutInflater inflater;


    public ChildPassPointAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }


    public List<TestPassPint> getList() {
        return list;
    }

    public void setList(List<TestPassPint> list) {
        if (null == list) {

            list = new ArrayList<>();

        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (list != null && list.size() > 0) {

            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TestPassPint testPassPint = list.get(position);

        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_child_pass_point, null);
            vh.iv_spot = (ImageView) convertView.findViewById(R.id.iv_spot);
            vh.tv_pass_spot_name = (TextView) convertView.findViewById(R.id.tv_pass_spot_name);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        vh.tv_pass_spot_name.setText(testPassPint.getPintName());




        return convertView;
    }





    class ViewHolder {
        ImageView iv_spot;//
        TextView tv_pass_spot_name;//途经点名称
    }


}
