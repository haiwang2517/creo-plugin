# CREO PLUGIN DEMO
## CREO 4.0 development document
```text
 <local_install_path>\Creo 4.0\F000\Common Files\otk_java_doc\otk_javaug.pdf
```

## Code structure

```text
+---gradle   
|   \---wrapper
+---libs                       # 依赖jar
+---plugin                     # 插件编译结果，含text、resource等配置； 注意dat文件中的路径要改为绝对路径
|   \---text
|       \---resource
\---src                        # 源代码
    +---main
    |   +---java
    |   |   \---com
    |   |       \---kenstudio
    |   |           +---component
    |   |           +---config
    |   |           \---listener
    |   \---resources
    \---test
        +---java
        \---resources
```
>注意事项： dat文件中的路径要改为绝对路径



## Creo add plugin step
### 加载插件
```text
  导航栏：
    工具 ->  辅助应用程序 -> 注册 (选择编译目录中的 dat文件) -> 启动
```

### 界面配置插件
```text
  导航栏：
    文件 -> 选项 -> 自定义 -> 功能区 ，自定义添加导航栏配置。
```


