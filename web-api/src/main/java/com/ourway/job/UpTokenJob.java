package com.ourway.job;

import com.ourway.baiduapi.action.BaiDuApi;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by D.chen.g on 2017/12/26.
 */
public class UpTokenJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        BaiDuApi.getToKen();
    }
}
