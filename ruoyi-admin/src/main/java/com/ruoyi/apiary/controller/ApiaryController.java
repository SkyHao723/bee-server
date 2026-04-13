package com.ruoyi.apiary.controller;

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
import com.ruoyi.apiary.domain.Apiary;
import com.ruoyi.apiary.service.IApiaryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蜂厂管理Controller
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
@RestController
@RequestMapping("/apiary/apiary")
public class ApiaryController extends BaseController
{
    @Autowired
    private IApiaryService apiaryService;

    /**
     * 查询蜂厂管理列表
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:list')")
    @GetMapping("/list")
    public TableDataInfo list(Apiary apiary)
    {
        startPage();
        List<Apiary> list = apiaryService.selectApiaryList(apiary);
        return getDataTable(list);
    }

    /**
     * 导出蜂厂管理列表
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:export')")
    @Log(title = "蜂厂管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Apiary apiary)
    {
        List<Apiary> list = apiaryService.selectApiaryList(apiary);
        ExcelUtil<Apiary> util = new ExcelUtil<Apiary>(Apiary.class);
        util.exportExcel(response, list, "蜂厂管理数据");
    }

    /**
     * 获取蜂厂管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:query')")
    @GetMapping(value = "/{apiaryId}")
    public AjaxResult getInfo(@PathVariable("apiaryId") Long apiaryId)
    {
        return success(apiaryService.selectApiaryByApiaryId(apiaryId));
    }

    /**
     * 新增蜂厂管理
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:add')")
    @Log(title = "蜂厂管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Apiary apiary)
    {
        return toAjax(apiaryService.insertApiary(apiary));
    }

    /**
     * 修改蜂厂管理
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:edit')")
    @Log(title = "蜂厂管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Apiary apiary)
    {
        return toAjax(apiaryService.updateApiary(apiary));
    }

    /**
     * 删除蜂厂管理
     */
    @PreAuthorize("@ss.hasPermi('apiary:apiary:remove')")
    @Log(title = "蜂厂管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{apiaryIds}")
    public AjaxResult remove(@PathVariable Long[] apiaryIds)
    {
        return toAjax(apiaryService.deleteApiaryByApiaryIds(apiaryIds));
    }
}
