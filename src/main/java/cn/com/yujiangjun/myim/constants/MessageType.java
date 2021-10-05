package cn.com.yujiangjun.myim.constants;

/**
 * 消息类型
 */
public enum MessageType {
    TEXT(1,"文本"),IMG(2,"图片"),EMOJI(3,"表情"),AUDIO(4,"音频"),VIDEO(5,"视频"),REGISTER(6,"登录注册");

    int code;
    String value;

    MessageType(int code, String value) {
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
