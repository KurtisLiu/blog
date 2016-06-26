<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>发布话题</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css">
</head>
<body>
    <#include "./components/header.ftl">

    <div class="body-wrapper">
        <form action="/topics" method="POST">
            <div class="ctrl-group">
                <label for="tag">选择板块</label>
                <select name="tag.id">
                <#list tags as tag>
                    <option value="${tag.id}">${tag.name}</option>
                </#list>
                </select>
            </div>

            <div class="ctrl-group">
                <input type="text" placeholder="标题" name="title" />
            </div>

            <div class="ctrl-group">
                <textarea name="content"></textarea>
            </div>

            <div class="ctrl-group">
                <input type="submit" value="提交" />
            </div>

        </form>
    </div>

</body>
</html>