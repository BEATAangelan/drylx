package ningjiaxin1.bwie.com.lala.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import ningjiaxin1.bwie.com.lala.R;
import ningjiaxin1.bwie.com.lala.ShowActivity;
import ningjiaxin1.bwie.com.lala.bean.Goods;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Goods.DataBean> list;

    public RecyclerAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    public void setList(List<Goods.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LinearLayout.inflate(context, R.layout.item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdapter.ViewHolder viewHolder, final int i) {
       viewHolder.txt_price.setText(list.get(i).getPrice()+"");
       viewHolder.txt_title.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getDetailUrl()).into(viewHolder.image_tu);
        viewHolder.button_gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowActivity.class);
                context.startActivity(intent);
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monClickListener!=null){
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(viewHolder.itemView, "alpha", 1, 0);
                    objectAnimator.setDuration(1000);
                    objectAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            list.remove(i);
                            notifyDataSetChanged();
                            viewHolder.itemView.setAlpha(1);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    objectAnimator.start();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_tu;
        TextView txt_title,txt_price;
        Button button_gou;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_tu = itemView.findViewById(R.id.image_tu);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_price = itemView.findViewById(R.id.txt_price);
            button_gou = itemView.findViewById(R.id.button_gou);
        }
    }
    //接口回调
    private OnItemClickLinstener monClickListener;
    public interface OnItemClickLinstener{
        void OnClickLinstener(View item, int position);
    }
    public void setOnClickListener(OnItemClickLinstener onClickListener ){
        monClickListener=onClickListener;
    }
}
