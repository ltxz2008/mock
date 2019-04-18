该Mock框架支持Rest和Dubbo

1.crtMock  为dubbo Mock方案之一，需要修改实现的源码，可实现在consumer或者provider  Mock

2.dubbo-mock  为dubbo Mock方案之二，提供控制台界面配置响应结果，比较方便，修改响应结果不用重启服务，依赖zookeeper

3.mockFramework  为Rest Mock,需要安装nodejs和Anyproxy(阿里)环境，响应结果可以是json,字符串，xml
