package com.ruoyi.apiary.mapper;

import java.util.List;
import com.ruoyi.apiary.domain.Apiary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
 * 蜂厂管理Mapper接口
 * 
 * @author ruoyi
 * @date 2026-04-12
 */
public interface ApiaryMapper 
{
    /**
     * 查询蜂厂管理
     * 
     * @param apiaryId 蜂厂管理主键
     * @return 蜂厂管理
     */
    public Apiary selectApiaryByApiaryId(Long apiaryId);

    /**
     * 查询蜂厂管理列表
     * 
     * @param apiary 蜂厂管理
     * @return 蜂厂管理集合
     */
    public List<Apiary> selectApiaryList(Apiary apiary);

    /**
     * 新增蜂厂管理
     * 
     * @param apiary 蜂厂管理
     * @return 结果
     */
    public int insertApiary(Apiary apiary);

    /**
     * 修改蜂厂管理
     * 
     * @param apiary 蜂厂管理
     * @return 结果
     */
    public int updateApiary(Apiary apiary);

    /**
     * 删除蜂厂管理
     * 
     * @param apiaryId 蜂厂管理主键
     * @return 结果
     */
    public int deleteApiaryByApiaryId(Long apiaryId);

    /**
     * 批量删除蜂厂管理
     * 
     * @param apiaryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApiaryByApiaryIds(Long[] apiaryIds);
}
