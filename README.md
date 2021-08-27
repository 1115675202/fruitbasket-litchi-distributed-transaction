# 分布式事务实现例子

## 模块结构

├── fruitbasket-litchi-code-generator           mybatis-plus 自动生成代码
├── fruitbasket-litchi-eureka                   注册中心
├── /txlcn
├── fruitbasket-litchi-txlcn-a               参与服务a(TC)
├── fruitbasket-litchi-txlcn-b               参与服务b(TC)
├── fruitbasket-litchi-txlcn-c               参与服务c(TC)
├── fruitbasket-litchi-txlcn-manager         事务协调服务(TM)
├── seata

## txlcn 测试

### 准备工作
 - 启动 MySQL Redis
 - 创建数据库 tx-manager txlcn-a txlcn-b txlcn-c
 - 在库 tx-manager 中执行 t_tx_exception.sql 创建表
 - 在库 txlcn-a txlcn-b 中执行 business_data.sql 创建表
 - 修改 a b c manager 四个服务的 MySQL Redis 配置
### 测试
 - 依次启动 eureka manager b c
 - 执行 a 服务 /test 目录下的测试类方法 LcnControllerTest TccControllerTest

 



