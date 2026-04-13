package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.beekeeperMapper;
import com.ruoyi.system.domain.beekeeper;
import com.ruoyi.system.service.IbeekeeperService;

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
        return beekeeperMapper.insertbeekeeper(beekeeper);
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
