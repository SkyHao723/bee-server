package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Beehive;

/**
 * 蜂箱管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public interface BeehiveMapper 
{
    /**
     * 查询蜂箱管理
     * 
     * @param beehiveId 蜂箱管理主键
     * @return 蜂箱管理
     */
    public Beehive selectBeehiveByBeehiveId(Long beehiveId);

    /**
     * 通过权力密钥查询蜂箱
     *
     * @param power 权力密钥
     * @return 蜂箱
     */
    public Beehive selectBeehiveByPower(String power);

    /**
     * 查询蜂箱管理列表
     * 
     * @param beehive 蜂箱管理
     * @return 蜂箱管理集合
     */
    public List<Beehive> selectBeehiveList(Beehive beehive);

    /**
     * 新增蜂箱管理
     * 
     * @param beehive 蜂箱管理
     * @return 结果
     */
    public int insertBeehive(Beehive beehive);

    /**
     * 修改蜂箱管理
     * 
     * @param beehive 蜂箱管理
     * @return 结果
     */
    public int updateBeehive(Beehive beehive);

    /**
     * 删除蜂箱管理
     * 
     * @param beehiveId 蜂箱管理主键
     * @return 结果
     */
    public int deleteBeehiveByBeehiveId(Long beehiveId);

    /**
     * 批量删除蜂箱管理
     * 
     * @param beehiveIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBeehiveByBeehiveIds(Long[] beehiveIds);
}
