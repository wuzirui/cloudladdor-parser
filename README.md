北京理工大学编译原理课程实验 5：语法分析

在本项目中，我们使用Java语言为cloud-laddor语言编写语法分析器并输出dot文件以便于可视化语法分析树.

![使用Graphviz工具可视化语法树](https://user-images.githubusercontent.com/7344146/167170511-86aaa778-5953-4150-845c-581371c413de.png)
上图为示例语法分析树.

`src/java`中 ast 包中存放了所有AST节点的文件，`Main.java`为主程序, `Parser.java`为语法分析器代码
`src/resources`中存放了示例代码、Antlr源文件等资源文件
