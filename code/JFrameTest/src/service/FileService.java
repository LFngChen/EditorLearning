/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.FileDao;
import java.util.ArrayList;
import po.File;

/**
 *
 * @author ���Ƹ�
 */
public class FileService {
    /**
     * �����ļ�
     * �����ļ������ݿ��е�������Ϣ
     * @param file
     * @return 
     */
    public static File createFile(File file){
        return FileDao.insertFile(file);
        /**
         * �ڱ��ش��̴����ļ���
         */
    }
    
    /**
     * �����ļ��������Ϣ
     * @param file
     * @return 
     */
    public static boolean updateFile(File file){
        return FileDao.updateFile(file);
    }
    public static ArrayList<File> findFileByFolderID(int folderID){
        return FileDao.findFileByFolderID(folderID);
    }
    
    public static File findFileByFileNameFolderID(int folderID, String fileName){
        return FileDao.findFileByFolderFileName(folderID, fileName);
    }
    
    /**
     * ͨ���û���Ѱ���ļ�
     * @param userID
     * @return
     */
    public static ArrayList<File> findFileByUserID(int userID){
        return FileDao.findFileByUserID(userID);
    }
    
    public static boolean deleteFileByFolderID(int folderID){
    	return FileDao.deleteFileByFolderID(folderID);
    }
}
