package mao.t3;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Project name(项目名称)：java并发编程_CountdownLatch
 * Package(包名): mao.t3
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/14
 * Time(创建时间)： 12:28
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Test
{
    public static void main(String[] args) throws InterruptedException
    {
        AtomicInteger num = new AtomicInteger(0);

        ExecutorService service = Executors.newFixedThreadPool(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        String[] all = new String[10];
        Random random = new Random();

        for (int j = 0; j < 10; j++)
        {
            int x = j;
            service.submit(new Runnable()
            {
                @Override
                public void run()
                {
                    for (int i = 0; i <= 100; i++)
                    {
                        try
                        {
                            Thread.sleep(random.nextInt(500));
                        }
                        catch (InterruptedException ignored)
                        {
                        }
                        all[x] = Thread.currentThread().getName() + "(" + (i + "%") + ")";
                        System.out.print("\r" + Arrays.toString(all));
                    }
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        System.out.println();
        Toolkit.getDefaultToolkit().beep();
        System.out.println("加载完毕！！！");
        service.shutdown();
    }
}
