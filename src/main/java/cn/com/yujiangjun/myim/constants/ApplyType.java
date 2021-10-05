package cn.com.yujiangjun.myim.constants;

public enum ApplyType {

    FRIEND(1,"好友"),
    GROUP(2,"群组");
    int code;
    String value;

    ApplyType(int code, String value) {
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
