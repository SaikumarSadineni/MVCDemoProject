package pac.DAO;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.practice.vo.Demo;

@Repository
public class FieldsDAO {
	private static final String url = "jdbc:sqlserver://192.168.6.202:1433;databaseName=AMRTPP_DEV_BKP";
	private static final String username = "amrtpp_sa";
	private static final String password = "(amrtppsa)";
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	
	public static Connection getConnection() {

		Connection objConnection = null;

		try {
			Class.forName(driver);
			System.out.println("Drivers loaded successfully");
			objConnection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
		}

		return objConnection;
	}

	public void save(Demo objDemo) throws SQLException {
		PreparedStatement objPreparedStatement = null;
		ResultSet objResultSet = null;
		Connection objConnection = null;
		int count = 0;
		String crateTableQuery = null;
		String query = null;
		StringBuffer insertQuery = null;

		try {

			objConnection = FieldsDAO.getConnection();
			query = "select count(1) from sys.tables where name='case_comments'";
			System.out.println(query);
			objPreparedStatement = objConnection.prepareStatement(query);
			objResultSet = objPreparedStatement.executeQuery();
			System.out.println("Query executed successfully");
			while (objResultSet.next()) {
				count = Integer.parseInt(objResultSet.getString(1));
			}
			System.out.print(count);
			if (count < 1) {
				crateTableQuery = "CREATE TABLE case_comments (\r\n" + "ID int IDENTITY(1,1) primary key ,\r\n"
						+ "subcategory VARCHAR (50) ,\r\n" + "notetype varchar(50),\r\n" + "notes varchar (500),\r\n"
						+ "lst_updt_dtm datetime\r\n" + ");";
				System.out.println(crateTableQuery);
				objPreparedStatement = objConnection.prepareStatement(crateTableQuery);
				objPreparedStatement.executeUpdate();
				System.out.println("Query executed successfully");

			}
			insertQuery = new StringBuffer("insert into case_comments values('").append(objDemo.getSubcategory())
					.append("','").append(objDemo.getNotetype()).append("','").append(objDemo.getNotes())
					.append("',getdate())");
			System.out.println("Final Query with values [ " + insertQuery + " ]");
			objPreparedStatement = objConnection.prepareStatement(insertQuery.toString());
			objPreparedStatement.executeUpdate();
			System.out.println("Query executed successfully");
		} catch (SQLException s) {
			s.getStackTrace();
		} catch (Exception e) {
			e.getStackTrace();
		}finally {
			objConnection.close();
		}

	}
	
	public ArrayList getCommentsDetails() throws SQLException {
		
		PreparedStatement objPreparedStatement = null;
		ResultSet objResultSet = null;
		Connection objConnection = null;
		String query = null;
		Demo objDemo = null;
		ArrayList objlist =null;
		try {
			objConnection = FieldsDAO.getConnection();
			objlist = new ArrayList();
			query = "select ID,subcategory,notetype,notes,lst_updt_dtm from case_comments";
			System.out.println(query);
			objPreparedStatement = objConnection.prepareStatement(query);
			objResultSet = objPreparedStatement.executeQuery();
			System.out.println("Query executed successfully");
			while(objResultSet.next()) {
				objDemo = new Demo();
				objDemo.setId(objResultSet.getInt(1));
				objDemo.setSubcategory(objResultSet.getString(2));
				objDemo.setNotetype(objResultSet.getString(3));
				objDemo.setNotes(objResultSet.getString(4));
				objDemo.setDateandtime(objResultSet.getString(5));
				objlist.add(objDemo);
				//System.out.println(objDemo);
			}	
		}catch(Exception e){
			e.getStackTrace().toString();
		}finally {
			if(objResultSet != null) {
				objResultSet.close();
			}
			objConnection.close();
		}
		
		return objlist;
	}
	
	public void delete(int id) throws SQLException {
		System.out.println("delete method");
		Connection objConnection = null;
		PreparedStatement objPreparedStatement=null;
		String query=null;
		
		try {
			objConnection = FieldsDAO.getConnection();
			query = "delete from case_comments where ID ="+id;
			System.out.println(query);
			objPreparedStatement = objConnection.prepareStatement(query);
			objPreparedStatement.executeUpdate();
			System.out.println("Record Deleted Succesfully");
			
		}catch (Exception e) {
			e.getStackTrace();
		}finally {
			objConnection.close();
		}

	}

}
