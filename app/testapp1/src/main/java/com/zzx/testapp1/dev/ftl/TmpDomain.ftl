package ${domainPackage};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.zzx.common.dao.domain.PO;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date ${.now?string("yyyy-MM-dd hh:mm:ss")}
 */
@Entity
@Table(name = "${tableName}", schema = "${schema}")
public class ${domainClassName} extends PO {

<#assign excludeFields = ["id", "create_date", "update_date"]>
    
<#list columns as column>
<#if !(excludeFields?seq_contains(column.columnName)) >
    <#assign autograph = "private " + column.propertyType + " " + column.propertyName + ";">
    <#if (column.columnComment?? && column.columnComment?length > 0)>
    /**
     * ${column.columnComment}
     */
    </#if>
    <#if column.propertyType == "java.util.Date">
    @javax.persistence.Temporal(javax.persistence.TemporalType<#if column.columnType == 91>.DATE<#elseif column.columnType == 92>.TIME<#else>.TIMESTAMP</#if>)
    </#if>
    @Column(name = "${column.columnName}"<#if column.columnSize != 0>, length = ${column.columnSize}</#if><#if column.isNullable == "NO">, nullable = false</#if><#if column.unique!false>, unique = true</#if>)
    ${autograph}
    
</#if>
</#list>
     
<#list columns as column>
<#if !(excludeFields?seq_contains(column.columnName)) >
    public void set${column.propertyName?cap_first} (${column.propertyType} ${column.propertyName}) {
        this.${column.propertyName} = ${column.propertyName};
    }
    
    public ${column.propertyType} get${column.propertyName?cap_first} () {
        return this.${column.propertyName};
    }
    
</#if>
</#list>
}
