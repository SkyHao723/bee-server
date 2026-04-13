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
import com.ruoyi.system.domain.beekeeper;
import com.ruoyi.system.service.IbeekeeperService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 蜂农信息Controller
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
@RestController
@RequestMapping("/beekeeper/beekeeper")
public class beekeeperController extends BaseController
{
    @Autowired
    private IbeekeeperService beekeeperService;

    /**
     * 查询蜂农信息列表
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:list')")
    @GetMapping("/list")
    public TableDataInfo list(beekeeper beekeeper)
    {
        startPage();
        List<beekeeper> list = beekeeperService.selectbeekeeperList(beekeeper);
        return getDataTable(list);
    }

    /**
     * 导出蜂农信息列表
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:export')")
    @Log(title = "蜂农信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, beekeeper beekeeper)
    {
        List<beekeeper> list = beekeeperService.selectbeekeeperList(beekeeper);
        ExcelUtil<beekeeper> util = new ExcelUtil<beekeeper>(beekeeper.class);
        util.exportExcel(response, list, "蜂农信息数据");
    }

    /**
     * 获取蜂农信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(beekeeperService.selectbeekeeperByUserId(userId));
    }

    /**
     * 新增蜂农信息
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:add')")
    @Log(title = "蜂农信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody beekeeper beekeeper)
    {
        return toAjax(beekeeperService.insertbeekeeper(beekeeper));
    }

    /**
     * 修改蜂农信息
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:edit')")
    @Log(title = "蜂农信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody beekeeper beekeeper)
    {
        return toAjax(beekeeperService.updatebeekeeper(beekeeper));
    }

    /**
     * 删除蜂农信息
     */
    @PreAuthorize("@ss.hasPermi('beekeeper:beekeeper:remove')")
    @Log(title = "蜂农信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(beekeeperService.deletebeekeeperByUserIds(userIds));
    }
}
