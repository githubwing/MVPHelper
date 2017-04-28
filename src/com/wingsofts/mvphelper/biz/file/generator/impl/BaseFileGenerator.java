package com.wingsofts.mvphelper.biz.file.generator.impl;

import com.intellij.ide.fileTemplates.JavaTemplateUtil;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.wingsofts.mvphelper.biz.config.MvpConfigurable;
import com.wingsofts.mvphelper.biz.config.impl.MvpConfig;
import com.wingsofts.mvphelper.biz.file.generator.FileGenerator;

import static com.wingsofts.mvphelper.biz.EventLogger.log;

/**
 * The base file generator, provide basic functions for child-class.
 *
 * @author Administrator
 * @since 2017/4/24.
 */
@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
abstract class BaseFileGenerator implements FileGenerator {
    //    private final org.apache.log4j.Logger logger;
    protected Project myProject;//current java project
    protected PsiDirectory myContractDir;//the contract package dir
    protected PsiDirectory myModelDir;//the model package dir
    protected PsiDirectory myPresenterDir;//the presenter package dir
    protected String myPrefix;//the prefix used to identify each other
    protected PsiElementFactory myFactory;//the factory used to generate interface/class/innerClass/classReference
    protected JavaDirectoryService myDirectoryService;//the dirService used to generate files under particular dir(package)
    protected PsiShortNamesCache myShortNamesCache;//used to search a class in particular scope
    protected GlobalSearchScope myProjectScope;//just this project is enough
    private MvpConfigurable myConfig;

    BaseFileGenerator(Project project, PsiDirectory contractDir, PsiDirectory modelDir, PsiDirectory presenterDir, String prefix) {
        this.myProject = project;
        this.myContractDir = contractDir;
        this.myModelDir = modelDir;
        this.myPresenterDir = presenterDir;
        this.myPrefix = prefix;
        myShortNamesCache = PsiShortNamesCache.getInstance(project);
        myFactory = JavaPsiFacade.getElementFactory(project);
        myDirectoryService = JavaDirectoryService.getInstance();
        myProjectScope = GlobalSearchScope.projectScope(project);
        myConfig = new MvpConfig(project);
    }

    /**
     * Generate a java file
     *
     * @param directory the directory to place the file
     * @param fileName  the name of the file tobe generate
     * @param type      the type of the file
     * @param listener  when the file has been generated, then the listener will be called.
     * @see JavaTemplateUtil#INTERNAL_CLASS_TEMPLATES
     */
    protected void generateFile(final PsiDirectory directory, final String fileName, final String type, final onFileGeneratedListener listener) {
        WriteCommandAction.runWriteCommandAction(myProject, () -> {
            String fixedFileName = fileName;
            if (myConfig.hasSuffix() && type.equals(JavaTemplateUtil.INTERNAL_CLASS_TEMPLATE_NAME)) {
                //only sub-class need to fix name
                fixedFileName += myConfig.getSuffix();
            }

            PsiClass[] psiClasses = myShortNamesCache.getClassesByName(fixedFileName, myProjectScope);//NotNull
            PsiClass psiClass;
            PsiJavaFile javaFile;
            if (psiClasses.length != 0) {//if the class already exist.
                psiClass = psiClasses[0];
                javaFile = (PsiJavaFile) psiClass.getContainingFile();
                javaFile.delete();//then delete the old one
                log("BaseFileGenerator: " + fixedFileName + " old file deleted");
            }//and re-generate one
            psiClass = myDirectoryService.createClass(directory, fixedFileName, type);
            javaFile = (PsiJavaFile) psiClass.getContainingFile();
            PsiPackage psiPackage = myDirectoryService.getPackage(directory);
            javaFile.setPackageName(psiPackage.getQualifiedName());
            log("BaseFileGenerator: " + fixedFileName + " generated");
            listener.onJavaFileGenerated(javaFile, psiClass);
        });
    }


    @FunctionalInterface
    protected interface onFileGeneratedListener {
        /**
         * When the file has been generated, then the listener will be called.
         *
         * @param javaFile the PsiJavaFile generated just now
         * @param psiClass the corresponding PsiClass
         */
        void onJavaFileGenerated(PsiJavaFile javaFile, PsiClass psiClass);
    }
}
