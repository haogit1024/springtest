<#list files as file>
<tr>
    <td class="text-right">
        <#if file.type == "folder">
            <i class="fa fa-${file.type}"></i>
        <#else>
            <i class="fa fa-file-${file.type}-o"></i>
        </#if>
    </td>
    <td><a href="javascript:void(0)" >${file.filename}</a></td>
    <td>${file.size}</td>
    <td>${file.time}</td>
</tr>
</#list>