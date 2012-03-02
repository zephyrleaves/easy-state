package com.github.easystate;

/**
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 12-3-2 下午4:35
 */
public enum OrderState implements State {
    @Next(arc = {"DO->END", "DONE->END"})
    INIT,
    @Next()
    TEST,
    END;

    @Override public int getValue() {
        return ordinal();
    }

    @Override public String getOrginName() {
        return name();
    }

    @Override public OrderState getStateByName(String name) {
        return valueOf(name);
    }
}