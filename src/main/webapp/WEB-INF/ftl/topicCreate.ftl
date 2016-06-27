<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>发布话题</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css">
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/topicCreate.css">
</head>
<body>
    <#include "./components/header.ftl">

    <div class="body-wrapper topic-creation">
        <div class="panel">
            <div class="panel-header"><a href="/" class="link">主页</a> / 发布话题</div>
            <div class="panel-body">
                <form action="/topic" method="POST">
                    <div class="form-group">
                        <label for="tag" class="form-label">选择板块:</label>
                        <select name="tag.id" class="tag">
                            <option value="">请选择</option>
                        <#list tags as tag>
                            <option value="${tag.id}">${tag.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="标题" name="title" class="title" />
                    </div>
                    <div class="form-group">
                        <textarea name="content" class="content"></textarea>
                    </div>

                    <div class="action-group">
                        <input type="submit" value="提交" class="submit" />
                    </div>
                </form>
            </div>
        </div>
    </div>

</body>
</html>