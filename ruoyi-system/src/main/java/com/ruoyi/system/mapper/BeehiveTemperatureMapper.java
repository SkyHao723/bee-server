package com.ruoyi.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.ruoyi.system.domain.BeehiveTemperature;

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
    int insertBeehiveTemperature(@Param("apiaryId") Long apiaryId, 
                                  @Param("beehiveId") Long beehiveId, 
                                  @Param("temperature") Double temperature, 
                                  @Param("humidity") Double humidity);

    /**
     * 查询温湿度数据列表
     * 
     * @param beehiveTemperature 温湿度数据
     * @return 温湿度数据集合
     */
    List<BeehiveTemperature> selectBeehiveTemperatureList(BeehiveTemperature beehiveTemperature);

    /**
     * 查询最新一条温湿度数据
     * 
     * @param apiaryId 蜂场ID
     * @param beehiveId 蜂箱ID
     * @return 温湿度数据
     */
    BeehiveTemperature selectLatestTemperature(@Param("apiaryId") Long apiaryId, 
                                                @Param("beehiveId") Long beehiveId);
}
