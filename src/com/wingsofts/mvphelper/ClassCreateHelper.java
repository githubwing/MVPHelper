package com.wingsofts.mvphelper;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**创建class的类
 * Created by wing on 2016/7/23.
 */
public class ClassCreateHelper {
   static final int  MODEL = 0;
    static final int VIEW = 2;
    static final int  PRESENTER = 1;
    static final int CONTRACT = 0;
    public static void createInterface(String path, String className, String classFullName, int mode) throws IOException {
        String type = null;
        if(mode == MODEL){
            type = "Model";
        }else if(mode == PRESENTER){
            type = "Presenter";
        }else if(mode == VIEW){
            type = "View";
        }
        String dir = path + type.toLowerCase()+"/";

        path = dir + className + type+".java";

        File dirs = new File(dir);

        System.out.println("dirs = "+dir);
        File file = new File(path);
        if(!dirs.exists()){
            dirs.mkdir();
        }
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        String packageName = getPackageName(path);
        writer.write("package "+ packageName + type.toLowerCase()+";");
        writer.newLine();
        writer.newLine();
        ;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        writer.write("/**\n* Created by MVPHelper on "+sdf.format(date)+"\n*/");

        writer.newLine();
        writer.newLine();
        writer.write("public interface " + className + type + "{");
        writer.newLine();
        writer.newLine();
        writer.write("}");

        writer.flush();
        writer.close();
    }

    /**
     * 用于contract 模式文件 以及presenter模式 实现类 的创建
     * @param path
     * @param className
     * @param classFullName
     * @param mode
     * @param tag
     * @throws IOException
     */
    public static void createImplClass(String path, String className, String classFullName, int mode, int tag) throws IOException {
        String type = null;
        if(mode == MODEL){
            type = "Model";
        }else if(mode == PRESENTER){
            type = "Presenter";
        }
        //开始创建实现文件
        String dir = path+type.toLowerCase()+"/";
        path = dir+className+type+"Impl.java";
        File dirs = new File(dir);
        File file = new File(path);
        String packageName = getPackageName(path);
        System.out.println(packageName);
        if(!dirs.exists()) {
            dirs.mkdirs();
        }
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("package "+ packageName + type.toLowerCase()+";");

        writer.newLine();
        if(tag == CONTRACT) {
            writer.write("import " + packageName + "contract." + classFullName + ";");
        }
        writer.newLine();
        writer.newLine();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        writer.write("/**\n* Created by MVPHelper on "+sdf.format(date)+"\n*/");

        writer.newLine();
        writer.newLine();
        if(tag == CONTRACT) {
            writer.write("public class " + className + type + "Impl implements " +
                    classFullName + "."  + type + "{");
        }else if(tag == PRESENTER){

            writer.write("public class " + className+type + "Impl implements " +
                     className + type + "{");
        }
        writer.newLine();

        writer.newLine();
        writer.write("}");
        writer.flush();
        writer.close();
    }

    private static String getPackageName(String path) {
        String[] strings = path.split("/");
        StringBuilder packageName = new StringBuilder();
        int index = 0;
        int length = strings.length;
        for(int i = 0;i<strings.length;i++){
            if(strings[i].equals("com")||strings[i].equals("org")||strings.equals("cn")){
                index = i;
                break;
            }

        }
        for(int j = index;j<length-2;j++){
            packageName.append(strings[j]+".");
        }
        return packageName.toString();
    }

    public static String getCurrentPath(AnActionEvent e,String classFullName){

        VirtualFile currentFile = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());

        String path = currentFile.getPath().replace(classFullName+".java","");
        return path;
    }


}
