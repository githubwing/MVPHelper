package com.wingsofts.mvphelper.biz.dir.generator.impl;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.impl.file.PsiDirectoryFactory;
import com.wingsofts.mvphelper.biz.dir.generator.DirGenerator;
import org.jetbrains.annotations.NotNull;

/**
 * @author DengChao
 * @see #moveDirPointer(PsiDirectory, String)
 * @see #generateDirsBasedOnSuffix(String)
 * @see #onGenerateForkDirs(String)
 * @since 2017/4/24.
 */
@SuppressWarnings({"ConstantConditions", "WeakerAccess"})
abstract class BaseDirGenerator implements DirGenerator {
    protected final Project myProject;
    protected final String myPackageName;//where the action happens
    protected PsiDirectory myCurrentDir;//the base line of dir generation
    protected PsiDirectory myContractDir;
    protected PsiDirectory myModelDir;
    protected PsiDirectory myPresenterDir;
    protected String myPrefix;

    BaseDirGenerator(@NotNull AnActionEvent actionEvent, @NotNull String prefix) {
        this.myPrefix = prefix;
        myProject = actionEvent.getData(PlatformDataKeys.EDITOR).getProject();
        PsiDirectory baseDir = PsiDirectoryFactory.getInstance(myProject).createDirectory(myProject.getBaseDir());
        myCurrentDir = baseDir.findSubdirectory("src");//locate myCurrentDir to "src" folder(dir)
        PsiJavaFile javaFile = (PsiJavaFile) actionEvent.getData(CommonDataKeys.PSI_FILE);
        myPackageName = javaFile.getPackageName();
    }

    /**
     * <i><b>Make sure to call this on sub-class#{@link #start()}'s first line</b></i>
     *
     * @param suffix the suffix of the package witch used to determine where should have a fork.
     */
    protected void generateDirsBasedOnSuffix(@NotNull String suffix) {
        boolean isForkDirGenerated = false;
        String[] subPackages = myPackageName.split("\\.");
        for (String subPackage : subPackages) {
            if (!subPackage.endsWith(suffix)) {//if the package does not end with 'contract':
                if (isForkDirGenerated) {// and the 'presenter' and 'model' dir already generated,
                    afterForkDirGenerated(subPackage);
                }// but the 'presenter' and 'model' dir has not been generated,
            } else {
                onGenerateForkDirs(subPackage);
                isForkDirGenerated = true;//update the flag
            }

            //the current dir is the base line, so
            //no matter what happens, move myCurrentDir pointer to it's child;
            myCurrentDir = moveDirPointer(myCurrentDir, subPackage);
        }
    }

    /**
     * Move the pointer to it's sub-package.<br/>
     * sub-package will be generate if it's not exist.
     *
     * @param subPackage the sub-package's name
     */
    @NotNull
    protected PsiDirectory moveDirPointer(@NotNull final PsiDirectory currentDir, @NotNull final String subPackage) {
        final PsiDirectory[] subDirectory = {currentDir.findSubdirectory(subPackage)};
        if (subDirectory[0] == null) {
            WriteCommandAction.runWriteCommandAction(currentDir.getProject(), () -> {
                subDirectory[0] = currentDir.createSubdirectory(subPackage);
            });
        }
        return subDirectory[0];
    }

    /**
     * Here the fork appears, sub-class have to implement this method to achieve particular work.<br/>
     * For example: subPackage = "Acontract"<br/>
     * Then this method should
     * <ol>
     * <li>Move {@link #myContractDir} to {@link #myCurrentDir}</li>
     * <li>Move {@link #myModelDir} to "Amodel"</li>
     * <li>Move {@link #myPresenterDir} to "Apresenter"</li>
     * </ol>
     *
     * @param subPackage the name of the dir which should have siblings
     * @see #moveDirPointer(PsiDirectory, String)
     */
    protected abstract void onGenerateForkDirs(@NotNull String subPackage);

    /**
     * After the fork dir generated, sub-class have to implement this method to achieve particular work.<br/>
     * For example: subPackage = "Acontract"<br/>
     * Then this method should
     * <ol>
     * <li>Move {@link #myContractDir} to "{@link #myCurrentDir}.subPackage"</li>
     * <li>Move {@link #myModelDir} to "Amodel.subPackage"</li>
     * <li>Move {@link #myPresenterDir} to "Apresenter.subPackage"</li>
     * </ol>
     *
     * @param subPackage the name of the dir to be generate under siblings
     */
    protected abstract void afterForkDirGenerated(@NotNull String subPackage);

}
