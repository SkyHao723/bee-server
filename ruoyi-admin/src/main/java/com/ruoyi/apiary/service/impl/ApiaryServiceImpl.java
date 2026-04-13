package com.ruoyi.apiary.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.apiary.mapper.ApiaryMapper;
import com.ruoyi.apiary.domain.Apiary;
import com.ruoyi.apiary.service.IApiaryService;

/**
 * 蜂厂管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
@Service
public class ApiaryServiceImpl implements IApiaryService 
{
    @Autowired
    private ApiaryMapper apiaryMapper;

    /**
     * 查询蜂厂管理
     * 
     * @param apiaryId 蜂厂管理主键
     * @return 蜂厂管理
     */
    @Override
    public Apiary selectApiaryByApiaryId(Long apiaryId)
    {
        return apiaryMapper.selectApiaryByApiaryId(apiaryId);
    }

    /**
     * 查询蜂厂管理列表
     * 
     * @param apiary 蜂厂管理
     * @return 蜂厂管理
     */
    @Override
    public List<Apiary> selectApiaryList(Apiary apiary)
    {
        return apiaryMapper.selectApiaryList(apiary);
    }

    /**
     * 新增蜂厂管理
     * 
     * @param apiary 蜂厂管理
     * @return 结果
     */
    @Override
    public int insertApiary(Apiary apiary)
    {
        return apiaryMapper.insertApiary(apiary);
    }

    /**
     * 修改蜂厂管理
     * 
     * @param apiary 蜂厂管理
     * @return 结果
     */
    @Override
    public int updateApiary(Apiary apiary)
    {
        return apiaryMapper.updateApiary(apiary);
    }

    /**
     * 批量删除蜂厂管理
     * 
     * @param apiaryIds 需要删除的蜂厂管理主键
     * @return 结果
     */
    @Override
    public int deleteApiaryByApiaryIds(Long[] apiaryIds)
    {
        return apiaryMapper.deleteApiaryByApiaryIds(apiaryIds);
    }

    /**
     * 删除蜂厂管理信息
     * 
     * @param apiaryId 蜂厂管理主键
     * @return 结果
     */
    @Override
    public int deleteApiaryByApiaryId(Long apiaryId)
    {
        return apiaryMapper.deleteApiaryByApiaryId(apiaryId);
    }
}
