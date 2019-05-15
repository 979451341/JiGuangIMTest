package zzw.imtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import cn.jpush.im.android.api.model.Conversation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener {
            if(TextUtils.isEmpty(et_account.text.toString())){
                Toast.makeText(this@MainActivity,"请输入目标账号", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var myIntent= Intent(this@MainActivity,ChatActivity::class.java)
            myIntent.putExtra("data",et_account.text.toString())
            startActivity(myIntent)
        }
    }
}
