package com.example.android.itwasnews;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.itwasnews.databinding.FooterItemBinding;
import com.example.android.itwasnews.databinding.ListItemBinding;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<News> newsFeed;
    private Context context;

    private static String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Context context, List<News> newsFeed) {
        this.newsFeed = newsFeed;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (viewType == TYPE_ITEM) {
            ListItemBinding listItemBinding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
            NewsHolder newsHolder = new NewsHolder(listItemBinding.getRoot(), listItemBinding);
            return newsHolder;
        } else if (viewType == TYPE_FOOTER) {
            FooterItemBinding footerItemBinding = DataBindingUtil.inflate(inflater, R.layout.footer_item, parent, false);
            FooterViewHolder footerHolder = new FooterViewHolder(footerItemBinding.getRoot(), footerItemBinding);
            return footerHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NewsHolder) {
            NewsHolder newsHolder = (NewsHolder) holder;
            News news = newsFeed.get(position);
            newsHolder.getBinding().setVariable(com.example.android.itwasnews.BR.news, news);
            newsHolder.getBinding().executePendingBindings();
        } else if (holder instanceof FooterViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return newsFeed.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public List<News> swapNewsFeed(List<News> newsFeed) {
        if (this.newsFeed == newsFeed) {
            return null;
        }
        List<News> oldNewsFeed = this.newsFeed;
        this.newsFeed = newsFeed;
        if (newsFeed != null) {
            this.notifyDataSetChanged();
        }
        return oldNewsFeed;
    }

    private boolean isPositionFooter(int position) {
        return position == newsFeed.size() - 1;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        private ListItemBinding listItemBinding;
        private final Context context;

        public NewsHolder(View itemView, final ListItemBinding binding) {
            super(itemView);
            listItemBinding = binding;
            context = itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, StoryWebView.class);
                    i.putExtra("url", listItemBinding.getNews().getUrl());
                    i.putExtra("title", listItemBinding.getNews().getTitle());
                    context.startActivity(i);
                }
            });
        }

        public ListItemBinding getBinding() {
            return listItemBinding;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private FooterItemBinding footerItemBinding;

        public FooterViewHolder(View itemView, FooterItemBinding binding) {
            super(itemView);
            footerItemBinding = binding;
        }

        public FooterItemBinding getBinding() {
            return footerItemBinding;
        }
    }
}


