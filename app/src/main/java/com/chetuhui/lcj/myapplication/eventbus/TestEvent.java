package com.chetuhui.lcj.myapplication.eventbus;

public class TestEvent {
    private int mMsg;
    public TestEvent(int msg) {
        mMsg = msg;
    }
    public int getMsg(){
        return mMsg;
    }
}