package dao;

import java.sql.*;
import java.util.ArrayList;

import Frame.DataBaseInit;
import po.Folder;

/*
 * �ļ������ݿ����
 */
public class FolderDao {

   
    public static Folder findFolderByFolderName(int userID, String folderName){
        Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from folder where owner = ? and fName = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, userID);
                        pst.setString(2, folderName);
			rs = pst.executeQuery();
			
			try {
                            if(rs.next()){
                            Folder folder = new Folder();
                            folder.setName(rs.getString("fName"));
                            folder.setId(rs.getInt("id"));
                            folder.setuID(rs.getInt("owner"));
                            return folder;
                            }
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
		}catch(SQLException e){
			System.out.println("�����ļ���ʧ�ܣ�");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return null;
    }
	/*
	 * �����û��ı��Ѱ�Ҷ�Ӧ���ļ��в��ҷ���
	 */
	public static ArrayList<Folder> findFolderByUserID(int userID){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<Folder> list = new ArrayList<Folder>();
		String sql = "select * from folder where owner = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			if(rs != null){
				try {
					while(rs.next()){
						Folder folder = new Folder();
						folder.setName(rs.getString("fName"));
						folder.setId(rs.getInt("id"));
						folder.setuID(rs.getInt("owner"));
						list.add(folder);
					}
					return list;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}catch(SQLException e){
			System.out.println("�����ļ���ʧ�ܣ�");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * ���ļ��б��в����½����ļ�����Ϣ
	 */
	public static Folder insertFolder(Folder folder){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into folder (fName, owner)values(?, ?);";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, folder.getName());
			pst.setInt(2, folder.getOwnerID());
			pst.execute();
                        folder = findFolderByFolderName(folder.getOwnerID(), folder.getName());
		}catch(SQLException e){
			System.out.println("�����ļ��б�ʧ�ܣ�");
			e.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
                return folder;
	}
	
	/*
	 * �����ļ��е�����
	 * ɾ��ѡ�е��ļ���
	 */
	public static boolean deleteFolder(Folder folder){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from folder where fName = ? and owner = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, folder.getName());
			pst.setInt(2, folder.getOwnerID());
			pst.execute();
		}catch(SQLException ex){
			System.out.println("ɾ��ʧ��");
			ex.printStackTrace();
		}finally{
			try{
				if(pst != null){
					pst.close();
				}
				if(c != null){
					c.close();
				}
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Folder f = new Folder();
		f.setName("�ð�����");
		f.setuID(2);
		insertFolder(f);
		ArrayList<Folder> list = findFolderByUserID(2);
		for(int i = 0;i < list.size();i++){
			f = list.get(i);
			System.out.println(f.getId() + " " + f.getName() + " " + f.getOwnerID());
		}
	}

}
