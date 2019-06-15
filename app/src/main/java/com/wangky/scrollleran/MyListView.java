package com.wangky.scrollleran;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MyListView extends ListView implements AbsListView.OnScrollListener{


    private View mHeaderView;


    private View mFooter;


    private int headerHeight;


    private int footerHeight;


    private int downY;


    private  int mScrollState;


    private int mFirstVisibleItem;

    private int mTotalItemCount;

    private boolean mLastItemVisible;//listView 最后一项可见


    private int mCurrentState = 0; //记录当前下拉是哪种状态


    private final int  PULL_REFRESH = 0;//正在下拉状态


    private final int RELEASE_RREFSH = 1;//松开进行刷新

    private final int REFRESHING = 2; //正在刷新状态


    private OnRefreshListener mOnRefreshListener;//外部activity 实现这个接口，然后刷新数据


    private RotateAnimation upAnimation,downAnimation;
    
    
    private ImageView arrow;//header 头部的箭头
    
    
    private TextView textView;


    private boolean loading =false;//上拉加载数据



    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.initView();
    }



    public  void initView(){

        mHeaderView =  LayoutInflater.from(getContext()).inflate(R.layout.list_header,null);

        arrow = mHeaderView.findViewById(R.id.img_arrow);

        textView = mHeaderView.findViewById(R.id.header_tip);

        mHeaderView.measure(0,0);
        headerHeight = mHeaderView.getMeasuredHeight();


        setHeaderTopPadding(-headerHeight);//设置mHeaderView 初始不可见

        addHeaderView(mHeaderView);//给listview 加一个header



        mFooter = LayoutInflater.from(getContext()).inflate(R.layout.list_footer,null);

        mFooter.measure(0,0);

        footerHeight = mFooter.getMeasuredHeight();

        mFooter.setPadding(0,-footerHeight,0,0);


        addFooterView(mFooter);

        setOnScrollListener(this);


        initRotateAnimation();
    }



    public void setHeaderTopPadding(int topPadding){
        mHeaderView.setPadding(mHeaderView.getPaddingLeft(),topPadding,
                mHeaderView.getPaddingRight(),mHeaderView.getPaddingBottom());

        mHeaderView.invalidate();
    }



    public void initRotateAnimation(){

        upAnimation = new RotateAnimation(0,-180,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);

        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);

        downAnimation = new RotateAnimation(-180,-360,
                RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f );
        downAnimation.setDuration(300);
        downAnimation.setFillAfter(true);

    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        this.mScrollState = scrollState;

        if(mLastItemVisible && !loading){

            loading = true;

            mFooter.setPadding(0,0,0,0);

            mOnRefreshListener.onLoadingMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        this.mFirstVisibleItem = firstVisibleItem;
        this.mTotalItemCount = totalItemCount;

        this.mLastItemVisible = firstVisibleItem+visibleItemCount == totalItemCount;//是否到达listView 底部

    }





//    @Override
//    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                downY = (int) ev.getY();

                break;


            case MotionEvent.ACTION_MOVE:

                if(mCurrentState == REFRESHING){
                    //正在刷新状态，不做处理
                    break;
                }


                int offset =  (int)(ev.getY())-downY;//手指滑动的距离
                Log.i("MyListView","offset-"+offset);

                //往下拉offset 是正值，越来越大，往上滑 offset 是负值，越来越小
                if(mFirstVisibleItem == 0){//顶部第一个可见

                    setHeaderTopPadding(offset-headerHeight);//让header 一点点显示出来

                    int tempHeight = headerHeight- offset;
                    Log.i("MyListView","tempHeight-"+tempHeight);

                    if(tempHeight <= 0 && mCurrentState == PULL_REFRESH){

                        mCurrentState = RELEASE_RREFSH;
                        refreshHeaderView();

                    }else if(tempHeight > 0 && mCurrentState ==RELEASE_RREFSH ){ //下拉超过headerView后，再往上滑 恢复原来状态

                        mCurrentState = PULL_REFRESH;

                        refreshHeaderView();


                    }

                }

                if(mLastItemVisible){//到达底部
                    Log.i("MyListView","listView 到达底部");


                }


                break;


            case MotionEvent.ACTION_UP:

                    if(mCurrentState == PULL_REFRESH){
                        //下拉，未超过headerView 高度，还原到初始状态不做处理
                        setHeaderTopPadding(-headerHeight);
                    }else if(mCurrentState == RELEASE_RREFSH){//下拉到一定高度，超过headerView 高度，可以进行刷新操作

                        mCurrentState = REFRESHING;//当前正在刷新

                        refreshHeaderView();

                        //请求数据
                        mOnRefreshListener.onPullRefresh();
                    }

                break;


        }


        return super.onTouchEvent(ev);
    }





    //判断滑动状态，判断是否进行刷新
    public void refreshHeaderView(){
        //设置header里的提示，动画之类
        switch (mCurrentState){

            case  PULL_REFRESH:
                //下拉滑动的距离还没有超过header 本身的高度
                textView.setText("下拉可以刷新");
                arrow.setVisibility(VISIBLE);
                mHeaderView.findViewById(R.id.progress).setVisibility(GONE);

                arrow.startAnimation(downAnimation);

                break;

            case RELEASE_RREFSH:

                //下拉滑动的距离已超过header 本身高度，提示文字改为""
                textView.setText("松开可以刷新");
                arrow.setVisibility(VISIBLE);
                mHeaderView.findViewById(R.id.progress).setVisibility(GONE);

                arrow.startAnimation(upAnimation);

                break;

            case REFRESHING:
                //下拉滑动的距离已超过header 本身高度，提示文字改为""
                textView.setText("正在刷新。。。");
                arrow.clearAnimation();
                arrow.setVisibility(GONE);
                mHeaderView.findViewById(R.id.progress).setVisibility(VISIBLE);
              break;

        }


    }


    //刷新完成后操作
    public void refreshComplete(){

        Log.i("MyListView","refreshComplete");
//
        textView.setText("下拉可以刷新");
        mHeaderView.findViewById(R.id.progress).setVisibility(GONE);

        arrow.setVisibility(VISIBLE);

        mCurrentState = PULL_REFRESH;

        setHeaderTopPadding(-headerHeight);

    }



    public void loadComplete(){

        loading =false;

        mFooter.setPadding(0,-footerHeight,0,0);

    }



    public void setOnRefreshListener(OnRefreshListener listener){
        this.mOnRefreshListener = listener;
    }


    public interface OnRefreshListener{
        void onPullRefresh();
        void onLoadingMore();
    }



}
