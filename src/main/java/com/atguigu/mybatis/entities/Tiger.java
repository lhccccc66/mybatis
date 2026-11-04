package com.atguigu.mybatis.entities;

import lombok.Data;

/**
 * 老虎实体类：对应数据库t_tiger表
 * @Data：Lombok注解，自动生成getter、setter、toString、equals/hashCode等方法
 * 核心规则：数据库下划线命名（tiger_id）→ Java驼峰命名（tigerId），MyBatis默认支持该映射
 */
@Data
public class Tiger {
    private Integer tigerId;    // 对应tiger_id（主键）
    private String tigerName;   // 对应tiger_name
    private Integer tigerAge;   // 对应tiger_age
    private Double tigerSalary; // 对应tiger_salary
}