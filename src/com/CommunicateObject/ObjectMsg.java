package com.CommunicateObject;

import java.io.Serializable;

public abstract class ObjectMsg implements Serializable {
    public abstract String getMsg();
    public abstract void setMsg(String msg);
}
