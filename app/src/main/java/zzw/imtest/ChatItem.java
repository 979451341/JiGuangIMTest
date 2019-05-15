package zzw.imtest;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ChatItem implements MultiItemEntity {

    public static final int SEND = 1;
    public static final int RECEIVE = 2;
    private int itemType;
    private String content;

    public ChatItem(int itemType,String content) {
        this.itemType = itemType;
        this.content=content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
