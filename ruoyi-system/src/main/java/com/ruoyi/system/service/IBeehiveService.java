package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Beehive;
import org.springframework.transaction.annotation.Transactional;

/**
 * 蜂箱管理Service接口
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public interface IBeehiveService 
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
     * 批量删除蜂箱管理
     * 
     * @param beehiveIds 需要删除的蜂箱管理主键集合
     * @return 结果
     */
    public int deleteBeehiveByBeehiveIds(Long[] beehiveIds);

    /**
     * 删除蜂箱管理信息
     *
     * @param beehiveId 蜂箱管理主键
     * @return 结果
     */
    public int deleteBeehiveByBeehiveId(Long beehiveId);

    /**
     * 激活蜂箱并关联设备（扫码绑定后调用）
     *
     * @param beehive 蜂箱信息（包含beehiveId和power）
     * @param deviceStatus 设备状态
     * @return 结果
     */
    @Transactional
    public int activateBeehive(Beehive beehive, Integer deviceStatus);
}
