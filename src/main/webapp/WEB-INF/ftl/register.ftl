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
    <div class="body-wrapper register-wrapper">
        <div class="panel">
            <div class="panel-header"><a href="/" class="link">主页</a> / 注册</div>
            <div class="panel-body">
                <form action="/user/register" method="POST">
                    <div class="form-group">
                        <label class="form-label" for="username">用户名</label>
                        <input class="form-input" type="text" name="username" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="password">密码</label>
                        <input class="form-input" type="password" name="password" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="email">邮箱</label>
                        <input class="form-input" type="email" name="email" autocomplete="off" />
                    </div>
                    <div class="action-group">
                        <input type="submit" class="submit login" value="注册" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>