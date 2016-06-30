package com.hjh.nooneknow.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjh.nooneknow.R;
import com.hjh.nooneknow.net.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HJH on 2016/6/30.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private Context mContext;
    private List<Message> mMessages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.mContext = context;
        this.mMessages = messages;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_message_timeline, parent, false);
        return new MessageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.mTvMessage.setText(mMessages.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class MessageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvMessage)
        TextView mTvMessage;
        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }


}
