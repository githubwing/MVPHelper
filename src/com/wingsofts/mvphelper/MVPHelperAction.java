package com.wingsofts.mvphelper;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

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
    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here

        this._event = e;
        canCreate = true;
        init(e);
        getClassModel();
        createFiles();

        try {
            if(canCreate) {
                createClassFiles();
                MessagesCenter.showMessage("created success! please wait a moment","success");
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
        String className = _classModel.get_className();
        String classFullName = _classModel.get_classFullName();
        System.out.println("_path:" + _path);
            ClassCreateHelper.CreateClass(_path
                    , className
                    , classFullName, ClassCreateHelper.MODEL
            );
            ClassCreateHelper.CreateClass(
                    _path, className
                    , classFullName, ClassCreateHelper.PRESENTER
            );

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
            _path = _path.replace("contract/", "");
        }else {
            MessagesCenter.showErrorMessage("Your Contract should in package 'contract'.","error");
            canCreate = false;
        }
        if(canCreate) {
            setFileDocument();
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
        final String content = setContent();
        //wirte in runWriteAction
        WriteCommandAction.runWriteCommandAction(_editor.getProject(),
                new Runnable() {
                    @Override
                    public void run() {
                        _editor.getDocument().setText(content);
                    }
                });

    }

    private String setContent() {
        String className = _classModel.get_className();
        String content = _content + "public interface " + className + "View{\n}\n\n"
                + "public interface " + className + "Presenter{\n}\n\n"
                + "public interface " + className + "Model{\n}\n\n"
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
            }
        }
        if (null == _classModel.get_className()) {
            MessagesCenter.showErrorMessage("Create failed ,Can't found 'Contract' in your class name,your class name must contain 'Contract'", "error");
            canCreate = false;
        }
    }

    private void init(AnActionEvent e) {
        _editor = e.getData(PlatformDataKeys.EDITOR);
        _classModel = new ClassModel();
    }


}
