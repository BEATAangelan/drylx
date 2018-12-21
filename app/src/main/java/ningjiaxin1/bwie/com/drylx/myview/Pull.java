package ningjiaxin1.bwie.com.drylx.myview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class Pull extends LinearLayout {
    public Pull(Context context) {
        super(context);
    }

    public Pull(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
   //定义最大孩子
    private int MaxChilden;
    private int mLeft=20;
    private int mTop=20;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //找到最大孩子
        findMaxChilden();

        int left=0;
        int top=0;
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if(left!=0){
                if((left+view.getMeasuredWidth())>sizeWidth){
                    top+=MaxChilden+mTop;
                    left=0;
                }
            }
            left+=view.getMeasuredWidth()+mLeft;
        }
        setMeasuredDimension(sizeWidth,(top+MaxChilden)>sizeHeight?sizeHeight:top+MaxChilden);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        findMaxChilden();

        int left = 0;
        int top = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (left != 0) {
                if ((left + view.getMeasuredWidth()) > getWidth()) {
                    top += MaxChilden + mTop;
                    left = 0;
                }
            }
            view.layout(left,top,left+view.getMeasuredWidth(),top+MaxChilden);
             left+=mLeft+view.getMeasuredWidth();
        }

    }

    private void findMaxChilden() {
        MaxChilden=0;
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View view = getChildAt(i);
            if(view.getMeasuredHeight()>MaxChilden){
                MaxChilden=view.getMeasuredHeight();
            }
        }
    }
}
