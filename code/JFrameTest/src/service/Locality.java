/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;


/**
 *�ṩ���ػ����������û���Ϣ���ļ�����Ϣ���ļ���Ϣ���ػ�
 * @author ���Ƹ�
 */
public class Locality {
    static final String mainDirectory = "C:/j2EE/";
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LINE_SEPARATOR = System.lineSeparator();
    /**
     * �����ļ���
     * @param userName
     * @return 
     */
    public static boolean createDirectory(String folder){
        Path path = getPath(folder);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    /**
     * ɾ���ļ���
     * @param folder
     * @return 
     */
    public static boolean deleteDirectory(String folder){
        folder = mainDirectory + folder;
        folder = folder.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //·��ת���ɱ���ƽ̨��
        File file = new File(folder);
        return deleteDir(file);
    }
    
    /**
     * �������ļ�
     * @param filePath
     * @return 
     */
    public static boolean createFile(String filePath){
        Path path = getPath(filePath);
       // System.out.println(path.toString());
        try {
            Files.createFile(path);
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
            return false;
	}
        return true;
    }
    
    /**
     * �������ļ�����ǰ������ʾ
     * @param filePath
     * @param textarea
     * @return 
     */
    public static boolean readFile(String filePath, JTextArea textarea){
        filePath = mainDirectory + filePath;
        FileReader fr = null;
        BufferedReader bfr = null;
        try {
            fr = new FileReader(filePath);
//            bfr = new BufferedReader(fr);
            int s;
            while((s = fr.read()) != -1){
                textarea.append((char)s + "");                 //���ļ�������д���������
            } 
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            try {
                if(fr != null && bfr != null){
                    fr.close();
                    bfr.close();
                } 
            } catch (IOException ex) {
                Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    /**
     * ����������TextArea�е�����д���ļ���
     * @param filePath
     * @param textarea
     * @return 
     */
    public static boolean writeFile(String filePath, JTextArea textarea){
        filePath = mainDirectory + filePath;
        FileWriter fw = null;
        try {
            fw = new FileWriter(filePath);
            String content = textarea.getText();
            for(int i = 0;i < content.length();i++){
                if(content.charAt(i)==10){                  //����ǻ��з������ɱ��ػ��з�
                    fw.write(LINE_SEPARATOR);
                }
                else{
                    fw.write(content.charAt(i));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        try {  
            if(fw != null){
                fw.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Locality.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
        
    /**
     * ���ص�ǰ·����Path����
     * @param folder
     * @return 
     */
    private static Path getPath(String folder){
        String directory = mainDirectory + folder;
        directory = directory.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //·��ת���ɱ���ƽ̨��
        Path path = Paths.get(directory);
        return path;
    }
     
       /**
     * ɾ����Ŀ¼
     * @param dir ��Ҫɾ����Ŀ¼·��
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * �ݹ�ɾ��Ŀ¼�µ������ļ�����Ŀ¼�������ļ�
     * @param dir ��Ҫɾ�����ļ�Ŀ¼
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //�ݹ�ɾ����Ŀ¼
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // Ŀ¼��ʱΪ�գ�����ɾ��
        return dir.delete();
    }
}

