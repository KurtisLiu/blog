<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css" />
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/topicDetail.css" />
</head>
<body>
    <#include "./components/header.ftl">

    <div class="body-wrapper topic-detail-wrapper">
        <div class="section-left">
            <div class="topic-detail">
                <div class="header">
                    <div class="topic-title">${topic.title}</div>
                    <div class="topic-info">
                        <ul class="header-left">
                            <li class="create-date">发布于${DateUtils.getRangeToNow(topic.crateDate)!""}</li>
                            <li class="author-name">作者：${topic.author???string(topic.author.username!"", "")}</li>
                            <li class="visit-count">${topic.visitCount}次浏览</li>
                            <li class="tag">来自${topic.tag???string(topic.tag.name!"", "")}</li>
                        </ul>
                        <div class="header-right">

                        </div>
                    </div>
                </div>
                <div class="content">
                ${topic.content}
                </div>
            </div>

            <div class="panel replies-panel">
                <div class="panel-header">
                ${topic.replies?size}次浏览
                </div>
                <div class="panel-body">
                <#list topic.replies as reply>
                    <div class="reply_item">
                        <div class="reply-item-row">
                            <img src="${reply.author.avator!''}" class="reply-author-avator">
                            <span class="reply-content">
                                <span>${reply.author.username!''}</span>
                                <a href="#">${reply_index + 1}楼</a>
                            </span>
                            <span class="reply-action">

                            </span>
                        </div>
                        <div class="reply-item-row">
                            ${reply.content}
                        </div>
                    </div>
                </#list>
                </div>
            </div>

            <div class="panel reply-panel">
                <div class="panel-header">添加回复</div>
                <div class="panel-body">
                    <form action="/reply" method="POST">
                        <input type="hidden" name="topic.id" value="${topic.id}" />
                        <textarea id="reply" name="content" class="reply-content"></textarea>
                        <input type="submit" class="reply-btn" value="回复" />
                    </form>
                </div>
            </div>

        </div>

        <div class="section-right">
            <#if topic.author??>
                <div class="panel">
                    <div class="panel-header">作者</div>
                    <div class="panel-body">
                        <img src="${topic.author.avator!""}" />
                        <span class="author-name">${topic.author.username!""}</span>
                    </div>
                </div>
            </#if>
        </div>
    </div>
</body>
</html>