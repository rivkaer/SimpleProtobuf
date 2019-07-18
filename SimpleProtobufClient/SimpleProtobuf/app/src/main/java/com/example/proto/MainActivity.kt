package com.example.proto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proto.net.JsonBeanMessage
import com.example.proto.net.RetrofitHandler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DefaultObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vCenterHello.setOnClickListener {
            RetrofitHandler.getInstance("http://192.168.2.103:8060").service.sendMessage()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<Message.LoginResponse>() {
                    override fun onNext(t: Message.LoginResponse) {
                        Log.e("protobuf --> ", "code : ${t.code} msg: ${t.msg}")
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {

                    }
                })
        }

        vCenterHelloJson.setOnClickListener {
            RetrofitHandler.getInstance("http://192.168.2.103:8060").service.sendJsonMessage()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DefaultObserver<JsonBeanMessage>() {
                    override fun onNext(t: JsonBeanMessage) {
                        Log.e("protobuf --> ", "code : ${t.code} msg: ${t.msg}")
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {

                    }
                })
        }


    }
}
