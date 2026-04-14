package com.ruoyi.apiary.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蜂厂管理对象 apiary
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public class Apiary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 蜂场ID */
    private Long apiaryId;

    /** 蜂场名称 */
    @Excel(name = "蜂场名称")
    private String apiaryName;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 蜂场地址 */
    @Excel(name = "蜂场地址")
    private String apiaryAddress;

    /** 位置 */
    private String location;

    public void setApiaryId(Long apiaryId) 
    {
        this.apiaryId = apiaryId;
    }

    public Long getApiaryId() 
    {
        return apiaryId;
    }

    public void setApiaryName(String apiaryName) 
    {
        this.apiaryName = apiaryName;
    }

    public String getApiaryName() 
    {
        return apiaryName;
    }

    public void setContactName(String contactName) 
    {
        this.contactName = contactName;
    }

    public String getContactName() 
    {
        return contactName;
    }

    public void setContactPhone(String contactPhone) 
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone() 
    {
        return contactPhone;
    }

    public void setApiaryAddress(String apiaryAddress)
    {
        this.apiaryAddress = apiaryAddress;
    }

    public String getApiaryAddress()
    {
        return apiaryAddress;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getLocation()
    {
        return location;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("apiaryId", getApiaryId())
            .append("apiaryName", getApiaryName())
            .append("contactName", getContactName())
            .append("contactPhone", getContactPhone())
            .append("apiaryAddress", getApiaryAddress())
            .append("location", getLocation())
            .toString();
    }
}
