package com.chaoxing.test.util.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * @className: FileUtils
 * @description:
 * @author: chaoxing
 * @date: 2018-04-28 14:27:16
 * @version: ver 1.0
 */
public class FileUtils {

    private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);

    /** 删除文件和文件夹
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:16:36
     * @param file
     * @return boolean
     */
    public static boolean deleteFileAndFolder(File file){
        try {
            if(file == null || !file.exists()){
                return true;
            }
            if(file.isDirectory()){
                File[] childrenFile = file.listFiles();
                if(childrenFile != null){
                    for(File childFile : childrenFile){
                        if(!deleteFileAndFolder(childFile)){
                            return false;
                        }
                    }
                }
                return file.delete();
            }else{
                return file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************删除文件和文件夹error,e:{}", e.getMessage());
        }
        return false;
    }
    /** 删除文件和文件夹
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:16:24
     * @param path
     * @return boolean
     */
    public static boolean deleteFileAndFolder(String path) {
        boolean deleteFlag = false;
        try {
            File file = new File(path);
            deleteFlag = deleteFileAndFolder(file);
            if(deleteFlag){
                LOGGER.info("************删除文件成功************");
            }else{
                LOGGER.info("************删除文件失败************");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************删除{}目录下的文件和文件夹 error,e:{}", path, e.getMessage());
        }
        return deleteFlag;
    }
    /** 删除文件和文件夹（排除指定目录和文件）
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:16:10
     * @param deleteFile
     * @param excludeFiles
     * @return boolean
     */
    public static boolean deleteFileAndFolder(File deleteFile, final List<File> excludeFiles){
        try {
            if(excludeFiles == null || excludeFiles.isEmpty()){
                return deleteFileAndFolder(deleteFile);
            }
            if(deleteFile == null || !deleteFile.exists()){
                return true;
            }
            if(deleteFile.isDirectory()){//目录
                //是否包含排除的文件
                boolean hasExcludeFile = false;
                String folderPath = deleteFile.getPath();
                for(File excludeFile : excludeFiles){
                    if(excludeFile.getPath().indexOf(folderPath) > -1){
                        hasExcludeFile = true;
                        break;
                    }
                }
                //排除过滤的目录就是可删除的目录列表
                File[] deleteAbleFileArrs = deleteFile.listFiles(pathname -> {
                    //对于排除的目录不能删除
                    boolean exclude = true;
                    for(File excludeFile : excludeFiles){
                        if(isSameFile(pathname, excludeFile)){
                            exclude = false;
                            excludeFiles.remove(excludeFile);//该文件在当前目录排除后不可能在其他目录也被排除
                            break;
                        }
                    }
                    return exclude;
                });
                List<File> deleteAbleFiles = new ArrayList<File>(Arrays.asList(deleteAbleFileArrs));
                if(deleteAbleFiles != null && !deleteAbleFiles.isEmpty()){
                    for(File deleteAbleFileItem : deleteAbleFiles){
                        if(!deleteFileAndFolder(deleteAbleFileItem, excludeFiles)){
                            return false;
                        }
                    }
                }
                if(!hasExcludeFile){
                    return deleteFile.delete();
                }else{
                    return true;
                }
            }else{
                for(File excludeFile : excludeFiles){
                    if(isSameFile(deleteFile, excludeFile)){
                        return true;
                    }
                }
                return deleteFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************删除文件和文件夹（排除指定目录和文件） error,e:{}", e.getMessage());
        }
        return false;
    }
    /** 验证是否同一文件（根据文件路径）
     * @Description: 如果两个文件的目录相同则认为是同一个文件
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:15:51
     * @param oldFile
     * @param newFile
     * @return boolean
     */
    public static boolean isSameFile(File oldFile, File newFile){
        boolean theSame = false;
        try {
            if(oldFile == null || newFile == null){
                return false;
            }
            String oldFilePath = oldFile.getAbsolutePath();
            String newFilePath = newFile.getAbsolutePath();
            theSame = oldFilePath.equals(newFilePath);
            LOGGER.info("************验证是否同一文件（根据文件路径） success************");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************验证是否同一文件（根据文件路径） error,e:{}", e.getMessage());
        }
        return theSame;
    }
    /** nio文件通道拷贝文件
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:15:41
     * @param sourcePath
     * @param targetPath
     * @return boolean
     */
    public static boolean nioCopyFile(String sourcePath, String targetPath){
        boolean copyFlag = false;
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        createFolder(targetPath);

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel sourceChannel = null;
        FileChannel targetChannel = null;
        try {
            if (!sourceFile.exists()) {
                LOGGER.error("************nio文件通道拷贝文件error,e:需要拷贝的文件不存在,path:{}", sourcePath);
                copyFlag = false;
            } else {
                fileInputStream = new FileInputStream(sourceFile);
                fileOutputStream = new FileOutputStream(targetFile);
                sourceChannel = fileInputStream.getChannel();
                targetChannel = fileOutputStream.getChannel();
				/*MappedByteBuffer mappedByteBuffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChannel.size());
				targetChannel.write(mappedByteBuffer);*/
                sourceChannel.transferTo(0, sourceChannel.size(), targetChannel);
                sourceChannel.force(true);
                copyFlag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("************nio文件通道拷贝文件error,e:{}", e.getMessage());
        } finally {
            if (targetChannel != null) {
                try {
                    targetChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("************关闭目标文件通道error,e:{}", e.getMessage());
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("************关闭目标文件输出流error,e:{}", e.getMessage());
                }
            }
            if (sourceChannel != null) {
                try {
                    sourceChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("************关闭源文件通道error,e:{}", e.getMessage());
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("************关闭源文件输入流error,e:{}", e.getMessage());
                }
            }
        }
        return copyFlag;
    }
    /** 拷贝文件
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:15:30
     * @param sourcePath
     * @param targetPath
     * @return void
     */
    public static void copyFile(String sourcePath, String targetPath){
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        createFolder(targetPath);
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            if(!sourceFile.exists()){
                return;
            }
            int byteread = 0;
            is = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1444];
            while ((byteread = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteread);
            }
        } catch(IOException e){
            e.printStackTrace();
            LOGGER.error("************拷贝文件 error,e:{}", e.getMessage());
            throw new RuntimeException(e);
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("************关闭目标文件输出流error,e:{}", e.getMessage());
            }
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("************关闭源文件输入流error,e:{}", e.getMessage());
            }
        }
    }
    /** 验证文件是否存在
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:15:18
     * @param path
     * @return
     * @return boolean
     */
    public static boolean fileExist(String path){
        boolean isFileExist = false;
        try {
            File file = new File(path);
            isFileExist = file.exists();
            LOGGER.info("************验证文件是否存在 success************");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************验证文件是否存在 error,e:{}", e.getMessage());
        }
        return isFileExist;
    }
    /** 创建目录
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 下午2:14:11
     * @param filePath
     * @return void
     */
    public static void createFolder(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            File supperFile = file.getParentFile();
            if (!supperFile.exists()) {
                file.mkdirs();
            } else {
                file.mkdir();
            }
        }
    }
    /** 获取文件的后缀
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月11日 上午9:15:08
     * @param filePath
     * @return
     * @return String
     */
    public static String getFileSuffix(String filePath){
        String suffix = null;
        try {
            if(filePath.indexOf(".") != -1){
                suffix = filePath.substring(filePath.lastIndexOf("."), filePath.length());
                LOGGER.info("************获取文件的后缀 success************");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("************获取文件的后缀 error,e:{}", e.getMessage());
        }
        return suffix;
    }
    /** 生成当前时间戳
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月14日 上午10:13:23
     * @return String
     */
    private static String generateTimeStamp(){
        return DateUtils.TIMESTAMP.format(Calendar.getInstance().getTime());
    }
    /** 生成上传文件的时间戳文件名
     * @Description:
     * @author: 王文彬
     * @version: 2016年5月14日 上午10:13:43
     * @param suffix
     * @return String
     */
    public static String generateUploadFileTimeStampFileName(String suffix){
        return generateTimeStamp() + suffix;
    }
    /** 获取文件的目录
     * @Description: 
     * @author: 
     * @Date: 2018-04-28 14:35:42
     * @param: file
     * @return: java.lang.String
     */
    public static String getFileDir(File file) {
        if (file.isDirectory()) {
            return file.getPath();
        }
        return file.getParent();
    }
    /** 获取文件的目录
     * @Description: 
     * @author: 
     * @Date: 2018-04-28 14:36:33
     * @param: filePath
     * @return: java.lang.String
     */
    public static String getFileDir(String filePath) {
        if (!StringUtils.isEmpty(filePath)) {
            filePath = filePath.replaceAll("%20", " ");
        }
        File file = new File(filePath);
        return getFileDir(file);
    }
    /**保存上传文件并返回路径
     * @Description: 
     * @author: wwb
     * @Date: 2018-08-03 13:06:37
     * @param: multipartFile
     * @param: savePath
     * @return: java.lang.String
     */
    public static String saveUploadFile(MultipartFile multipartFile, String savePath) throws IOException {
        Assert.hasLength(savePath, "保存文件的路径不能为空");
        Assert.notNull(multipartFile, "上传文件不能为空");
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = getFileSuffix(originalFilename);
        String saveFileName = generateUploadFileTimeStampFileName(suffix);
        createFolder(savePath);
        File folder = new File(savePath);
        String filePath = folder.getAbsolutePath() + File.separator + saveFileName;
        multipartFile.transferTo(new File(filePath));
        return filePath;
    }
    /**保存上传文件并返回路径(使用原文件名)
     * @Description:
     * @author: wwb
     * @Date: 2018-08-03 13:06:37
     * @param: multipartFile
     * @param: savePath
     * @return: java.lang.String
     */
    public static String saveUploadFileFormerName(MultipartFile multipartFile, String savePath) throws IOException {
        Assert.hasLength(savePath, "保存文件的路径不能为空");
        Assert.notNull(multipartFile, "上传文件不能为空");
        String originalFilename = multipartFile.getOriginalFilename();
        createFolder(savePath);
        File folder = new File(savePath);
        String filePath = folder.getAbsolutePath() + File.separator + originalFilename;
        multipartFile.transferTo(new File(filePath));
        return filePath;
    }
}
