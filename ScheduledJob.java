package com.push.scheduling;

import com.push.entity.PushMsgInfo;
import com.push.service.PushMsgService;
import org.jboss.logging.Logger;
import org.quartz.*;
import org.quartz.impl.JobExecutionContextImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJob implements Job {
 
    private static final Logger logger= Logger.getLogger(ScheduledJob.class);
 
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        Integer appId = mergedJobDataMap.getInt("appId");
        String msg = mergedJobDataMap.getString("msg");
        String title = mergedJobDataMap.getString("title");
        pushMsgService.pushMsg( appId,  msg, title);
        logger.info("执行推送任务"+appId+msg+title);
    }

    @Autowired
    private PushMsgService pushMsgService;
}
