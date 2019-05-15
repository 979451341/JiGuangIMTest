package zzw.imtest;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatItem, BaseViewHolder> {

    public ChatAdapter(@Nullable List<ChatItem> data) {
        super( data);
        addItemType(ChatItem.SEND,R.layout.item_chat_send);
        addItemType(ChatItem.RECEIVE,R.layout.item_chat_receive);
    }

    @Override
    protected void convert(BaseViewHolder helper,ChatItem item) {

        switch (helper.getItemViewType()){
            case ChatItem.SEND:
                if(TextUtils.isEmpty(item.getContent())){
                    helper.setText(R.id.tv_content," ");
                }else{
                    helper.setText(R.id.tv_content,item.getContent());

                }
                break;

            case ChatItem.RECEIVE:

                if(TextUtils.isEmpty(item.getContent())){
                    helper.setText(R.id.tv_content," ");
                }else{
                    helper.setText(R.id.tv_content,item.getContent());

                }
                break;
        }


    }
}
