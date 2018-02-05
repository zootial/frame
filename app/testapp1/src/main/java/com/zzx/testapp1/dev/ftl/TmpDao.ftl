package ${daoPackage};

import ${domainPackage}.${domainClassName};
import org.springframework.transaction.annotation.Transactional;
import com.zzx.common.dao.CommonDao;

/**
 * TODO:
 * 
 * @author CodeCreator
 * @Date ${.now?string("yyyy-MM-dd hh:mm:ss")}
 */
@Transactional(readOnly = true)
public interface ${domainClassName}${daoSuffix} extends CommonDao<${domainClassName}, Long> {

}
