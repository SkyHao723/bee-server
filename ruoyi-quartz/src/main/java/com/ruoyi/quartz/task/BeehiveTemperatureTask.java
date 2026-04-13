package com.ruoyi.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.ruoyi.system.mapper.BeehiveTemperatureMapper;

/**
 * 温湿度数据定时任务
 * 
 * @author ruoyi
 */
@Component("beehiveTemperatureTask")
public class BeehiveTemperatureTask
{
    private static final Logger log = LoggerFactory.getLogger(BeehiveTemperatureTask.class);

    @Autowired
    private BeehiveTemperatureMapper beehiveTemperatureMapper;

    /**
     * 定时插入温湿度假数据
     * apiary_id=1, beehive_id=1
     * 温度范围：-10 到 60
     * 湿度范围：30 到 100
     * cron表达式：每5分钟执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void generateTemperatureData()
    {
        // 固定值
        Long apiaryId = 1L;
        Long beehiveId = 1L;

        // 生成随机温度（-10 到 60）
        double temperature = Math.round((Math.random() * 70 - 10) * 10.0) / 10.0;

        // 生成随机湿度（30 到 100）
        double humidity = Math.round((Math.random() * 70 + 30) * 10.0) / 10.0;

        // 插入数据
        int result = beehiveTemperatureMapper.insertBeehiveTemperature(apiaryId, beehiveId, temperature, humidity);

        log.info("定时任务执行成功：插入温湿度数据 - apiaryId:{}, beehiveId:{}, temperature:{}, humidity:{}", 
                apiaryId, beehiveId, temperature, humidity);
    }
}
