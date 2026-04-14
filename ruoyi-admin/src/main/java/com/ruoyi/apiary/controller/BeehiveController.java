package com.ruoyi.system.controller;

import java.util.List;
import java.util.Arrays;
import java.util.Base64;
import java.security.MessageDigest;
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
import com.ruoyi.system.domain.BeehiveBindRequest;
import com.ruoyi.system.domain.Equipment;
import com.ruoyi.system.service.IBeehiveService;
import com.ruoyi.system.service.IEquipmentService;
import com.ruoyi.apiary.service.IApiaryService;
import com.ruoyi.apiary.domain.Apiary;
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

    @Autowired
    private IEquipmentService equipmentService;

    @Autowired
    private IApiaryService apiaryService;

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

    /**
     * 通过二维码绑定蜂箱
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:bind')")
    @Log(title = "蜂箱绑定", businessType = BusinessType.INSERT)
    @PostMapping("/bind")
    public AjaxResult bindByQrCode(@RequestBody BeehiveBindRequest request)
    {
        String qrCode = request.getQrCode();
        System.out.println("=== Bind QR Code ===");
        System.out.println("Received qrCode: " + qrCode);

        if (qrCode == null || qrCode.isEmpty())
        {
            return error("二维码数据不能为空");
        }

        // 方法1: 直接用原始文本查询
        Equipment equipment = equipmentService.selectEquipmentByPower(qrCode);
        System.out.println("Method 1 (raw): " + (equipment != null));

        // 方法2: 如果方法1失败，计算MD5后再查
        if (equipment == null)
        {
            try
            {
                String[] parts = qrCode.split("_");
                if (parts.length >= 2)
                {
                    Long equipmentId = Long.parseLong(parts[0]);
                    String fixedSecret = "BEE_HIVE_SECRET_2026";
                    String dataToHash = equipmentId + "_" + fixedSecret;
                    String encryptedPower = md5(dataToHash);
                    System.out.println("Method 2 encrypted: " + encryptedPower);
                    equipment = equipmentService.selectEquipmentByPower(encryptedPower);
                    System.out.println("Method 2 (MD5): " + (equipment != null));
                }
            }
            catch (Exception e)
            {
                System.out.println("MD5 calc error: " + e.getMessage());
            }
        }

        if (equipment == null)
        {
            return error("无效的二维码数据");
        }

        // 检查蜂箱状态：0-未激活，1-已激活（这里简化为检查设备是否已绑定蜂箱）
        if (equipment.getBeehiveId() != null)
        {
            return error("设备已绑定蜂箱");
        }

        // 返回设备信息让前端打开添加弹窗
        return success(equipment);
    }

    /**
     * 验证二维码并获取设备信息（用于扫码后验证设备）
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:bind')")
    @GetMapping("/verify/{qrCode}")
    public AjaxResult verifyQrCode(@PathVariable String qrCode)
    {
        System.out.println("=== Verify QR Code ===");
        System.out.println("Received qrCode: " + qrCode);

        if (qrCode == null || qrCode.isEmpty())
        {
            return error("二维码数据不能为空");
        }

        // 方法1: 直接用原始文本查询
        Equipment equipment = equipmentService.selectEquipmentByPower(qrCode);
        System.out.println("Method 1 (raw): " + (equipment != null));

        // 方法2: 如果方法1失败，计算MD5后再查
        if (equipment == null)
        {
            try
            {
                String[] parts = qrCode.split("_");
                if (parts.length >= 2)
                {
                    Long equipmentId = Long.parseLong(parts[0]);
                    String fixedSecret = "BEE_HIVE_SECRET_2026";
                    String dataToHash = equipmentId + "_" + fixedSecret;
                    String encryptedPower = md5(dataToHash);
                    System.out.println("Method 2 encrypted: " + encryptedPower);
                    equipment = equipmentService.selectEquipmentByPower(encryptedPower);
                    System.out.println("Method 2 (MD5): " + (equipment != null));
                }
            }
            catch (Exception e)
            {
                System.out.println("MD5 calc error: " + e.getMessage());
            }
        }

        if (equipment == null)
        {
            return error("暂无");
        }

        // 已关联蜂箱的设备不能重复绑定
        if (equipment.getBeehiveId() != null)
        {
            return error("设备已绑定蜂箱");
        }

        // 返回设备信息
        return success(equipment);
    }

    /**
     * 激活蜂箱（用户确认保存后调用）
     * 同时创建蜂箱并关联设备
     */
    @PreAuthorize("@ss.hasPermi('system:beehive:add')")
    @Log(title = "蜂箱激活", businessType = BusinessType.INSERT)
    @PostMapping("/activate")
    public AjaxResult activate(@RequestBody Beehive beehive)
    {
        // 1. 创建蜂箱（不传location，由后端自动获取蜂厂位置）
        beehive.setLocation(null);
        // 设置蜂箱状态为已激活(1)
        beehive.setBeehiveStatus(1);
        int result = beehiveService.insertBeehive(beehive);

        // 2. 获取蜂厂位置并更新蜂箱位置
        if (result > 0 && beehive.getApiaryId() != null)
        {
            Apiary apiary = apiaryService.selectApiaryByApiaryId(beehive.getApiaryId());
            System.out.println("=== Get Apiary Location ===");
            System.out.println("Apiary: " + apiary);
            System.out.println("Apiary location from DB: " + (apiary != null ? apiary.getLocation() : "null"));
            if (apiary != null && apiary.getLocation() != null)
            {
                System.out.println("Setting beehive location to: " + apiary.getLocation());
                beehive.setLocation(apiary.getLocation());
                beehiveService.updateBeehive(beehive);
            }
        }

        // 3. 通过power查找设备并更新
        if (result > 0 && beehive.getPower() != null)
        {
            System.out.println("=== Activate: Looking for equipment ===");
            System.out.println("Raw power from request: " + beehive.getPower());

            Equipment equipment = equipmentService.selectEquipmentByPower(beehive.getPower());
            System.out.println("Method 1 (raw): " + (equipment != null));

            if (equipment == null)
            {
                // 尝试MD5解密
                try
                {
                    String[] parts = beehive.getPower().split("_");
                    if (parts.length >= 2)
                    {
                        Long equipmentId = Long.parseLong(parts[0]);
                        String fixedSecret = "BEE_HIVE_SECRET_2026";
                        String dataToHash = equipmentId + "_" + fixedSecret;
                        String encryptedPower = md5(dataToHash);
                        System.out.println("Calculated MD5: " + encryptedPower);
                        equipment = equipmentService.selectEquipmentByPower(encryptedPower);
                        System.out.println("Method 2 (MD5): " + (equipment != null));
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Activate error: " + e.getMessage());
                }
            }

            if (equipment != null)
            {
                // 检查设备是否已绑定其他蜂箱
                if (equipment.getBeehiveId() != null && !equipment.getBeehiveId().equals(beehive.getBeehiveId()))
                {
                    return error("该设备已绑定其他蜂箱");
                }
                System.out.println("Found equipment: " + equipment.getEquipmentId());
                System.out.println("beehiveId to set: " + beehive.getBeehiveId());
                System.out.println("apiaryId to set: " + beehive.getApiaryId());
                equipment.setBeehiveId(beehive.getBeehiveId());
                equipment.setApiaryId(beehive.getApiaryId());
                equipment.setDeviceStatus(1);
                equipmentService.updateEquipment(equipment);
                System.out.println("Equipment updated with deviceStatus=1, beehiveId=" + beehive.getBeehiveId());
            }
            else
            {
                System.out.println("Equipment NOT found!");
            }
        }

        return toAjax(result);
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
}