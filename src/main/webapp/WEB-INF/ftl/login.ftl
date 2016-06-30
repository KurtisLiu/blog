<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css" />
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/entrence.css" />
</head>
<body>
    <#include "./components/header.ftl">
    <div class="body-wrapper">
        <div class="panel">
            <div class="panel-header"><a href="/" class="link">主页</a> / 登录</div>
            <div class="panel-body">
                <#if Request.errorMessage??>
                    <div class="error">${errorMessage}</div>
                </#if>
                <form action="/user/login${RequestParameters["returnUrl"]???string("?returnUrl=" + RequestParameters["returnUrl"]!"", "")}" method="POST">
                    <div class="form-group">
                        <label class="form-label" for="email">邮箱</label>
                        <input class="form-input" type="text" name="email" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="password">密码</label>
                        <input class="form-input" type="password" name="password" autocomplete="off" />
                    </div>
                    <div class="action-group">
                        <input type="submit" class="submit login" value="登录" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>