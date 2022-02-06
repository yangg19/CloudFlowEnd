package com.cnpc.server;

import com.cnpc.server.service.ITodolistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyTask {
    @Autowired
    private ITodolistService todolistService;
//    @Scheduled(cron = "*/5 * * * * ?") // 每隔5秒钟执行一次

    @Scheduled(cron = "0 0 0/1 * * ?") // 每隔整点执行一次
    public void firstTask() {
        System.out.println(new Date() + ":这是第1个定时任务");
        todolistService.updateTaskStatus(new Date());
    }
}
