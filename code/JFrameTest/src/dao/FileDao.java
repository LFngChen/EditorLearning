package dao;
import java.sql.*;
import java.util.ArrayList;

import Frame.DataBaseInit;
import po.File;
/*
 * �ļ����ݿ����
 */
public class FileDao {

	/*
	 * �����ļ��еı����Ѱ���ļ�
	 */
	public static ArrayList<File> findFileByFolderID(int folderID){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<File> list = new ArrayList<File>();
		String sql = "select * from file where folder = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, folderID);
			rs = pst.executeQuery();
			if(rs != null){
				try {
					while(rs.next()){
						File file = new File();
						file.setId(rs.getInt("id"));
						file.setModifiedTime(rs.getTimestamp("modifiedTime"));
						file.setCreateTime(rs.getTimestamp("createTime"));
						file.setTitle(rs.getString("title"));
						file.setTag(rs.getString("tag"));
						file.setSize(rs.getString("fileSize"));
						list.add(file);
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
	
        public static File findFileByFolderFileName(int folderID, String fileName){
            Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from file where folder = ? and title = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, folderID);
                        pst.setString(2, fileName);
			rs = pst.executeQuery();
                        if(rs.next()){
                            File file = new File();
                            file.setId(rs.getInt("id"));
                            file.setModifiedTime(rs.getTimestamp("modifiedTime"));
                            file.setCreateTime(rs.getTimestamp("createTime"));
                            file.setTitle(rs.getString("title"));
                            file.setTag(rs.getString("tag"));
                            file.setSize(rs.getString("fileSize"));
                            return file;
                        }
			else{
                            c.close();
                            pst.close();
                            return null;
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
	 * �����ݱ��в���һ���ļ���Ϣ
	 */
	public static File insertFile(File file){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "insert into file (title, tag, createTime, modifiedTime, fileSize, folder)values(?, ?, ?, ?, ?, ?);";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, file.getTitle());
			pst.setString(2, file.getTag());
			pst.setTimestamp(3, file.getCreateTime());
			pst.setTimestamp(4, file.getModifiedTime());
			pst.setString(5, file.getSize());
			pst.setInt(6, file.getfId());
			pst.execute();
                        file = findFileByFolderFileName(file.getfId(), file.getTitle());
		}catch(SQLException e){
			System.out.println("�����ļ��б�ʧ�ܣ�");
			e.printStackTrace();
                        return null;
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
		return file;
	}
        
        public static boolean updateFile(File file){
            Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "update file set tag = ?, modifiedTime = ?, fileSize = ? where id = ?;";
		try{
			pst = c.prepareStatement(sql);
			pst.setString(1, file.getTag());
			pst.setTimestamp(2, file.getModifiedTime());
			pst.setString(3, file.getSize());
			pst.setInt(4, file.getId());
			pst.execute();
		}catch(SQLException e){
			System.out.println("�����ļ��б�ʧ�ܣ�");
			e.printStackTrace();
                        return false;
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
		ArrayList<File> list = new ArrayList<File>();
		File file = new File();
		//��ȡ��ǰʱ��
		java.util.Date date = new java.util.Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		file.setfId(2);
		file.setSize("100K");
		file.setTag("���±�");
		file.setTitle("����.txt");
		file.setModifiedTime(timestamp);
		file.setCreateTime(timestamp);
		insertFile(file);
		list = findFileByFolderID(2);
		for(int i = 0;i < list.size();i++){
			file = list.get(i);
			System.out.println(file.getTitle() + file.getId());
		}
	}

	public static ArrayList<File> findFileByUserID(int userID){
        Connection c = DataBaseInit.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<File> list = new ArrayList<File>();
        String sql = "select * from file where folder in (select id from folder where owner = ?) order by modifiedTime desc;";
	try{
		pst = c.prepareStatement(sql);
		pst.setInt(1, userID);
		rs = pst.executeQuery();
		if(rs != null){
			try {
				while(rs.next()){
					File file = new File();
					file.setId(rs.getInt("id"));
					file.setModifiedTime(rs.getTimestamp("modifiedTime"));
					file.setCreateTime(rs.getTimestamp("createTime"));
					file.setTitle(rs.getString("title"));
					file.setTag(rs.getString("tag"));
					file.setSize(rs.getString("fileSize"));
					list.add(file);
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
	
	/**
	 * ɾ��ĳ���ļ����µ������ļ�
	 * @param folderID
	 * @return
	 */
	public static boolean deleteFileByFolderID(int folderID){
		Connection c = DataBaseInit.getConnection();
		PreparedStatement pst = null;
		String sql = "delete from file where folder = ?";
		try{
			pst = c.prepareStatement(sql);
			pst.setInt(1, folderID);
			pst.execute();
		}catch(SQLException e){
			System.out.println("ɾ���ļ�ʧ��");
			e.printStackTrace();
			try {
				c.close();
				pst.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}finally{
			try {
				c.close();
				pst.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return true;
	}
}
