package com.yunlong.samples.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by shiyunlong on 16/9/13.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    /**
     * 数据
     */
    protected List<T> mData;
    /**
     * 上下文
     */
    protected Context mContext;

    public BaseAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    /**
     * 创建CreateViewHolder
     *
     * @param parent:子view的父view，每一个item对应的View
     * @param viewType:子view的类型
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, getResourceId(viewType));
    }

    /**
     * 绑定View和data
     *
     * @param holder:viewHolder
     * @param position:位置
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        bindView(holder.itemView, position);
        final OnItemClickListener onItemClickListener = getOnItemClickListener();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(v, position);
            }
        });
    }

    /**
     * 得到Item信息
     *
     * @param position:位置
     * @return
     */
    public T getItem(int position) {
        return mData != null && position >= 0 && position < mData.size() ? mData.get(position) : null;
    }

    /**
     * 获取Item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(LayoutInflater inflater, ViewGroup parent, int resourceId) {
            super(inflater.inflate(resourceId, parent, false));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public abstract OnItemClickListener getOnItemClickListener();

    public abstract int getResourceId(int viewType);

    public abstract void bindView(View itemView, int position);

}
