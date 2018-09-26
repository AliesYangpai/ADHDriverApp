package com.adhdriver.work.ui.adapter.forlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.adhdriver.work.R;
import com.adhdriver.work.constant.ConstSign;
import com.adhdriver.work.entity.driver.study.Learning;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 * 类描述
 * 版本
 */

public class LearnAdapter extends BaseAdapter {


    private List<Learning> list;

    private Context context ;

    private LayoutInflater inflater;


    public LearnAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(this.context);
    }


    public List<Learning> getList() {
        return list;
    }

    public void setList(List<Learning> list) {
        if(null == list) {

            list = new ArrayList<>();

        }
        this.list = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(list != null && list.size() > 0) {

            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list != null && list.size() > 0) {

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
        Learning learning = list.get(position);


        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_learn_list, null);
            vh.tv_learn_topic = (TextView) convertView.findViewById(R.id.tv_learn_topic);
            vh.tv_learn_answer = (TextView) convertView.findViewById(R.id.tv_learn_answer);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }



        vh.tv_learn_topic.setText(position+1+ ConstSign.COMMA+learning.getDaturn_topic());
        vh.tv_learn_answer.setText(learning.getAnswer_content());

        return convertView;
    }

    class ViewHolder {


        TextView tv_learn_topic;
        TextView tv_learn_answer;
    }

}
