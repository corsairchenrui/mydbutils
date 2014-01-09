package MySQL.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;










import MySQL.MySQLEntity;

public class DAOImpl implements MysqlDAO {

	protected Statement statement;
	public static DAOImpl newDAO(String database){
		if(null != database)
			return new DAOImpl(database);
		return null;
	}
	protected DAOImpl(String database) {
		statement = DAOFactory.load(database);
	}
	@Override
	public boolean insert(MySQLEntity entity) {
		String sql = null;
		if(null != statement && null != entity)
		try {
			sql = entity.inserStatement();
			if(null == sql)
				return false;
			if(statement.execute(sql))
				return true;
		} catch (SQLException e) {
			System.err.println(e.getErrorCode());
		}
		return false;
	}
	public void insert(Set<? extends MySQLEntity> entitySet){
		if(null != entitySet){
			for(MySQLEntity entity:entitySet)
				this.insert(entity);
		}
	}

	@Override
	public ResultSet select(Map<String, ?> criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
