package Frame;
import java.sql.*;
public class DataBaseInit {
	/*
	 *�������ݿ�
	 *�������ݿ����Ӷ���
	 */
	public static Connection getConnection(){
		//�������ݿ�
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:C:/Users/���Ƹ�/test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  //  System.out.println("Opened database successfully");    
	    return c;
	}
	
	/*
	 * ��ʼ�����ݿ�
	 */
	public static void initDataBase(){
		//������ݿ�����
		Connection c = getConnection();
		Statement stat = null;
		//�����û���Ϣ��
		String sql1 = "DROP TABLE IF EXISTS userInfo;" + 
					"create table if not exists userInfo(" +
					"id INTEGER primary key AUTOINCREMENT," + 
					"uName varchar(20),"+
					"password varchar(20)," + 
					"loginID varchar(20));";
		//�����ļ��б�
		String sql2 = "DROP TABLE IF EXISTS folder;" + 
					"create table if not exists folder(" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"fName varchar(25)," +
					"owner INTEGER);";
		//�����ļ���
		String sql3 = "DROP TABLE IF EXISTS file;"
				+ "create table if not exists file("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "title varchar(25),"
				+ "tag varchar(25),"
				+ "createTime time,"
				+ "modifiedTime time,"
				+ "fileSize varchar(15),"
				+ "folder INTEGER);";
		
		//���û����в���ԭʼ�û����
		String sql4 = "insert into userInfo (id, loginID)values(1, " + 100000 + ");";
		String sql5 = "insert into userInfo (loginID, password, uName) values('15020031023', '15020031023','LanYunFu');";
		try {
			stat = c.createStatement();
			stat.executeUpdate(sql1 + sql2 + sql3 + sql4 + sql5);
//			System.out.println(c == null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("���ݿ��ʼ��ʧ��!");
			e.printStackTrace();
		}finally{
			try{
				if(stat != null){
					stat.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initDataBase();
//		Connection c = getConnection();
	}

}
