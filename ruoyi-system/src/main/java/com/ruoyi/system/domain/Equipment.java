package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备管理对象 equipment
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public class Equipment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备编号 */
    private Long equipmentId;

    /** 蜂箱ID */
    @Excel(name = "蜂箱ID")
    private Long beehiveId;

    /** 蜂厂ID */
    @Excel(name = "蜂厂ID")
    private Long apiaryId;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Integer deviceStatus;

    public void setEquipmentId(Long equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public Long getEquipmentId() 
    {
        return equipmentId;
    }

    public void setBeehiveId(Long beehiveId) 
    {
        this.beehiveId = beehiveId;
    }

    public Long getBeehiveId() 
    {
        return beehiveId;
    }

    public void setApiaryId(Long apiaryId) 
    {
        this.apiaryId = apiaryId;
    }

    public Long getApiaryId() 
    {
        return apiaryId;
    }

    public void setDeviceStatus(Integer deviceStatus) 
    {
        this.deviceStatus = deviceStatus;
    }

    public Integer getDeviceStatus() 
    {
        return deviceStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("equipmentId", getEquipmentId())
            .append("beehiveId", getBeehiveId())
            .append("apiaryId", getApiaryId())
            .append("deviceStatus", getDeviceStatus())
            .toString();
    }
}
