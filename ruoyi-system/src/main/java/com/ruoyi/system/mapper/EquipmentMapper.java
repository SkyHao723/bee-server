package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Equipment;

/**
 * 设备管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public interface EquipmentMapper 
{
    /**
     * 查询设备管理
     * 
     * @param equipmentId 设备管理主键
     * @return 设备管理
     */
    public Equipment selectEquipmentByEquipmentId(Long equipmentId);

    /**
     * 查询设备管理列表
     * 
     * @param equipment 设备管理
     * @return 设备管理集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备管理
     * 
     * @param equipment 设备管理
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 删除设备管理
     * 
     * @param equipmentId 设备管理主键
     * @return 结果
     */
    public int deleteEquipmentByEquipmentId(Long equipmentId);

    /**
     * 批量删除设备管理
     * 
     * @param equipmentIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEquipmentByEquipmentIds(Long[] equipmentIds);
}
