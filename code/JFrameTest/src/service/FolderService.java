package service;

import dao.FolderDao;
import po.Folder;
import po.User;

public class FolderService {
	/**
	 *1. ����dao����ط��������½��ļ�����Ϣд�����ݿ�
	 *2.�ڱ��ش��̴�����Ӧ���ļ���
	 *folderName: �½��ļ�������
	 *userID: �ļ���ӵ�����û�id
	 *���أ������Ƿ�ɹ�
	 */
	public static Folder createFolder(String folderName, User user){
		Folder folder = new Folder();
		folder.setName(folderName);
		folder.setuID(user.getId());
		//���÷����ڱ��ش����ļ���
		return FolderDao.insertFolder(folder);
	}
	public static Folder searchFolderByNameUserID(int userID, String folderName){
            return FolderDao.findFolderByFolderName(userID, folderName);
        }
	/**
	 * �����ļ��������ݿ��еõ��ļ����е��ļ��б�
	 */
	public static void getFolderFile(){
		
	}
	
	public static void deleteFolder(Folder folder){
		FolderDao.deleteFolder(folder);
		//�����ļ������ķ���ɾ�������ļ���
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
