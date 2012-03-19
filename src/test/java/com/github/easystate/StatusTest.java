package com.github.easystate;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author <a href="mailto:zephyrleaves@gmail.com"></a>
 * @since 12-3-2 下午4:36
 */
public class StatusTest {
    @org.testng.annotations.BeforeTest
    public void setUp() {
    }

    @Test
    public void testConstuructor() {
        Status<OrderState, OrderEvent> status = new Status<OrderState, OrderEvent>(OrderState.INIT);    //初始化状态为INIT
        Assert.assertEquals(OrderState.valueOf("INIT"), status.getCurrentState());
    }

    @Test
    public void testHappen() {
        Status<OrderState, OrderEvent> status = new Status<OrderState, OrderEvent>(OrderState.INIT);    //初始化状态为INIT
        Assert.assertEquals(OrderState.INIT, status.getCurrentState());
        HappenedResult result = status.happen(OrderEvent.DO);      //当前状态出发了DO事件
        Assert.assertEquals(result, HappenedResult.SUCCESS);       //跳转成功
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(status.getCurrentState(), OrderState.END);    //当前状态已经跳转为END
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);  //上一个状态为INIT


        HappenedResult result1 = status.happen(null);               //没有发生事件
        Assert.assertEquals(result1, HappenedResult.NO_EVENT);       //提示你没事件发生
        Assert.assertEquals(status.getCurrentState(), OrderState.END);         //当前状态还是END
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);       //上一个状态还是INIT

        HappenedResult result2 = status.happen(OrderEvent.DONE);      //在当前状态发了DONE事件
        Assert.assertEquals(result2, HappenedResult.NO_NEXT);         //这个状态下没有定义会触发DONE事件
        Assert.assertEquals(status.getCurrentState(), OrderState.END);      //当前状态还是END
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);   //上一个状态还是INIT


        Status<OrderState, OrderEvent> status1 = new Status<OrderState, OrderEvent>(OrderState.TEST); //初始化状态为TEST
        HappenedResult result3 = status1.happen(OrderEvent.DO);             //在当前状态发了DO事件
        Assert.assertEquals(result3, HappenedResult.NO_ARC);                //这个状态下没有定义会触发的事件
        Assert.assertEquals(status1.getCurrentState(), OrderState.TEST);         //当前状态还是TEST
        Assert.assertEquals(status1.getPreviousState(), null);              //没有上一个状态
    }
}

