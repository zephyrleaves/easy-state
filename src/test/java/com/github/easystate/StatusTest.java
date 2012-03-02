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
        Status<OrderState, OrderEvent> status = new Status<OrderState, OrderEvent>(OrderState.INIT);
        Assert.assertEquals(OrderState.valueOf("INIT"), status.getCurrentState());
    }

    @Test
    public void testHappen() {
        Status<OrderState, OrderEvent> status = new Status<OrderState, OrderEvent>(OrderState.INIT);
        Assert.assertEquals(OrderState.INIT, status.getCurrentState());
        HappenedResult result = status.happen(OrderEvent.DO);
        Assert.assertEquals(result, HappenedResult.SUCCESS);
        Assert.assertTrue(result.isSuccess());
        Assert.assertEquals(status.getCurrentState(), OrderState.END);
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);


        HappenedResult result1 = status.happen(null);
        Assert.assertEquals(result1, HappenedResult.NO_EVENT);
        Assert.assertEquals(status.getCurrentState(), OrderState.END);
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);

        HappenedResult result2 = status.happen(OrderEvent.DONE);
        Assert.assertEquals(result2, HappenedResult.NO_NEXT);
        Assert.assertEquals(status.getCurrentState(), OrderState.END);
        Assert.assertEquals(status.getPreviousState(), OrderState.INIT);


        Status<OrderState, OrderEvent> status1 = new Status<OrderState, OrderEvent>(OrderState.TEST);
        HappenedResult result3 = status1.happen(OrderEvent.DO);
        Assert.assertEquals(result3, HappenedResult.NO_ARC);
        Assert.assertEquals(status1.getCurrentState(), OrderState.TEST);
        Assert.assertEquals(status1.getPreviousState(), null);
    }
}

