package offery.wizzo.in.offery.adapter;

/**
 * Created by ist on 10/9/17.
 */

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.model.ClassModel;
import offery.wizzo.in.offery.model.DataBean;

public class ClassListAdapter extends BaseExpandableListAdapter {
    private Activity mcontext;
    private Map<String, List<ClassModel>> laptopCollections;
    private List<DataBean> laptops;

    public ClassListAdapter(Activity context, List<DataBean> laptops,
                            Map<String, List<ClassModel>> laptopCollections) {
        this.mcontext = context;
        this.laptopCollections = laptopCollections;
        this.laptops = laptops;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return laptopCollections.get(laptops.get(groupPosition).getName()).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final ClassModel laptop = (ClassModel) getChild(groupPosition, childPosition);
        LayoutInflater inflater = mcontext.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.raw_child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.txtItemChildId);

        final ImageView delete = (ImageView) convertView.findViewById(R.id.imgDeleteChildId);
        if (laptop.isChecked()) {
            delete.setBackgroundResource(R.drawable.ic_checked_circle);
            Log.d("ic_checked", "ch");
        } else {
            delete.setBackgroundResource(R.drawable.ic_normal_circle);
            Log.d("ic_checked", "unch");
        }


        delete.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                ClassModel child =
                        laptopCollections.get(laptops.get(groupPosition).getName()).get(childPosition);
                if (child.isChecked()) {
                    laptopCollections.get(laptops.get(groupPosition).getName()).get(childPosition).setChecked(false);
                } else {
                    laptopCollections.get(laptops.get(groupPosition).getName()).get(childPosition).setChecked(true);
                }

                notifyDataSetChanged();
            }
        });

        item.setText(laptop.getClass_name());
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return laptopCollections.get(laptops.get(groupPosition).getName()).size();
    }

    public Object getGroup(int groupPosition) {
        return laptops.get(groupPosition);
    }

    public int getGroupCount() {
        return laptops.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        DataBean dataBean = (DataBean) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mcontext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.raw_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.paretn_text);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(dataBean.getName());
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}