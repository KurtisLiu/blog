<!doctype html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>设置</title>
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/base.css" />
    <link type="text/css" rel="stylesheet" href="${staticPath}/css/profile.css" />
</head>
<body>
    <#include "./components/header.ftl">
    <div class="body-wrapper profile-wrapper">
        <div class="panel">
            <div class="panel-header"><a href="/" class="link">主页</a> / 设置</div>
            <div class="panel-body">
                <form action="/user" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label class="form-label" for="username">用户名</label>
                        <input class="form-input" type="text" name="username" value="${loginUser.username!''}" autocomplete="off" />
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="email">邮箱</label>
                        <input class="form-input" type="email" name="email" value="${loginUser.email!''}" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-label" for="avator">用户头像</label>
                        <div class="form-upload">
                            <#if loginUser.avator??>
                                <img src="${loginUser.avator}" class="avator">
                            </#if>
                            <input type="file" name="avatorFile"  />
                        </div>
                    </div>
                    <div class="action-group">
                        <input type="submit" class="submit update" value="保存设置" />
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>