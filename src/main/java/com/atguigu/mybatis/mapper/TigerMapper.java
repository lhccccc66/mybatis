package com.atguigu.mybatis.mapper;


import org.apache.ibatis.annotations.Mapper;
import com.atguigu.mybatis.entities.Tiger;

import java.util.List;

/**
 * Tiger数据访问接口（DAO层）
 * @Mapper：标识为 MyBatis 的 Mapper 接口，Spring Boot 自动创建代理实现类
 */
@Mapper
public interface TigerMapper {
    /**
     * 新增老虎信息
     * @param tiger 新增的老虎对象（包含name/age/salary，无需传id，数据库自增）
     * @return 受影响的行数（新增成功返回1，失败返回0）
     */
    int insertTiger(Tiger tiger);
    /**
     * 根据ID删除老虎信息
     * @param tigerId 老虎ID
     * @return 受影响的行数（删除成功返回1）
     */
    int deleteTigerById(Integer tigerId);
    /**
     * 根据ID更新老虎信息
     * @param tiger 要更新的老虎对象（必须包含tigerId，以及要修改的属性值）
     * @return 受影响的行数（更新成功返回1）
     */
    int updateTigerById(Tiger tiger);
    /**
     * 根据ID查询老虎详情
     * @param tigerId 老虎ID
     * @return 对应的老虎对象（无数据返回null）
     */
    Tiger selectTigerById(Integer tigerId);
    /**
     * 查询所有老虎信息
     * @return 老虎列表（无数据返回空列表，不会返回null）
     */
    List<Tiger> selectTigerList();

    List<Tiger>selectTigerByCondition(Tiger tiger);
}

