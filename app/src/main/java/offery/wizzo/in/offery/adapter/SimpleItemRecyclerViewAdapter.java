package offery.wizzo.in.offery.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import offery.wizzo.in.offery.R;

import java.util.ArrayList;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<offery.wizzo.in.offery.adapter.SimpleItemRecyclerViewAdapter.ViewHolder> implements Filterable {
    private ArrayList<offery.wizzo.in.offery.model.SchoolBean> mArrayList;
    private ArrayList<offery.wizzo.in.offery.model.SchoolBean> mFilteredList;
    Context context;
    onClickEvent onCLickEvent;
    String flagExist;

    public SimpleItemRecyclerViewAdapter(Context context, ArrayList<offery.wizzo.in.offery.model.SchoolBean> arrayList, onClickEvent onCLickEvent, String flagExist) {
        mArrayList = arrayList;
        this.onCLickEvent = onCLickEvent;
        this.context = context;
        mFilteredList = arrayList;
        this.flagExist=flagExist;
    }

    @Override
    public offery.wizzo.in.offery.adapter.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_item_list_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(offery.wizzo.in.offery.adapter.SimpleItemRecyclerViewAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_name.setText(mFilteredList.get(i).getSchoolName());
        viewHolder.school_address.setText(mFilteredList.get(i).getAddress());

        if (mFilteredList.get(i).isChecked()) {
            viewHolder.checkUncheckedBox.setBackgroundResource(R.drawable.ic_checked_circle);
        } else {
            viewHolder.checkUncheckedBox.setBackgroundResource(R.drawable.ic_normal_circle);
        }

        viewHolder.checkUncheckedBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCLickEvent.onCLickEvent(v, i);
            }
        });
    }

    public ArrayList<offery.wizzo.in.offery.model.SchoolBean> getmFilteredList() {
        return mFilteredList;
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;
                } else {
                    ArrayList<offery.wizzo.in.offery.model.SchoolBean> filteredList = new ArrayList<>();
                    for (offery.wizzo.in.offery.model.SchoolBean androidVersion : mArrayList) {
                        if (androidVersion.getSchoolName().toLowerCase().contains(charString)) {
                            filteredList.add(androidVersion);
                        }
                    }
                    mFilteredList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<offery.wizzo.in.offery.model.SchoolBean>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, school_address, tv_api_level;
        ImageView checkUncheckedBox;

        public ViewHolder(View view) {
            super(view);
            checkUncheckedBox = (ImageView) view.findViewById(R.id.checkUncheckedBox);
            tv_name = (TextView) view.findViewById(R.id.txtContentId);
            school_address=(TextView)view.findViewById(R.id.txtSchoolAddressContentListId);

        }
    }


    public interface onClickEvent {

        public void onCLickEvent(View view, int position);
    }
}

