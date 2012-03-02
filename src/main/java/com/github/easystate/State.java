package com.github.easystate;

/**
 * 状态类型
 *
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 2010-4-13 12:34:25
 */
public interface State{

    /**
     * 获取值
     *
     * @return the value (type int) of this State object.
     */
    int getValue();

    /**
     * 获取状态名
     *
     * @return 状态名
     */
    String getOrginName();


    /**
     * 获取对应的状态
     *
     * @param name of type String
     *
     * @return State
     */
    State getStateByName(String name);
}
