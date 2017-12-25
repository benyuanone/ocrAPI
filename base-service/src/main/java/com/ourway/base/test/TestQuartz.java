package com.ourway.base.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2017/9/23.
 */
public class TestQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) + "★★★★★★★★★★★");
    }
}
