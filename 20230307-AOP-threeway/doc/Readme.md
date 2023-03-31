

## AOP的使用
- 目标 
  1. 不使用spring，能否使用 AOP 代理
  2. 使用 AOP 的最少的代码
- 时间：2023.03.07
- 作者：吴智豪
- 用途：模板
---


##工程目录
- main java
  - com.tyy
  - main
    - aop
      - LogAOP (切面类)
    - beaop
      - SayHello (被切面插入的类)

## 结论
- 不使用spring 可以实现 AOP，但是过程复杂一些
- 借助spring使用AOP，需要以下几步
  1. 定义执行方法，并在方法上通过AspectJ的注解告诉Spring应该在何处调用此方法；
  2. 标记`@Component`和`@Aspect`；
  3. 在`@Configuration`类上标注`@EnableAspectJAutoProxy`。