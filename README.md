# bedpod

##### 部分内容参考sofa代码。感谢sofa团队的贡献

## 背景
当前很多IT企业都使用中台化的思路去解决企业内部多业务的发展问题。虽然使用微服务和DDD的思想对系统做了很多领域的划分，但是在一个领域内也面临
如何支持多业务的发展。从单个领域来看主要面临如下问题
* 一个领域内也要支持多个业务的发展，比如一个公司内的支付域，需要承接整个公司所有业务的支付需求；另外支付域内的渠道接入也需要对接不同的支付
公司或者银行。
* 新业务接入的过程中，面临对系统各个流程的侵入。业务和业务之间代码耦合比较多，经常是 if..else..逻辑嵌套。系统维护性变差，也不利于新员工的
融入和成长。
* 创新业务接入时间长，而创新业务一般作为试点，都需要能够快速的利用之前的能力实现快速的迭代发布

从以上背景出发，本人考虑做一个基于SPI接口，插件式开发的框架，可以快速响应业务需求。

## 功能简介
### 面向SPI的可扩展机制
系统可以根据自己领域的边界和定位，定义SPI接口，使用 @Extensible 注解。接口的实现使用 @Extension 注解。系统根据 @Extension 注解中的
扩展点名称，就可以找到相应的扩展点。
### 扩展点隔离
每个扩展点的实现，可以收敛到一个jar包内，在resource根目录下添加 "META-INF/services/" + Extensible.file() 的文件，将SPI扩展点实现的
全路径写上，当使用扩展点时，框架就能加载进来。这样不同的业务，可以根据SPI在自己的jar包中做自己的业务逻辑，业务逻辑和系统领域逻辑隔离开，业
务和业务之间也隔离开。
### 能力发布与引用
每个扩展点jar包内可能使用系统的相关能力，比如缓存、数据库等等系统上的系统能力，在单个jar包内是无法使用的。所以基于以上需求，系统可以使用 
@AbilityService 注解发布一个能力，扩展点jar包内使用 @AbilityReference 引用系统发布的功能。这样系统也可以管理所有SPI插件使用的能力。

### sample
见https://github.com/shiwenwy/sample

## 未来规划
* 热部署
* 能力的filter机制
* 模块的类隔离机制
* 插件的可观测性
* 商业能力的插件化
