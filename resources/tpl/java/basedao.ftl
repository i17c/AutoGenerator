package ${package};

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.taobao.tddl.client.sequence.Sequence;

/**
 * BaseDAO
 * Date: ${today}
 * Generate by autoBaseDAO
 * Powered by duxing@Taobao
 */
public class BaseDAO implements InitializingBean {

    protected SqlMapClientTemplate sqlMapClient;

    protected Map<String, Sequence> sequenceTable;

    protected JdbcTemplate jdbcTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (sqlMapClient == null || sequenceTable == null)
            throw new Exception("BaseDAO initilize fail,check related spring's configuration file");
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }


    public void setSequenceTable(Map<String, Sequence> sequenceTable) {
        this.sequenceTable = sequenceTable;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 此处有个约定, sequence 的配置需要是 表名_sequence 作为sequenceTable的 key
     */
    protected Long getNextId(String idKey) throws Exception {
        if (idKey == null) throw new IllegalArgumentException("idKey can not be null");
        Sequence sequence = sequenceTable.get(idKey + "_sequence");
        if (sequence == null) throw new IllegalStateException(idKey + "'s sequence not found");
        try {
            return sequence.nextValue();
        } catch (Exception e) {
            throw new Exception("[BaseDAO-getNextId]", e);
        }
    }

    public int update(String statementName, Object parameterObject) throws Exception {
        try {
            return sqlMapClient.update(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-update]", e);
        }
    }

    public Object insert(String statementName, Object parameterObject) throws Exception {
        try {
            return sqlMapClient.insert(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-insert]", e);
        }
    }

    public int delete(String statementName, Object parameterObject) throws Exception {
        try {
            return sqlMapClient.delete(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-delete]", e);
        }
    }

    public Object queryForObject(String statementName, Object parameterObject) throws Exception {
        try {
            return sqlMapClient.queryForObject(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-queryForObject]", e);
        }
    }

    public List<?> queryForList(String statementName, Object parameterObject) throws Exception {
        try {
            return sqlMapClient.queryForList(statementName, parameterObject);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-queryForList]", e);
        }
    }

    /**
     * 取List，包含分页
     *
     * @param statementName sql语句
     * @param parameterObject 参数对象
     * @param pageNo 页次
     * @param pageSize 每页记录数
     * @return list
     * @throws Exception exception
     */
    public List<?> queryForList(String statementName, Object parameterObject, int pageNo, int pageSize) throws Exception {
        try {
            return sqlMapClient.queryForList(statementName, parameterObject, pageSize * (pageNo - 1), pageSize);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-queryForList]", e);
        }
    }

    public Map<?, ?> queryForMap(String statementName, Object parameterObject, String keyProperty) throws Exception {
        try {
            return sqlMapClient.queryForMap(statementName, parameterObject, keyProperty);
        } catch (DataAccessException e) {
            throw new Exception("[BaseDAO-queryForMap]", e);
        }
    }

}