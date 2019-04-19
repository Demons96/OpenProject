package com.kcrason.highperformancefriendscircle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kcrason.highperformancefriendscircle.adapters.FriendCircleAdapter;
import com.kcrason.highperformancefriendscircle.beans.FriendCircleBean;
import com.kcrason.highperformancefriendscircle.interfaces.OnPraiseOrCommentClickListener;
import com.kcrason.highperformancefriendscircle.others.DataCenter;
import com.kcrason.highperformancefriendscircle.others.FriendsCircleAdapterDivideLine;
import com.kcrason.highperformancefriendscircle.others.GlideSimpleTarget;
import com.kcrason.highperformancefriendscircle.ui.activitys.EmojiPanelActivity;
import com.kcrason.highperformancefriendscircle.utils.Utils;
import com.kcrason.highperformancefriendscircle.widgets.EmojiPanelView;

import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener,
        OnPraiseOrCommentClickListener,             // 评论弹框的监听
        ImageWatcher.OnPictureLongPressListener,
        ImageWatcher.Loader {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Disposable mDisposable;
    private FriendCircleAdapter mFriendCircleAdapter;
    private ImageWatcher mImageWatcher;
    private EmojiPanelView mEmojiPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmojiPanelView = findViewById(R.id.emoji_panel_view);
        mEmojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        mSwipeRefreshLayout = findViewById(R.id.swpie_refresh_layout);
        mImageWatcher = findViewById(R.id.image_watcher);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        findViewById(R.id.img_back).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, EmojiPanelActivity.class)));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("TAG", "onScrollStateChanged: newState" + newState);
                //静止,没有滚动
//                public static final int SCROLL_STATE_IDLE = 0;
                //正在被外部拖拽,一般为用户正在用手指滚动
//                public static final int SCROLL_STATE_DRAGGING = 1;
                //自动滚动开始
//                public static final int SCROLL_STATE_SETTLING = 2;
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(MainActivity.this).resumeRequests();
                } else {
                    // 滑动的时候暂停请求
                    Glide.with(MainActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new FriendsCircleAdapterDivideLine());
        recyclerView.setAdapter(mFriendCircleAdapter = new FriendCircleAdapter(this, recyclerView, mImageWatcher));
        mImageWatcher.setTranslucentStatus(Utils.calcStatusBarHeight(this));
        mImageWatcher.setErrorImageRes(R.mipmap.error_picture);
        mImageWatcher.setOnPictureLongPressListener(this);
        mImageWatcher.setLoader(this);
        Utils.showSwipeRefreshLayout(mSwipeRefreshLayout, this::asyncMakeData);
    }


    private void asyncMakeData() {
        mDisposable = Single.create((SingleOnSubscribe<List<FriendCircleBean>>) emitter ->
                emitter.onSuccess(DataCenter.makeFriendCircleBeans(this)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((friendCircleBeans, throwable) -> {
                    Utils.hideSwipeRefreshLayout(mSwipeRefreshLayout);
                    if (friendCircleBeans != null && throwable == null) {
                        mFriendCircleAdapter.setFriendCircleBeans(friendCircleBeans);
                    }
                });

//        mDisposable = Single.create(new SingleOnSubscribe<List<FriendCircleBean>>() {
//            @Override
//            public void subscribe(SingleEmitter<List<FriendCircleBean>> emitter) throws Exception {
//                emitter.onSuccess(DataCenter.makeFriendCircleBeans(MainActivity.this));
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BiConsumer<List<FriendCircleBean>, Throwable>() {
//                    @Override
//                    public void accept(List<FriendCircleBean> friendCircleBeans, Throwable throwable) throws Exception {
//                        Utils.hideSwipeRefreshLayout(mSwipeRefreshLayout);
//                        if (friendCircleBeans != null && throwable == null) {
//                            mFriendCircleAdapter.setFriendCircleBeans(friendCircleBeans);
//                        }
//                    }
//                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onRefresh() {
        asyncMakeData();
    }

    @Override
    public void onPraiseClick(int position) {
        Toast.makeText(this, "You Click Praise!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentClick(int position) {
//        Toast.makeText(this, "you click comment", Toast.LENGTH_SHORT).show();
        mEmojiPanelView.showEmojiPanel();
    }

    @Override
    public void onBackPressed() {
        if (!mImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }


    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }
}
