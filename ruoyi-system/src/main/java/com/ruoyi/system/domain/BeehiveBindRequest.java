package com.ruoyi.system.domain;

import jakarta.validation.constraints.NotBlank;

/**
 * 蜂箱绑定请求体
 *
 * @author ruoyi
 * @date 2026-04-14
 */
public class BeehiveBindRequest
{
    /** 二维码数据 */
    @NotBlank(message = "二维码数据不能为空")
    private String qrCode;

    /** 蜂厂ID（如果二维码不包含蜂厂ID，则使用此参数） */
    private Long apiaryId;

    public String getQrCode()
    {
        return qrCode;
    }

    public void setQrCode(String qrCode)
    {
        this.qrCode = qrCode;
    }

    public Long getApiaryId()
    {
        return apiaryId;
    }

    public void setApiaryId(Long apiaryId)
    {
        this.apiaryId = apiaryId;
    }

    @Override
    public String toString()
    {
        return "BeehiveBindRequest{qrCode='" + qrCode + "', apiaryId=" + apiaryId + "}";
    }
}