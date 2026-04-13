package com.ruoyi.system.controller;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Beehive;
import com.ruoyi.system.service.IBeehiveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蜂箱管理Controller
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
@RestController
@RequestMapping("/system/beehive")
public class BeehiveController extends BaseController
{
    @Autowired
    private IBeehiveService beehiveService;

    /**
     * 查询蜂箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:list')")
    @GetMapping("/list")
    public TableDataInfo list(Beehive beehive)
    {
        startPage();
        List<Beehive> list = beehiveService.selectBeehiveList(beehive);
        return getDataTable(list);
    }

    /**
     * 导出蜂箱管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:export')")
    @Log(title = "蜂箱管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Beehive beehive)
    {
        List<Beehive> list = beehiveService.selectBeehiveList(beehive);
        ExcelUtil<Beehive> util = new ExcelUtil<Beehive>(Beehive.class);
        util.exportExcel(response, list, "蜂箱管理数据");
    }

    /**
     * 获取蜂箱管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:query')")
    @GetMapping(value = "/{beehiveId}")
    public AjaxResult getInfo(@PathVariable("beehiveId") Long beehiveId)
    {
        return success(beehiveService.selectBeehiveByBeehiveId(beehiveId));
    }

    /**
     * 新增蜂箱管理
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:add')")
    @Log(title = "蜂箱管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Beehive beehive)
    {
        return toAjax(beehiveService.insertBeehive(beehive));
    }

    /**
     * 修改蜂箱管理
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:edit')")
    @Log(title = "蜂箱管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Beehive beehive)
    {
        return toAjax(beehiveService.updateBeehive(beehive));
    }

    /**
     * 删除蜂箱管理
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:remove')")
    @Log(title = "蜂箱管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{beehiveIds}")
    public AjaxResult remove(@PathVariable Long[] beehiveIds)
    {
        return toAjax(beehiveService.deleteBeehiveByBeehiveIds(beehiveIds));
    }
}
