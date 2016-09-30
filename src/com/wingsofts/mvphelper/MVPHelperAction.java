package com.wingsofts.mvphelper;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiJavaFile;
import org.apache.commons.logging.Log;
import org.codehaus.groovy.ast.ClassHelper;

import java.io.IOException;

/**
 * 程序的入口
 * Created by wing on 16/7/23.
 */
public class MVPHelperAction extends AnAction {
    private ClassModel _classModel;
    private Editor _editor;
    private String _content;
    private boolean canCreate;
    private AnActionEvent _event;
    private String _path;
    private int mode;
    private final int MODE_CONTRACT = 0;
    private final int MODE_PRESENTER = 1;
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        this._event = e;
        canCreate = true;
        init(e);
        getClassModel();
        createFiles();
        PsiJavaFile javaFile = (PsiJavaFile) e.getData(CommonDataKeys.PSI_FILE);

        System.out.println("current package name is :"+javaFile.getPackageName());
        try {
            if(canCreate) {
                createClassFiles();
//                MessagesCenter.showMessage("created success! please wait a moment","success");
                refreshProject(e);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

    private void refreshProject(AnActionEvent e) {
        e.getProject().getBaseDir().refresh(false,true);
        }

    /**
     * 创建class文件
     * create class files
     * @throws IOException
     */
    private void createClassFiles() throws IOException {

        if (mode == MODE_CONTRACT) {
            createFileWithContract();

        }else if(mode == MODE_PRESENTER){
            createClassWithPresenter();
        }
    }

    private void createClassWithPresenter() throws IOException {
        String className = _classModel.get_className();
        String classFullName = _classModel.get_classFullName();
        System.out.println("_path presenter:" + _path);
        ClassCreateHelper.createImplClass(_path
                , className
                , classFullName, ClassCreateHelper.MODEL
                ,ClassCreateHelper.PRESENTER);
        ClassCreateHelper.createImplClass(
                _path, className
                , classFullName, ClassCreateHelper.PRESENTER
                ,ClassCreateHelper.PRESENTER);
        ClassCreateHelper.createInterface(_path,className,classFullName,ClassCreateHelper.MODEL);


        ClassCreateHelper.createInterface(_path,className,classFullName,ClassCreateHelper.VIEW);
    }

    /**
     * 以contract模式生成 .java文件
     * @throws IOException
     */
    private void createFileWithContract() throws IOException {
        String className = _classModel.get_className();
        String classFullName = _classModel.get_classFullName();
        System.out.println("_path:" + _path);


        // create presenter file
        ClassCreateHelper.createImplClass(_path
                , className
                , classFullName, ClassCreateHelper.MODEL
        ,ClassCreateHelper.CONTRACT);


        // create presenter file
        ClassCreateHelper.createImplClass(
                _path, className
                , classFullName, ClassCreateHelper.PRESENTER
        ,ClassCreateHelper.CONTRACT);
    }


    /**
     * 生成 contract类内容
     * create Contract Model Presenter
     */
    private void createFiles() {
        if (null == _classModel.get_className()) {
            return;
        }

         _path= ClassCreateHelper.getCurrentPath(_event,_classModel.get_classFullName());
        if(_path.contains("contract")) {
            System.out.println("_path replace contract "+ _path);
            _path = _path.replace("contract/", "");
        }else if(_path.contains("presenter")){

            System.out.println("_path replace contract "+ _path);
            _path = _path.replace("presenter/","");

        }
        else {
            if(mode == MODE_CONTRACT) {
                MessagesCenter.showErrorMessage("Your Contract should in package 'contract'.", "error");
            }else if(mode == MODE_PRESENTER){
                MessagesCenter.showErrorMessage("Your Presenter should in package 'presenter'.", "error");
            }
                canCreate = false;
        }
        if(canCreate) {
            if(mode == MODE_CONTRACT){
            setFileDocument();
        }
        }

    }

    /**
     * 生成 contract类内容
     * create Contract Model Presenter
     */
    private void setFileDocument() {


        int lastIndex = _content.lastIndexOf("}");
        _content = _content.substring(0, lastIndex);
        MessagesCenter.showDebugMessage(_content, "debug");
        final String content = setContractContent();
        //wirte in runWriteAction
        WriteCommandAction.runWriteCommandAction(_editor.getProject(),
                new Runnable() {
                    @Override
                    public void run() {
                        _editor.getDocument().setText(content);
                    }
                });

    }

    private String setContractContent() {
        String className = _classModel.get_className();
        String content = _content + "public interface "  + "View{\n}\n\n"
                + "public interface "  + "Presenter{\n}\n\n"
                + "public interface "  + "Model{\n}\n\n"
                + "\n}";

        return content;
    }


    private void getClassModel() {
        _content = _editor.getDocument().getText();

        String[] words = _content.split(" ");

        for (String word : words) {
            if (word.contains("Contract")) {
                String className = word.substring(0, word.indexOf("Contract"));
                _classModel.set_className(className);
                _classModel.set_classFullName(word);
                MessagesCenter.showDebugMessage(className, "class name");
                mode = MODE_CONTRACT;
            }else if(word.contains("Presenter")){


                String className = word.substring(0, word.indexOf("Presenter"));
                _classModel.set_className(className);
                _classModel.set_classFullName(word);
                mode = MODE_PRESENTER;
                MessagesCenter.showDebugMessage(className, "class name");
            }
        }
        if (null == _classModel.get_className()) {
            MessagesCenter.showErrorMessage("Create failed ,Can't found 'Contract' or 'Presenter' in your class name,your class name must contain 'Contract' or 'Presenter'", "error");
            canCreate = false;
        }
    }

    private void init(AnActionEvent e) {
        _editor = e.getData(PlatformDataKeys.EDITOR);
        _classModel = new ClassModel();
    }


}
