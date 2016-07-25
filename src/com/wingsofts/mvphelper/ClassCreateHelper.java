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
    static final int  PRESENTER = 1;
    public static void CreateClass(String path,String className,String classFullName,int mode) throws IOException {
        String type = null;
        if(mode == MODEL){
            type = "Model";
        }else {
            type = "Presenter";
        }
        String dir = path+type.toLowerCase()+"/";
        path = dir+className+type+"Impl.java";
        File dirs = new File(dir);
        File file = new File(path);
        String packageName = getPackageName(path);
        System.out.println(packageName);
        dirs.mkdirs();
        file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("package "+ packageName + type.toLowerCase()+";");

        writer.newLine();
        writer.write("import "+packageName +"contract."+classFullName+";");
        writer.newLine();
        writer.newLine();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        writer.write("/**\n* Created by MVPHelper on "+sdf.format(date)+"\n*/");

        writer.newLine();
        writer.newLine();
        writer.write("public class "+className+type+"Impl implements "+
                classFullName+"."+className+type+"{");
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
