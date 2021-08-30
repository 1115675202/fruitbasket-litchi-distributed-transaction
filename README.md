# 分布式事务实现例子

## 模块结构
``` lua
├── fruitbasket-litchi-code-generator           mybatis-plus 自动生成代码
├── fruitbasket-litchi-eureka                   注册中心
├── txlc/
├   ├── fruitbasket-litchi-txlcn-a               参与服务a(TC)
├   ├── fruitbasket-litchi-txlcn-b               参与服务b(TC)
├   ├── fruitbasket-litchi-txlcn-c               参与服务c(TC)
├   └── fruitbasket-litchi-txlcn-manager         事务协调服务(TM)
├── seata
├   ├── fruitbasket-litchi-seata-account         参与服务-扣款(RM)
├   ├── fruitbasket-litchi-seata-business        参与服务-事务发起(TM)
├   ├── fruitbasket-litchi-seata-order           参与服务-订单(RM)
├   └── fruitbasket-litchi-seata-storage         参与服务-库存(RM)
├   └── seata-server/                            事务协调者(TM)

```

## txlcn 测试步骤

### 准备工作
 - 启动 MySQL Redis
 - 创建数据库 tx-manager txlcn-a txlcn-b txlcn-c
 - 在库 tx-manager 中执行 t_tx_exception.sql 创建表
 - 在库 txlcn-a txlcn-b 中执行 business_data.sql 创建表
 - 修改 a b c manager 四个服务的 MySQL Redis 链接
 - tx-manager 高可用，部署多个然后参与者配置tx-manager 地址用逗号分隔
 
### 测试
 - 依次启动 eureka manager b c
 - 执行 a 服务 /test 目录下的测试类方法 LcnControllerTest TccControllerTest

 ## seata 测试步骤
 
 ### 准备工作
 - 启动 MySQL Redis
 - 创建数据库 seata seata-sample
 - 在库 seata 中执行 seata_server.sql 创建表
 - 在库 seata-sample 中执行 seata_sample.sql 创建表
 
 - 修改 account business order storage 四个服务的 MySQL 链接
 - 修改 seata/seata-server/conf/file.conf MySQL 链接

 ### 测试
 - 启动 eureka
 - 使用 seata/seata-server/bin 下脚本启动 seata_server
 - 启动 account business order storage 四个服务
 - 测试 AT http://localhost:9091/purchase/commit http://localhost:9091/purchase/rollback
 - 测试 TCC http://localhost:9091/tcc/commit http://localhost:9091/tcc/rollback



