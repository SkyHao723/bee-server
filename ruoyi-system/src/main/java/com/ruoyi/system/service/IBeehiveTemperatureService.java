package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BeehiveTemperature;

/**
 * 蜂箱温湿度数据Service接口
 * 
 * @author ruoyi
 * @date 2026-04-13
 */
public interface IBeehiveTemperatureService 
{
    /**
     * 查询温湿度数据列表
     * 
     * @param beehiveTemperature 温湿度数据
     * @return 温湿度数据集合
     */
    public List<BeehiveTemperature> selectBeehiveTemperatureList(BeehiveTemperature beehiveTemperature);

    /**
     * 查询最新一条温湿度数据
     * 
     * @param apiaryId 蜂场ID
     * @param beehiveId 蜂箱ID
     * @return 温湿度数据
     */
    public BeehiveTemperature selectLatestTemperature(Long apiaryId, Long beehiveId);
}
