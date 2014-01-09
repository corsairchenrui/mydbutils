package MySQL.DAO;

import java.sql.ResultSet;
import java.util.Map;

import MySQL.MySQLEntity;

public interface MysqlDAO {
	public boolean insert(MySQLEntity entity);
	public ResultSet select(Map<String,?> criteria);
}
