package com.github.easystate;

/**
 * 发生后的结果
 *
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 2010-4-13 13:29:07
 */
public enum HappenedResult {
    /**
     * 成功
     */
    SUCCESS,
    /**
     * 没有这个状态
     */
    NO_FIELD,
    /**
     * 没有配置下一步操作
     */
    NO_NEXT,
    /**
     * 没有配置路径
     */
    NO_ARC,
    /**
     * 没有事件
     */
    NO_EVENT,
    /**
     * 位置错误
     */
    UNKNOW;

    /**
     * 是否成功
     *
     * @return true 成功 false 不成功
     */
    public boolean isSuccess() {
        return SUCCESS.equals(this);
    }

}
