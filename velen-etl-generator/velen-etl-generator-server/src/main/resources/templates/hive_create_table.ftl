CREATE TABLE ${table}
(
<#list ${tableColumn} as col>
  col
</#list>
    uuid string,
    id int,
    `time` date,
)
    row format delimited
    fields terminated by ','
    map keys terminated by ':';


<#if ...></#if>

<#if map["最新-图片"]?exists>