# java项目自开发通用工具

## 本机打包部署

> 在pom.xml文件所在目录下执行以下命令

```shell
mvn install:install-file -Dfile="xxx.jar" -DgroupId=top.yinaicheng -DartifactId=java_toolkit_yinaicheng -Dversion=1.0-SNAPSHOT -Dpackaging=jar
```