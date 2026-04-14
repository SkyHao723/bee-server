package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.mapper.beekeeperMapper;
import com.ruoyi.system.domain.beekeeper;
import com.ruoyi.system.service.IbeekeeperService;
import com.ruoyi.system.service.ISysRoleService;

/**
 * 蜂农信息Service业务层处理
 *
 * @author ruoyi
 * @date 2026-04-12
 */
@Service
public class beekeeperServiceImpl implements IbeekeeperService
{
    @Autowired
    private beekeeperMapper beekeeperMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询蜂农信息
     * 
     * @param userId 蜂农信息主键
     * @return 蜂农信息
     */
    @Override
    public beekeeper selectbeekeeperByUserId(Long userId)
    {
        return beekeeperMapper.selectbeekeeperByUserId(userId);
    }

    /**
     * 查询蜂农信息列表
     * 
     * @param beekeeper 蜂农信息
     * @return 蜂农信息
     */
    @Override
    public List<beekeeper> selectbeekeeperList(beekeeper beekeeper)
    {
        // 如果当前用户是蜂厂管理员，则只显示该蜂厂管理员创建的蜂农
        if (SecurityUtils.isApiaryAdmin())
        {
            Long currentUserId = SecurityUtils.getUserId();
            SysUser currentUser = userMapper.selectUserById(currentUserId);
            if (currentUser != null && currentUser.getApiaryId() != null)
            {
                beekeeper.setApiaryId(currentUser.getApiaryId());
            }
        }
        return beekeeperMapper.selectbeekeeperList(beekeeper);
    }

    /**
     * 新增蜂农信息
     * 
     * @param beekeeper 蜂农信息
     * @return 结果
     */
    @Override
    public int insertbeekeeper(beekeeper beekeeper)
    {
        beekeeper.setCreateTime(DateUtils.getNowDate());
        // 对密码进行BCrypt加密
        if (beekeeper.getPassword() != null && !beekeeper.getPassword().startsWith("$2a$"))
        {
            beekeeper.setPassword(SecurityUtils.encryptPassword(beekeeper.getPassword()));
        }
        // 如果当前用户是蜂厂管理员，则自动设置该蜂厂管理员的apiary_id
        if (SecurityUtils.isApiaryAdmin())
        {
            Long currentUserId = SecurityUtils.getUserId();
            SysUser currentUser = userMapper.selectUserById(currentUserId);
            if (currentUser != null && currentUser.getApiaryId() != null)
            {
                beekeeper.setApiaryId(currentUser.getApiaryId());
            }
        }
        // 先插入用户，获取userId
        int rows = beekeeperMapper.insertbeekeeper(beekeeper);
        if (rows > 0 && beekeeper.getUserId() != null)
        {
            // 查询beekeeper角色的ID
            com.ruoyi.common.core.domain.entity.SysRole role = roleService.selectRoleByRoleKey("beekeeper");
            if (role != null)
            {
                // 插入用户角色关联
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(beekeeper.getUserId());
                userRole.setRoleId(role.getRoleId());
                List<SysUserRole> list = new ArrayList<>();
                list.add(userRole);
                userRoleMapper.batchUserRole(list);
            }
        }
        return rows;
    }

    /**
     * 修改蜂农信息
     * 
     * @param beekeeper 蜂农信息
     * @return 结果
     */
    @Override
    public int updatebeekeeper(beekeeper beekeeper)
    {
        beekeeper.setUpdateTime(DateUtils.getNowDate());
        return beekeeperMapper.updatebeekeeper(beekeeper);
    }

    /**
     * 批量删除蜂农信息
     * 
     * @param userIds 需要删除的蜂农信息主键
     * @return 结果
     */
    @Override
    public int deletebeekeeperByUserIds(Long[] userIds)
    {
        return beekeeperMapper.deletebeekeeperByUserIds(userIds);
    }

    /**
     * 删除蜂农信息信息
     * 
     * @param userId 蜂农信息主键
     * @return 结果
     */
    @Override
    public int deletebeekeeperByUserId(Long userId)
    {
        return beekeeperMapper.deletebeekeeperByUserId(userId);
    }
}
