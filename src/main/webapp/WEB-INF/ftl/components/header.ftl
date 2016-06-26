<header>
    <h1 class="title">Liu Kia's Blog</h1>
    <input type="type" id="search" class="search" />

    <div class="right">
        <a href="/" class="link">首页</a>
        <#if loginUser??>
            <a href="/messages/unread" class="link">未读消息</a>
            <a href="/user/setting" class="link">设置</a>
            <a href="/logout" class="link">退出</a>
        <#else>
            <a href="/user/login" class="link">登录</a>
            <a href="/user/register" class="link">注册</a>
        </#if>
    </div>
</header>