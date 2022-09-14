package mao.t2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Project name(项目名称)：java并发编程_CountdownLatch
 * Package(包名): mao.t2
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/14
 * Time(创建时间)： 12:22
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    private static final CountDownLatch countDownLatch = new CountDownLatch(5);

    /**
     * 线程池
     */
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws InterruptedException
    {
        for (int i = 0; i < 5; i++)
        {
            threadPool.submit(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        log.debug("开始运行");
                        try
                        {
                            Thread.sleep(1000);
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
            });
        }
        countDownLatch.await();
        log.debug("主线程开始运行");
    }
}
