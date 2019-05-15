package zzw.imtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.widget.Toast
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.android.api.model.Conversation
import cn.jpush.im.api.BasicCallback
import kotlinx.android.synthetic.main.activity_chat.*
import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng.EventEntity
import cn.jpush.im.android.api.content.TextContent
import cn.jpush.im.android.api.enums.MessageDirect
import cn.jpush.im.android.api.event.MessageEvent


open class ChatActivity : AppCompatActivity() {

    var adapter:ChatAdapter?=null

    var data:MutableList<ChatItem> = mutableListOf()



    var conversation:Conversation?=null

    var account_target=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        JMessageClient.registerEventReceiver(this)

       account_target = intent.getStringExtra("data")
        supportActionBar?.hide()

/*        data.add(ChatItem(ChatItem.SEND,"zz"))
        data.add(ChatItem(ChatItem.RECEIVE,"你才是"))*/


        btn_send.setOnClickListener {
            if(TextUtils.isEmpty(et_send.text.toString())){
                Toast.makeText(this@ChatActivity,"请输入发送内容", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
       //    var message = JMessageClient.createSingleTextMessage(account_target,App.APP_KEY,et_send.text.toString())

            var content=et_send.text.toString()
            var textContent=TextContent(content)
            var m=conversation?.createSendMessage(textContent)
            m?.setOnSendCompleteCallback(object :BasicCallback(){
                override fun gotResult(p0: Int, p1: String?) {

                    data.add(ChatItem(ChatItem.SEND, content))
                    adapter?.notifyDataSetChanged()

                    recyc.scrollToPosition(data.size-1)
                }

            })
            JMessageClient.sendMessage(m)

            et_send.setText("")

        }

        conversation=JMessageClient.getSingleConversation(account_target)
        if(conversation==null){
            conversation=Conversation.createSingleConversation(account_target)
        }

        if(conversation?.allMessage!=null){
            for( bean in  conversation?.allMessage!!.toMutableList()){
                if(bean.direct==MessageDirect.send){
                    data.add(ChatItem(ChatItem.SEND, (bean.getContent() as TextContent).text))
                }else{
                    data.add(ChatItem(ChatItem.RECEIVE,(bean.getContent() as TextContent).text))
                }

            }
        }


        adapter= ChatAdapter(data)

        recyc.layoutManager=LinearLayoutManager(this)
        recyc.adapter=adapter


        recyc.scrollToPosition(data.size-1)
    }


    override fun onResume() {
        super.onResume()
        JMessageClient.enterSingleConversation(account_target)
    }

    override fun onPause() {
        super.onPause()
        JMessageClient.exitConversation()
    }

    public fun onEvent(event: MessageEvent) {

        runOnUiThread(
            object :Runnable{
                override fun run() {
                    if(event.message.direct==MessageDirect.send){
                        data.add(ChatItem(ChatItem.SEND, (event.message.getContent() as TextContent).text))
                    }else{
                        data.add(ChatItem(ChatItem.RECEIVE,(event.message.getContent() as TextContent).text))
                    }
                    adapter?.notifyDataSetChanged()

                    recyc.scrollToPosition(data.size-1)
                }

            }
        )


    }
/*

    public fun onEventMainThread(event:MessageEvent){
        if(event.message.direct==MessageDirect.send){
            data.add(ChatItem(ChatItem.SEND, (event.message.getContent() as TextContent).text))
        }else{
            data.add(ChatItem(ChatItem.RECEIVE,(event.message.getContent() as TextContent).text))
        }
        adapter?.notifyDataSetChanged()

        recyc.scrollToPosition(data.size-1)
    }


*/



    override fun onDestroy() {
        super.onDestroy()
        JMessageClient.unRegisterEventReceiver(this)
    }

}
