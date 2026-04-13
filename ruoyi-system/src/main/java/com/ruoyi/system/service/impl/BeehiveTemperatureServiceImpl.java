package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BeehiveTemperatureMapper;
import com.ruoyi.system.domain.BeehiveTemperature;
import com.ruoyi.system.service.IBeehiveTemperatureService;

/**
 * 蜂箱温湿度数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-13
 */
@Service
public class BeehiveTemperatureServiceImpl implements IBeehiveTemperatureService 
{
    @Autowired
    private BeehiveTemperatureMapper beehiveTemperatureMapper;

    /**
     * 查询温湿度数据列表
     * 
     * @param beehiveTemperature 温湿度数据
     * @return 温湿度数据
     */
    @Override
    public List<BeehiveTemperature> selectBeehiveTemperatureList(BeehiveTemperature beehiveTemperature)
    {
        return beehiveTemperatureMapper.selectBeehiveTemperatureList(beehiveTemperature);
    }

    /**
     * 查询最新一条温湿度数据
     * 
     * @param apiaryId 蜂场ID
     * @param beehiveId 蜂箱ID
     * @return 温湿度数据
     */
    @Override
    public BeehiveTemperature selectLatestTemperature(Long apiaryId, Long beehiveId)
    {
        return beehiveTemperatureMapper.selectLatestTemperature(apiaryId, beehiveId);
    }
}
