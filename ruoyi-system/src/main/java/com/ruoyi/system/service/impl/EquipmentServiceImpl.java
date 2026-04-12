package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EquipmentMapper;
import com.ruoyi.system.domain.Equipment;
import com.ruoyi.system.service.IEquipmentService;

/**
 * 设备管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备管理
     * 
     * @param equipmentId 设备管理主键
     * @return 设备管理
     */
    @Override
    public Equipment selectEquipmentByEquipmentId(Long equipmentId)
    {
        return equipmentMapper.selectEquipmentByEquipmentId(equipmentId);
    }

    /**
     * 查询设备管理列表
     * 
     * @param equipment 设备管理
     * @return 设备管理
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment)
    {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment)
    {
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment)
    {
        return equipmentMapper.updateEquipment(equipment);
    }

    /**
     * 批量删除设备管理
     * 
     * @param equipmentIds 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds)
    {
        return equipmentMapper.deleteEquipmentByEquipmentIds(equipmentIds);
    }

    /**
     * 删除设备管理信息
     * 
     * @param equipmentId 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteEquipmentByEquipmentId(Long equipmentId)
    {
        return equipmentMapper.deleteEquipmentByEquipmentId(equipmentId);
    }
}
