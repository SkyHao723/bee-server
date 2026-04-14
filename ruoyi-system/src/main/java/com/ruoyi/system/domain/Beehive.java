package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蜂箱管理对象 beehive
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public class Beehive extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 蜂箱ID */
    @Excel(name = "蜂箱ID")
    private Long beehiveId;

    /** 蜂厂ID */
    @Excel(name = "蜂厂ID")
    private Long apiaryId;

    /** 蜂箱名称 */
    @Excel(name = "蜂箱名称")
    private String beehiveName;

    /** 蜂箱状态 */
    @Excel(name = "蜂箱状态")
    private Integer beehiveStatus;

    /** 蜂箱组 */
    @Excel(name = "蜂箱组")
    private Long beehiveGroup;

    /** 位置 */
    @Excel(name = "位置")
    private String location;

    /** 权力密钥（用于二维码绑定验证） */
    @Excel(name = "权力密钥")
    private String power;

    /** 蜂厂名称 */
    private String apiaryName;

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

    public void setBeehiveName(String beehiveName) 
    {
        this.beehiveName = beehiveName;
    }

    public String getBeehiveName() 
    {
        return beehiveName;
    }

    public void setBeehiveStatus(Integer beehiveStatus) 
    {
        this.beehiveStatus = beehiveStatus;
    }

    public Integer getBeehiveStatus() 
    {
        return beehiveStatus;
    }

    public void setBeehiveGroup(Long beehiveGroup) 
    {
        this.beehiveGroup = beehiveGroup;
    }

    public Long getBeehiveGroup() 
    {
        return beehiveGroup;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    public void setPower(String power)
    {
        this.power = power;
    }

    public String getPower()
    {
        return power;
    }

    public String getApiaryName()
    {
        return apiaryName;
    }

    public void setApiaryName(String apiaryName)
    {
        this.apiaryName = apiaryName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("beehiveId", getBeehiveId())
            .append("apiaryId", getApiaryId())
            .append("beehiveName", getBeehiveName())
            .append("beehiveStatus", getBeehiveStatus())
            .append("beehiveGroup", getBeehiveGroup())
            .append("location", getLocation())
            .append("power", getPower())
            .toString();
    }
}
