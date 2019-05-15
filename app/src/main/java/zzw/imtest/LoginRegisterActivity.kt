package zzw.imtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import cn.jpush.im.android.api.JMessageClient
import cn.jpush.im.api.BasicCallback
import kotlinx.android.synthetic.main.activity_login_register.*

class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)


        btn_login.setOnClickListener {
            if(TextUtils.isEmpty(et_account.text.toString())){
                Toast.makeText(this@LoginRegisterActivity,"请输入账号",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(et_pw.text.toString())){
                Toast.makeText(this@LoginRegisterActivity,"请输入密码",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            JMessageClient.login(et_account.text.toString(),et_pw.text.toString(),object :BasicCallback(){
                override fun gotResult(p0: Int, p1: String?) {
                    Toast.makeText(this@LoginRegisterActivity,p0.toString()+" "+p1,Toast.LENGTH_SHORT).show()

                    var myIntent= Intent(this@LoginRegisterActivity,MainActivity::class.java)
                    startActivity(myIntent)
                }
            })

        }

        btn_register.setOnClickListener {
            if(TextUtils.isEmpty(et_account.text.toString())){
                Toast.makeText(this@LoginRegisterActivity,"请输入账号",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(et_pw.text.toString())){
                Toast.makeText(this@LoginRegisterActivity,"请输入密码",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            JMessageClient.register(et_account.text.toString(),et_pw.text.toString(),object :BasicCallback(){
                override fun gotResult(p0: Int, p1: String?) {
                    Toast.makeText(this@LoginRegisterActivity,p0.toString()+" "+p1,Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
