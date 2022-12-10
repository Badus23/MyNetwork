<#import "macros/common.ftlh" as c>
<#import "macros/login.ftlh" as l>

<@c.page>
    ${message?ifExists}
    <@l.login "/login" false/>
</@c.page>
