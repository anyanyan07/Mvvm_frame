package com.ayy.usercenter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ayy.usercenter.databinding.ActivityLoginBinding
import com.ayy.usercenter.event.LoginEvent
import org.greenrobot.eventbus.EventBus

class LoginActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding.btnLogin.setOnClickListener {
            login()
            finish()
        }
    }

    private fun login() {
        //模拟登录
        val loginEvent = LoginEvent(mBinding.etUsername.text.toString())
        EventBus.getDefault().post(loginEvent)

    }
}