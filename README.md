# java项目自开发通用工具

## 本机打包部署

> 在pom.xml文件所在目录下执行以下命令

```shell
mvn install:install-file -Dfile=.\target\java_toolkit_yinaicheng-1.0-SNAPSHOT.jar -DgroupId=top.yinaicheng -DartifactId=java_toolkit_yinaicheng -Dversion=1.0-SNAPSHOT -Dpackaging=jar
```

> 其他项目引入该Maven依赖方式

```xml
<dependency>
    <groupId>top.yinaicheng</groupId>
    <artifactId>java_toolkit_yinaicheng</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```