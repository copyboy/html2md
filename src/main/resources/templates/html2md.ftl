<#assign base=ctx.contextPath />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <base id="base" href="${base}">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.0.1/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h4 class="panel-title">
                    链接支持CSDN,CNBLOG,微信公众号,其他平台未测试,
                    CNBLOG自定义样式较多,如果MD格式有误,可以使用HTML导出,或者尝试直接复制页面可能得到MD文档
                </h4>
            </div>
            <div class="panel-body">
                <div class="form-group col-md-12">
                    <label class="sr-only" for="blogUrl">只支持CSDN</label>
                    <div class="input-group">
                        <div class="input-group-addon">文章链接</div>
                        <input type="text" class="form-control" id="blogUrl" placeholder="链接支持CSDN,CNBLOG,微信公众号，其他平台未测试">
                    </div>
                </div>
<#--                <div class="form-group col-md-10">-->
<#--                    <label>HTML源代码</label>-->
<#--                    <textarea class="form-control" rows="8" id="htmlString" style="resize:none"></textarea>-->
<#--                </div>-->
                <div class="form-group col-md-12 text-center">
                    <button type="submit" id="submitBtnMd" onclick="convert('md')" class="btn btn-large btn-primary">转换Markdown</button>
                    <button type="submit" id="submitBtnHtml" onclick="convert('html')" class="btn btn-large btn-primary">转换HTML</button>
                    <button type="submit" id="downloadBtnMd" onclick="download('md')" class="btn btn-large btn-success">下载Markdown</button>
                    <button type="submit" id="downloadBtnHtml" onclick="download('html')" class="btn btn-large btn-success">下载Html</button>
                </div>
                <div class="form-group col-md-12">
                    <label>HTML/Markdown格式 源代码</label>
                    <textarea class="form-control" rows="8" id="result" style="resize:none"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function convert(type) {
        var url = $("#blogUrl").val();
        var html = $("#htmlString").val();
        var base = document.getElementById("base").href
        var target = base.replace('html2md','convert');
        $.ajax({
            type: "POST",
            url: target,
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({url:url,html:html, type: type}),
            success: function(data){
                var result = data.data;
                $("#result").val(result);
            }
        });
    }
    function download(type) {
        var path = document.getElementById("base").href.replace('html2md','download');
        var url = $("#blogUrl").val();
        console.log(path+"?type="+type+"&&"+"url="+url);
        window.open(path+"?type="+type+"&&"+"url="+url);
    }
</script>
</body>
</html>