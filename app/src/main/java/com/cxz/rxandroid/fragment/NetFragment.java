package com.cxz.rxandroid.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cxz.rxandroid.R;
import com.cxz.rxandroid.protocol.TestProtocol;
import com.cxz.rxandroid.utils.XLog;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * RxJava网络请求
 * Created by chenxz on 2017/3/8.
 */
public class NetFragment extends RxFragment {

    @Bind(R.id.tv_result)
    TextView tv_result;

    private TestProtocol mTestProtocol;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net,null);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTestProtocol = new TestProtocol();
    }

    @OnClick(R.id.btn_get)
    void click_get(){
        mTestProtocol.text_Get()
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String data) {
                        XLog.d(data);
                        tv_result.setText("Get Result:\r\n" + data);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Get Error\r\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_post)
    void click_post(){
        TreeMap<String,Object> params = new TreeMap<>();
        //TODO
        //params.put();
        mTestProtocol.text_Post(params)
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv_result.setText("Post Result:\r\n" + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Post Error:\r\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_put)
    void click_put(){
        TreeMap<String,Object> params = new TreeMap<>();
        //TODO
        //params.put();
        mTestProtocol.text_Put(params)
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv_result.setText("Put Result:\r\n" + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Put Error:\r\n" + throwable.getMessage());
                    }
                });
    }

    //@OnClick(R.id.btn_delete)
    void click_delete(){
        mTestProtocol.text_Delete()
                .compose(this.<String>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv_result.setText("Delete Result:\r\n" + s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        tv_result.setText("Delete Error:\r\n" + throwable.getMessage());
                    }
                });
    }

}
