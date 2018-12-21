package ningjiaxin1.bwie.com.ejld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.ejld.R;
import ningjiaxin1.bwie.com.ejld.bean.Right;

public class GoodsAdpter extends RecyclerView.Adapter<GoodsAdpter.ViewHolder> {
    private List<Right.DataBean.ListBean> list;
    private Context context;

    public GoodsAdpter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Right.DataBean.ListBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.goods,null);
        GoodsAdpter.ViewHolder holder = new GoodsAdpter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsAdpter.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getIcon()).into(viewHolder.imageView);
        viewHolder.textView.setText(list.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.goodstitle);
            imageView = itemView.findViewById(R.id.goodsimage);
        }
    }
}
