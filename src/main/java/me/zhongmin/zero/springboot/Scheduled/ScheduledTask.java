package me.zhongmin.zero.springboot.Scheduled;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring内置定时任务
 *
 * fixedRate  该属性的含义是上一个调用开始后再次调用的延时（不用等待上一次调用完成），这样就会存在重复执行的问题，所以不是建议使用
 *
 * fixedDelay 该属性的功效与上面的fixedRate则是相反的，配置了该属性后会等到方法执行完成后延迟配置的时间再次执行该方法
 */
@Component
@EnableScheduling
public class ScheduledTask {
    /**
     *
     */
    @Scheduled(cron = "0 0/50 * * * ?")
    public void testTask(){
        System.out.println("ScheduledTask.testTask is runing");
    }

    //@Scheduled(fixedDelay = 1000)
    public void fixRate(){
        System.out.println("ScheduledTask.testTask is runing");
    }
}
