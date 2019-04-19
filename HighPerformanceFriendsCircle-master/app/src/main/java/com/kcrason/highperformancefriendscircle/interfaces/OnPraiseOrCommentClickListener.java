package com.kcrason.highperformancefriendscircle.interfaces;

/**
 * 评论弹框的监听
 */
public interface OnPraiseOrCommentClickListener {
    /**
     * 赞
     *
     * @param position
     */
    void onPraiseClick(int position);

    /**
     * 评论
     *
     * @param position
     */
    void onCommentClick(int position);
}
