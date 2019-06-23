package cn.wolfcode.crud.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBastisUtil {
	private MyBastisUtil() {
	}

	private static SqlSessionFactory factory;
	static {
		try {
			factory = new SqlSessionFactoryBuilder()
					.build(Resources
							.getResourceAsStream(
									"mybatis-config.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession openSession(){
		 return factory.openSession(true);
	}
}
