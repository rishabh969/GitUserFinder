package net.github.finduser.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.github.finduser.R;
import net.github.finduser.models.SearchResponse;
import net.github.finduser.util.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchResponse.ItemsBean> mArrayList;
    private Context context;


    public SearchAdapter(Context context,List<SearchResponse.ItemsBean> arrayList) {
        this.context=context;
        mArrayList = arrayList;
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder viewHolder, int i) {

        viewHolder.type.setText(""+mArrayList.get(i).getType());
        viewHolder.id.setText(""+mArrayList.get(i).getId());
        viewHolder.user.setText(""+mArrayList.get(i).getLogin());
        viewHolder.score.setText(""+mArrayList.get(i).getScore());
        try{
            Glide.with(context)
                    .load(mArrayList.get(i).getAvatar_url())
                    .into(viewHolder.iv_icon);
        }catch (Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView type,id,user,score;
        private RoundedImageView iv_icon;
        public ViewHolder(View view) {
            super(view);

            type = (TextView)view.findViewById(R.id.type);
            id = (TextView)view.findViewById(R.id.id);
            user = (TextView)view.findViewById(R.id.user);
            score = (TextView)view.findViewById(R.id.score);
            iv_icon=(RoundedImageView) view.findViewById(R.id.iv_icon);
        }
    }

}
