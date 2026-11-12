# MyBatis 底层练习工程(mybatis)

> **这是能力证明,不是简历项目。**
> 简历项目是 [`springmvc-project`](https://github.com/lhccccc66/springmvc-project)(进销存的技术底座)。
> 这个仓库的意义在于:**证明我不是只会用 MyBatis-Plus 的封装,而是懂它底下的 XML 到底怎么拼 SQL。**

## 为什么要有这个仓库

MyBatis-Plus 帮我们省掉了大量 XML,但面试官问起「`BaseMapper` 底下是怎么生成 SQL 的」「动态标签 `<where>` / `<set>` 到底做了什么」「`#{}` 和 `${}` 差在哪」——**答不上来就等于没学过 ORM。**

所以这个仓库用**手写 XML** 把 MyBatis 的核心能力全部过一遍。

## 覆盖的知识点

| 能力 | 实现 |
|---|---|
| Mapper 接口与 XML 绑定 | `namespace` = 接口全类名,`id` = 方法名 |
| 参数占位符 | `#{}` 预编译占位符(防 SQL 注入) |
| 动态 SQL | `<where>` 自动去掉多余的 `AND` / `OR`、`<if>` 条件拼接 |
| 条件组合查询 | 姓名模糊 + 薪资下限,按需拼条件 |
| 结果映射 | `resultType` 指定实体类全类名 |
| 列名映射 | `tiger_id tigerId` 起别名 / 驼峰自动映射 |
| 参数绑定 | `parameterType` 显式声明 |
| 注解 | `@Mapper` 让 Spring Boot 自动创建代理实现 |

## 目录

```
src/main/java/com/atguigu/mybatis/
├── MyBatisApplication.java        启动类
├── entities/Tiger.java            实体
└── mapper/TigerMapper.java        Mapper 接口(全方法带 Javadoc)

src/main/resources/
├── application.properties          数据源 + mapper-locations + 驼峰映射 + SQL 日志
└── mapper/TigerMapper.xml         手写 XML(每个语句带注释)

src/test/java/com/atguigu/mybatis/
├── TigerMapperTest.java           CRUD + 条件查询单元测试
├── MyBatisTest.java
└── MyBatisApplicationTests.java
```

## 运行

```bash
# 需要先建库 db_demo_crud 和 t_tiger 表
mvn spring-boot:run
mvn test
```

## 面试可讲

1. **`#{}` vs `${}`**
   `#{}` 编译成 `?` 占位符,值作为参数传进 JDBC,天然防注入;`${}` 是字符串直接拼接进 SQL,**会注入**,只能用于表名 / 排序字段这类白名单可控的场景

2. **`<where>` / `<set>` 的作用**
   `<where>` 会自动剥掉开头多余的 `AND` / `OR`;`<set>` 会自动去掉结尾多余的逗号。**没有这两个标签,动态拼接 SQL 就会因为多一个逗号直接语法报错**

3. **Mapper 接口没有实现类,是谁在干活?**
   `@Mapper` + Spring Boot 的 `MapperScannerConfigurer` 扫描接口 → 生成动态代理 → 代理内部把方法调用翻译成「找 namespace + id 对应的 SQL → 绑参 → 执行 → 映射结果」

4. **`parameterType` / `resultType` 的作用**
   前者告诉 MyBatis 怎么从参数对象里取 `#{xxx}`;后者告诉它每行结果映射成什么类型的对象

5. **为什么用 `useGeneratedKeys="true" keyProperty="id"`?**
   数据库自增主键在 INSERT 之后才有,MyBatis 需要用这个配置把生成的主键**回填**到实体的 `id` 属性上,否则 insert 完拿不到 id
