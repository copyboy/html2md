## blog-exporte-quick

- 已支持 CSDN,CNBLOG,微信公众号文章
- CNBLOG 自定义css样式较多,如果md格式有误,大多数是代码块,请使用html格式,或直接复制页面
- 支持 CSDN 分类专栏文章下载,格式如 https://blog.csdn.net/qq_37771475/category_8584136.html

### 扩展其它站点支持

- 在 HostEnums 中添加站点枚举值,并获取页面内容的 id 值

----------

> 暂时不支持的站点

- 掘金 : 前后端分离,正文内容需要等待页面全部加载完成
- 简书 : class 元素定位正文内容
- Bilibili : 前后端分离，需要处理json返回值



### 项目启动
启动项目之后，在浏览器输入网址`http://localhost:8090/tool/html2md`

工具地址：
https://api.zhangqingdong.cn/tool/html2md

![启动页面](http://file.zhangqingdong.cn/image-20210917182716129.png "启动页面")

### 参考项目

[blog-export-quick](https://github.com/vector4wang/blog-export-quick)

[html2file](https://github.com/petterobam/html2file)

[jHTML2Md](https://github.com/nico2sh/jHTML2Md)
