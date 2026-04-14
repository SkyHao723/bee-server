package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.mapper.BeehiveMapper;
import com.ruoyi.system.mapper.EquipmentMapper;
import com.ruoyi.system.domain.Beehive;
import com.ruoyi.system.service.IBeehiveService;

/**
 * 蜂箱管理Service业务层处理
 *
 * @author ruoyi
 * @date 2026-04-12
 */
@Service
public class BeehiveServiceImpl implements IBeehiveService
{
    @Autowired
    private BeehiveMapper beehiveMapper;

    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询蜂箱管理
     * 
     * @param beehiveId 蜂箱管理主键
     * @return 蜂箱管理
     */
    @Override
    public Beehive selectBeehiveByBeehiveId(Long beehiveId)
    {
        return beehiveMapper.selectBeehiveByBeehiveId(beehiveId);
    }

    /**
     * 通过权力密钥查询蜂箱
     *
     * @param power 权力密钥
     * @return 蜂箱
     */
    @Override
    public Beehive selectBeehiveByPower(String power)
    {
        return beehiveMapper.selectBeehiveByPower(power);
    }

    /**
     * 查询蜂箱管理列表
     * 
     * @param beehive 蜂箱管理
     * @return 蜂箱管理
     */
    @Override
    public List<Beehive> selectBeehiveList(Beehive beehive)
    {
        return beehiveMapper.selectBeehiveList(beehive);
    }

    /**
     * 新增蜂箱管理
     * 
     * @param beehive 蜂箱管理
     * @return 结果
     */
    @Override
    public int insertBeehive(Beehive beehive)
    {
        // 默认状态为未激活(0)
        if (beehive.getBeehiveStatus() == null)
        {
            beehive.setBeehiveStatus(0);
        }
        return beehiveMapper.insertBeehive(beehive);
    }

    /**
     * 修改蜂箱管理
     * 
     * @param beehive 蜂箱管理
     * @return 结果
     */
    @Override
    public int updateBeehive(Beehive beehive)
    {
        return beehiveMapper.updateBeehive(beehive);
    }

    /**
     * 批量删除蜂箱管理
     * 
     * @param beehiveIds 需要删除的蜂箱管理主键
     * @return 结果
     */
    @Override
    public int deleteBeehiveByBeehiveIds(Long[] beehiveIds)
    {
        return beehiveMapper.deleteBeehiveByBeehiveIds(beehiveIds);
    }

    /**
     * 删除蜂箱管理信息
     *
     * @param beehiveId 蜂箱管理主键
     * @return 结果
     */
    @Override
    public int deleteBeehiveByBeehiveId(Long beehiveId)
    {
        return beehiveMapper.deleteBeehiveByBeehiveId(beehiveId);
    }

    /**
     * 激活蜂箱并关联设备
     */
    @Override
    @Transactional
    public int activateBeehive(Beehive beehive, Integer deviceStatus)
    {
        // 1. 更新蜂箱状态为已激活(1)，同时更新power
        beehive.setBeehiveStatus(1);
        int result = beehiveMapper.updateBeehive(beehive);

        // 2. 根据beehiveId和apiaryId找到设备，更新设备的蜂箱ID和状态
        if (result > 0 && beehive.getBeehiveId() != null && beehive.getApiaryId() != null)
        {
            // 创建Equipment对象更新beehiveId和状态
            com.ruoyi.system.domain.Equipment equipment = new com.ruoyi.system.domain.Equipment();
            equipment.setBeehiveId(beehive.getBeehiveId());
            equipment.setApiaryId(beehive.getApiaryId());
            equipment.setDeviceStatus(deviceStatus);
            // 根据apiaryId查找设备并更新
            equipmentMapper.updateEquipmentByBeehiveIdAndApiaryId(equipment);
        }

        return result;
    }
}
