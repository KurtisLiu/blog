<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css" />
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/topics.css" />
</head>
<body>
    <#include "./components/header.ftl">

    <div class="body-wrapper topics-wrapper">

        <div class="left">
            <div class="panel">
                <div class="panel-header">
                    <div class="tag-group">
                        <a href="/topic}" class="tag-item link ${RequestParameters.tagId???string("", "active")}">全部</a>
                        <#list Application.tags as tag>
                            <a href="/topic?tagId=${tag.id}" class="tag-item link ${(RequestParameters.tagId!"" == tag.id)?string("active", "")}">${tag.name}</a>
                        </#list>
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="topic-list">
                    <#list topics as topic>
                        ${topic.title}
                    </#list>
                    </ul>
                </div>
            </div>

        </div>

        <div class="right">
            <#if loginUser??>
                <div class="panel user-info">
                    <h2 class="panel-title">Liu Kai's Blog</h2>
                    <div class="content">
                        <a href="/topic/toCreate" class="link">发布话题</a>
                    </div>
                </div>
            <#else>
                <div class="panel user-info">
                    <h2 class="panel-title">Liu Kai's Blog</h2>
                    <div class="content">您可以 <a href="/user/login" class="link">登录</a> 或 <a href="/user/register" class="link">注册</a></div>
                </div>
            </#if>
            <div class="panel">
                <div class="panel-header">无人回复的话题</div>
                <div class="panel-body">

                </div>
            </div>
            <div class="panel">
                <div class="panel-header">积分榜</div>
                <div class="panel-body">

                </div>
            </div>
        </div>

    </div>
</body>
</html>