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
                        <#list tags as tag>
                            <a href="/topic?tagId=${tag.id}" class="tag-item link ${(RequestParameters.tagId!"" == tag.id)?string("active", "")}">${tag.name}</a>
                        </#list>
                    </div>
                </div>
                <div class="panel-body">
                    <#if (topics?? && topics.list?? && topics.list?size > 0)>
                        <ul class="topic-list">
                            <#list topics.list as topic>
                                <li>
                                    <a href="/user/${topic.author.id}"><image src="${topic.author.avator!""}" class="author-avator" /></a>
                                    <span class="reply-info">
                                        <span class="reply-count">${topic.replyCount}</span>
                                        <span>/</span>
                                        <span class="visitCount">${topic.visitCount}</span>
                                    </span>
                                    <a href="/topic/${topic.id}" class="link">${topic.title}</a>
                                    <span class="last-time">${DateUtils.getRangeToNow(topic.lastReplyDate)!""}</span>
                                </li>
                            </#list>
                        </ul>
                        <div class="pagination">
                            当前第${topics.pageNow + 1}页，总共${topics.getTotalPage()}页
                        </div>
                    <#else>
                        暂无数据
                    </#if>
                </div>
            </div>

        </div>

        <div class="right">
            <#if Session.loginUser??>
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