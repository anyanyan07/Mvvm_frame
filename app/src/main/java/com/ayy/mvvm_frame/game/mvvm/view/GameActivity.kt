package com.ayy.mvvm_frame.game.mvvm.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.ayy.mvvm_frame.R
import com.ayy.mvvm_frame.databinding.ActivityGameBinding
import com.ayy.mvvm_frame.game.mvvm.viewmodel.GameViewModel

class GameActivity : AppCompatActivity() {
    lateinit var mBinding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game)
        mBinding.viewModel = GameViewModel()
    }
}