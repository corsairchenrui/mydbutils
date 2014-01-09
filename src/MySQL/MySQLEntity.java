package MySQL;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class MySQLEntity {
	
	protected String tablename;
	public MySQLEntity(String tablename) {
		super();
		this.tablename = tablename;
	}
	public String insertExpression(){
		Class<?> c = this.getClass();
		if(null == tablename)
			return null;
		StringBuilder tableCause = new StringBuilder(tablename+" (");
		StringBuilder valueCause = new StringBuilder(" values (");
		for(int i = 0;i<c.getDeclaredFields().length;i++){
			Field f = c.getDeclaredFields()[i];
			Object o = null;
			String getter = "get";
			getter += ((char)(f.getName().charAt(0)-32))+f.getName().substring(1);
			try {
				o = c.getMethod(getter).invoke(this);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException
					| SecurityException e) {
				e.printStackTrace();
			}
			if(null != o){
				tableCause.append(f.getName());
				valueCause.append('\''+o.toString()+'\'');
				if(i < c.getDeclaredFields().length - 1){
					tableCause.append(',');
					valueCause.append(',');
				}
				else{
					tableCause.append(')');
					valueCause.append(')');
				}
			}
		}
		StringBuilder ret = new StringBuilder("insert into "+tableCause.toString()+valueCause.toString());
		return ret.toString();
	}
	public String tablename(){
		return tablename;
	}
}
