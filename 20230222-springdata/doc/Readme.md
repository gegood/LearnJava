
## 说明
- 目标：
  - 实现 Hibernate 的 crud 操作
  - 实现 Springdata JPA 的 crud 操作 
- 时间：2023.02.22
- 作者：吴智豪
- 用途：模板
---
## 工程结构

### 直接使用 Hibernate
- 01-jpa-hibernate
  - java
    - com.tyy.pojo
      - Customer (实体类)
  - resources
    - hibernate.cfg.xml (Hibernate配置文件)
- test
  - java
    - com.tyy.pojo
      - HibernateTest (测试文件，使用crud操作)
---
### 直接使用 JPA
- 01-jpa-hibernate
  - java
    - com.tyy.pojo
      - Customer (实体类)
  - resources
    - META-INF
      - persistence.xml (JPA配置文件)
- test
  - java
    - com.tyy.pojo
      - JPATest (测试文件，使用crud操作)
---
### 使用 Springdata JPA
- 02-springdata-jpa
  - java
    - com.tyy
      - pojo
        - Customer (实体类)
      - config
        - SpringDataJpaConfig (配置文件，配置 beans)
      - repositories
        - CustomerPagingAndSortRepository (接口继承JPA接口)
        - CustomerRepository (接口继承JPA接口)
  - resources
    - spring.xml (配置文件，配置 beans ，与 SpringDataJpaConfig 二选一即可)
- test
  - java
    - com.tyy.test
      - SpringdataJpaTest (测试文件，使用crud操作)
      - SpringdataJpaPagingAndSortTest (测试文件，使用crud操作)