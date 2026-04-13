package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 蜂箱温湿度数据对象 beehive_temperature
 * 
 * @author ruoyi
 * @date 2026-04-13
 */
public class BeehiveTemperature
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 蜂场ID */
    @Excel(name = "蜂场ID")
    private Long apiaryId;

    /** 蜂箱ID */
    @Excel(name = "蜂箱ID")
    private Long beehiveId;

    /** 温度 */
    @Excel(name = "温度")
    private Double temperature;

    /** 湿度 */
    @Excel(name = "湿度")
    private Double humidity;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setApiaryId(Long apiaryId) 
    {
        this.apiaryId = apiaryId;
    }

    public Long getApiaryId() 
    {
        return apiaryId;
    }

    public void setBeehiveId(Long beehiveId) 
    {
        this.beehiveId = beehiveId;
    }

    public Long getBeehiveId() 
    {
        return beehiveId;
    }

    public void setTemperature(Double temperature) 
    {
        this.temperature = temperature;
    }

    public Double getTemperature() 
    {
        return temperature;
    }

    public void setHumidity(Double humidity) 
    {
        this.humidity = humidity;
    }

    public Double getHumidity() 
    {
        return humidity;
    }

    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
}
