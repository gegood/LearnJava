## 说明
- 目标：
    - 实现 spring boot 集成 quartz
    - 实现定时任务
    - 验证spring boot 会自己拉起定时器，只需要写具体的 job，配置 jobdetail 和trigger就可以了
- 时间：2023.03.09
- 作者：吴智豪
- 用途：模板
---
## 工程结构
- java
    - com.tyy
        - DongAoJob(工作类，写具体的任务代码)
        - QuartzConfig(配置 quartz，写jobdetail 和 trigger)
        - Main(启动spring boot)
    - resources
        - application.properties (spring boot 配置文件，quartz 也在这里配置)