package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 蜂箱温湿度数据Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-13
 */
@Mapper
public interface BeehiveTemperatureMapper 
{
    /**
     * 插入温湿度数据
     * 
     * @param apiaryId 蜂场ID
     * @param beehiveId 蜂箱ID
     * @param temperature 温度
     * @param humidity 湿度
     * @return 结果
     */
    int insertBeehiveTemperature(Long apiaryId, Long beehiveId, Double temperature, Double humidity);
}
