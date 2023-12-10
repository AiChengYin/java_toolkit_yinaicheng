# java项目自开发通用工具

## 本机打包部署

> 在pom.xml文件所在目录下执行以下命令

install命令的作用是将项目构建后的结果安装到本地仓库或远程仓库中，以便供其他项目依赖。它会将项目编译并打包，并将打包结果安装到本地仓库（默认是`.m2/repository`目录）或远程仓库（如果配置了相关信息）。这样其他项目就可以通过声明依赖来使用该项目的构件。

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