package com.ruoyi.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.BeehiveTemperature;
import com.ruoyi.system.service.IBeehiveTemperatureService;

/**
 * 蜂箱温湿度数据Controller
 * 
 * @author ruoyi
 * @date 2026-04-13
 */
@RestController
@RequestMapping("/system/temperature")
public class BeehiveTemperatureController extends BaseController
{
    @Autowired
    private IBeehiveTemperatureService beehiveTemperatureService;

    /**
     * 查询温湿度数据列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BeehiveTemperature beehiveTemperature)
    {
        startPage();
        List<BeehiveTemperature> list = beehiveTemperatureService.selectBeehiveTemperatureList(beehiveTemperature);
        return getDataTable(list);
    }

    /**
     * 获取最新温湿度数据
     */
    @GetMapping("/latest/{apiaryId}/{beehiveId}")
    public AjaxResult getLatest(@PathVariable("apiaryId") Long apiaryId, 
                                @PathVariable("beehiveId") Long beehiveId)
    {
        BeehiveTemperature temperature = beehiveTemperatureService.selectLatestTemperature(apiaryId, beehiveId);
        return success(temperature);
    }
}
