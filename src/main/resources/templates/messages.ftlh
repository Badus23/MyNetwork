<#import "macros/common.ftlh" as c>
<#import "macros/login.ftlh" as l>
<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
    <form method="post" action="/post">
        <input type="text" name="text" placeholder="Text"/>
        <input type="text" name="tag" placeholder="Tag"/>.
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Add"/>
    </form>
    </div>
    <div>Message list</div>
    <form method="post" action="/filter">
        <input type="text" name="filter">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Search">
    </form>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.authorName}</strong>
    </div>
        <#else>
        No messages
    </#list>
</@c.page>