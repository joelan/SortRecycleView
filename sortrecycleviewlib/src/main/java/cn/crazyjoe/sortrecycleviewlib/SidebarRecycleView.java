package cn.crazyjoe.sortrecycleviewlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SidebarRecycleView extends RelativeLayout {

    View Container;
    Context mContext;

    RecyclerView recyclerView;
    Sidebar sidebar;

    private int mTextSize;
    private int mHintTextSize;
    private int mTextColor;
    private int mWaveColor;
    private int mTextColorChoose;
    private int mWidth;
    private int mHeight;
    private int mItemHeight;
    private int mPadding;
    // 圆形半径
    private int mCircleRadius;
    // 贝塞尔曲线的分布半径
    private int mRadius;

    IBaseAdapter  adapter;
    LinearLayoutManager  manager;

    //这是字母表条背景颜色
    private  int backgroupcolor;
    //字母表条描边颜色
    private  int bgstrokecolor;

//    public SidebarRecycleView(Context context) {
//        super(context, null);
//        mContext = context;
//        init(null);
//    }

    public SidebarRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void init(AttributeSet attrs) {
        Container = LayoutInflater.from(mContext).inflate(R.layout.sort_recycle_view, null);
        recyclerView = Container.findViewById(R.id.data_recycle_view);
        sidebar = Container.findViewById(R.id.sideBar);

        addView(Container, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));


        //------------------------------------------sibar----------------


        mTextColor = Color.parseColor("#969696");
        mWaveColor = Color.parseColor("#bef9b81b");

        backgroupcolor = Color.parseColor("#F9F9F9");
        bgstrokecolor = Color.parseColor("#969696");

        mTextColorChoose = ContextCompat.getColor(mContext, android.R.color.white);
        mTextSize = mContext.getResources().getDimensionPixelSize(R.dimen.textSize);
        mHintTextSize = mContext.getResources().getDimensionPixelSize(R.dimen.hintTextSize);
        mPadding = mContext.getResources().getDimensionPixelSize(R.dimen.padding);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.waveSideBar);
            mTextColor = a.getColor(R.styleable.waveSideBar_textColor, mTextColor);

            backgroupcolor = a.getColor(R.styleable.waveSideBar_letterbackgroundColor, mTextColor);
            bgstrokecolor = a.getColor(R.styleable.waveSideBar_letterBgStrokeColor, mTextColor);

            mTextColorChoose = a.getColor(R.styleable.waveSideBar_chooseTextColor, mTextColorChoose);
            mTextSize = a.getDimensionPixelSize(R.styleable.waveSideBar_textSize, mTextSize);
            mHintTextSize = a.getDimensionPixelSize(R.styleable.waveSideBar_hintTextSize, mHintTextSize);
            mWaveColor = a.getColor(R.styleable.waveSideBar_backgroundColor, mWaveColor);
            mRadius = a.getDimensionPixelSize(R.styleable.waveSideBar_radius, mContext.getResources().getDimensionPixelSize(R.dimen.radius));
            mCircleRadius = a.getDimensionPixelSize(R.styleable.waveSideBar_circleRadius, mContext.getResources().getDimensionPixelSize(R.dimen.circleRadius));
            a.recycle();
        }
        if(sidebar!=null)
        {
            sidebar.setmTextColor(mTextColor);
            sidebar.setmTextColorChoose(mTextColorChoose);
            sidebar.setmTextSize(mTextSize);
            sidebar.setmHintTextSize(mHintTextSize);
            sidebar.setmWaveColor(mWaveColor);
            sidebar.setmRadius(mRadius);
            sidebar.setmCircleRadius(mCircleRadius);
            //这是字母表条背景颜色
            sidebar.setBackgroupcolor(backgroupcolor);
            //字母表描边颜色
            sidebar.setBgstrokecolor(bgstrokecolor);

        }


        //------------------------------------------sibar----------------

    }

    public <T> void setAdapter(final IBaseAdapter adapter, List<SortModel<T>> list) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter.getMyAdapter());
        Collections.sort(list, new PinyinComparator());
        adapter.getMyAdapter().notifyDataSetChanged();

        manager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new TitleItemDecoration.Builder<T>(mContext,list).build());
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //设置右侧SideBar触摸监听
        sidebar.setOnTouchLetterChangeListener(new Sidebar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

    }


    public <T> void setAdapter(final IBaseAdapter adapter, List<SortModel<T>> list,TitleItemDecoration decoration) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter.getMyAdapter());

        Collections.sort(list, new PinyinComparator());
        adapter.getMyAdapter().notifyDataSetChanged();

        manager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        //设置右侧SideBar触摸监听
        sidebar.setOnTouchLetterChangeListener(new Sidebar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

    }

    public <T> void setAdapter(final IBaseAdapter adapter, List<SortModel<T>> list,TitleItemDecoration decoration,RecyclerView.ItemDecoration divilineDecoration) {
        this.adapter = adapter;
        recyclerView.setAdapter(adapter.getMyAdapter());

        Collections.sort(list, new PinyinComparator());
        adapter.getMyAdapter().notifyDataSetChanged();

        manager=new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.addItemDecoration(divilineDecoration);
        //设置右侧SideBar触摸监听
        sidebar.setOnTouchLetterChangeListener(new Sidebar.OnTouchLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(letter.charAt(0));
                if (position != -1) {
                    manager.scrollToPositionWithOffset(position, 0);
                }
            }
        });

    }



}
