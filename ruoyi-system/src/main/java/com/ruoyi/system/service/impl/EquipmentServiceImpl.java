package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.Base64;
import java.security.MessageDigest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.mapper.EquipmentMapper;
import com.ruoyi.system.mapper.BeehiveMapper;
import com.ruoyi.system.domain.Equipment;
import com.ruoyi.system.domain.Beehive;
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

    @Autowired
    private BeehiveMapper beehiveMapper;

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
    @Transactional
    public int insertEquipment(Equipment equipment)
    {
        // 如果未设置设备状态，默认设置为0
        if (equipment.getDeviceStatus() == null)
        {
            equipment.setDeviceStatus(0);
        }
        // 先插入设备，获取设备编号
        int rows = equipmentMapper.insertEquipment(equipment);

        System.out.println("=== Equipment Insert Debug ===");
        System.out.println("rows: " + rows);
        System.out.println("equipmentId: " + equipment.getEquipmentId());
        System.out.println("beehiveId: " + equipment.getBeehiveId());
        System.out.println("apiaryId: " + equipment.getApiaryId());

        // 添加设备时生成power并存储到equipment表
        if (rows > 0 && equipment.getEquipmentId() != null)
        {
            // 密文：设备ID_固定密文，进行MD5加密
            String fixedSecret = "BEE_HIVE_SECRET_2026";
            String dataToHash = equipment.getEquipmentId() + "_" + fixedSecret;
            String power = md5(dataToHash);

            System.out.println("dataToHash: " + dataToHash);
            System.out.println("power (encrypted): " + power);

            // 更新设备的power字段（存加密后的密文）
            Equipment updateEquip = new Equipment();
            updateEquip.setEquipmentId(equipment.getEquipmentId());
            updateEquip.setPower(power);
            equipmentMapper.updateEquipment(updateEquip);
        }
        return rows;
    }

    /**
     * MD5加密
     */
    private String md5(String data)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        }
        catch (Exception e)
        {
            return null;
        }
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

    /**
     * 通过密文查找设备
     */
    @Override
    public Equipment selectEquipmentByPower(String power)
    {
        return equipmentMapper.selectEquipmentByPower(power);
    }
}
