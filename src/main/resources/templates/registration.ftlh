<#import "macros/common.ftlh" as c>
<#import "macros/login.ftlh" as l>

<@c.page>
    <div class="mb-1">Register</div>
    ${message?ifExists}
    <@l.login "/registration" true/>
</@c.page>