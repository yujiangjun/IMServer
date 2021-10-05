package cn.com.yujiangjun.myim.constants;

public enum MsgDirect {

    SEND(1,"发送"),
    RECEIVE(2,"接收");
    int code;
    String value;

    MsgDirect(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
