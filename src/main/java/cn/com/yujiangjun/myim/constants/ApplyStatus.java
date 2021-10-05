package cn.com.yujiangjun.myim.constants;

public enum ApplyStatus {
    PASS(1,"通过"),
    REJECT(2,"决绝");
    int code;
    String value;

    ApplyStatus(int code, String value) {
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
