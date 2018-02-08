package offery.wizzo.in.offery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import offery.wizzo.in.offery.R;
import offery.wizzo.in.offery.fragment.StareedFragmnet;
import offery.wizzo.in.offery.webApi.StarredList;

/**
 * Created by WinSnit on 01-Sep-17.
 */


public class StarredListAdapter extends RecyclerView.Adapter<StarredListAdapter.ViewHolder> implements View.OnClickListener {
    Context context;
    ArrayList<StarredList> items;

    public StarredListAdapter(Context context, ArrayList<StarredList> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_starred_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_news.setText(items.get(position).news);
        holder.tv_newsType.setText(items.get(position).news_type);
        holder.tv_newsStatus.setText(items.get(position).news_status);
        holder.tv_newsDate.setText(items.get(position).news_date);
        holder.tv_newsTime.setText(items.get(position).news_time);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {
        int itemposition = StareedFragmnet.rv_list.getChildLayoutPosition(v);
            /*context.startActivity(new Intent(context, CampaignDetailActivity.class)
                    .putExtra("campaign", items.get(itemposition)));*/
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_news, tv_newsType, tv_newsDate, tv_newsTime, tv_newsStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_news = (TextView) itemView.findViewById(R.id.tv_news);
            this.tv_newsType = (TextView) itemView.findViewById(R.id.tv_newsType);
            this.tv_newsDate = (TextView) itemView.findViewById(R.id.tv_newsDate);
            this.tv_newsTime = (TextView) itemView.findViewById(R.id.tv_newsTime);
            this.tv_newsStatus = (TextView) itemView.findViewById(R.id.tv_newsStatus);
        }
    }
}
