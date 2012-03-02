package com.github.easystate;

/**
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 12-3-2 下午4:33
 */
public enum OrderEvent implements Event{
    //正在进行中
    DO,
    //做完了
    DONE;

    @Override public String getName() {
        return name();
    }
}
