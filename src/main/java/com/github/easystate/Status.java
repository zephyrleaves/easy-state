package com.github.easystate;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 状态
 *
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 2010-4-13 10:12:07
 */
public class Status<T extends State, E extends Event> implements Serializable {
    private static final long serialVersionUID = 6108415012324114353L;
    private static final String ARC_REGEX = "->";

    /**
     * Constructor Status creates a new Status instance.
     *
     * @param currentState of type T
     */
    public Status(T currentState) {
        this.currentState = currentState;
    }

    /**
     * 当前状态
     */
    private T currentState;
    /**
     * 上一个状态
     */
    private T previousState;


    /**
     * 发生事件
     *
     * @param event of type Event
     *
     * @return 变更是否成功
     */
    public HappenedResult happen(E event) {
        try {
            if (event == null) {
                return HappenedResult.NO_EVENT;
            }
            Next annotation = currentState.getClass().getField(currentState.getOrginName()).getAnnotation(Next.class);
            if (annotation == null) {
                return HappenedResult.NO_NEXT;
            }
            for (String arc : annotation.arc()) {
                if (StringUtils.contains(arc, ARC_REGEX)) {
                    String[] paths = StringUtils.split(arc, ARC_REGEX);
                    String eventName = paths[0];
                    String destStateName = paths[1];
                    if (eventName.equals(event.getName())) {
                        T nextStatus = (T) currentState.getStateByName(destStateName);
                        if (nextStatus != null) {
                            previousState = currentState;
                            currentState = nextStatus;
                            return HappenedResult.SUCCESS;
                        }
                    }
                }
            }
            return HappenedResult.NO_ARC;
        } catch (NoSuchFieldException e) {
            return HappenedResult.NO_FIELD;
        } catch (Exception e) {
            return HappenedResult.UNKNOW;
        }
    }

    /**
     * Method getCurrentState returns the currentState of this Status object.
     * <p/>
     * 当前状态
     *
     * @return the currentState (type T) of this Status object.
     */
    public T getCurrentState() {
        return currentState;
    }

    /**
     * Method getPreviousState returns the previousState of this Status object.
     * <p/>
     * 上一个状态
     *
     * @return the previousState (type T) of this Status object.
     */
    public T getPreviousState() {
        return previousState;
    }
}
