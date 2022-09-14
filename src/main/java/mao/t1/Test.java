package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Project name(项目名称)：java并发编程_CountdownLatch
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/14
 * Time(创建时间)： 12:10
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{

    private static final CountDownLatch countDownLatch = new CountDownLatch(3);

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    /**
     * 运行
     *
     * @param sleepTime 睡眠时间
     */
    public static void run(long sleepTime)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    log.debug("开始运行");
                    try
                    {
                        Thread.sleep(sleepTime);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                finally
                {
                    log.debug("结束运行");
                    countDownLatch.countDown();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException
    {
        run(1000);
        run(3000);
        run(2000);
        run(500);
        countDownLatch.await();
        log.debug("主线程开始运行");
    }
}
