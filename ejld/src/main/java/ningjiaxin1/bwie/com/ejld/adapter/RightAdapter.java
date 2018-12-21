package ningjiaxin1.bwie.com.ejld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.ejld.R;
import ningjiaxin1.bwie.com.ejld.bean.Left;
import ningjiaxin1.bwie.com.ejld.bean.Right;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<Right.DataBean> list;

    public RightAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Right.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rightlayout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textView.setText(list.get(i).getName());
        GoodsAdpter goodsAdpter = new GoodsAdpter(context);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        viewHolder.recyclerView.setLayoutManager(gridLayoutManager);
        viewHolder.recyclerView.setAdapter(goodsAdpter);
        viewHolder.recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL));
        goodsAdpter.setList(list.get(i).getList());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rigthtitle);
            recyclerView = itemView.findViewById(R.id.goodsrecycle);
        }
    }
}
