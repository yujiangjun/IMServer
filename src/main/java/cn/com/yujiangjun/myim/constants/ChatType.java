package cn.com.yujiangjun.myim.constants;

public enum ChatType {

    SINGLE(1,"åč"),
    GROUP(2,"įž¤č");
    int code;
    String value;

    ChatType(int code, String value) {
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
