package service;

import po.User;
import dao.UserDao;
public class UserService {
	private UserDao ud = new UserDao();
	
	/**
	 * �ж��û��Ƿ��ܹ���¼
	 * @param user
	 * @return
	 */
	public User userLogin(User user){
               return ud.userQuery(user);
	}
	
	/**
	 * �����ݱ��в������û�
	 * @param user
	 * @return
	 */
	public String userRegist(User user){
		return ud.insertUserInfo(user);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
