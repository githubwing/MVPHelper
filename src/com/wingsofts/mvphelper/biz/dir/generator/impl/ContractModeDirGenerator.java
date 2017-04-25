package com.wingsofts.mvphelper.biz.dir.generator.impl;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.wingsofts.mvphelper.biz.file.generator.impl.JavaModeFileGenerator;
import org.jetbrains.annotations.NotNull;

/**
 * @author Administrator
 * @since 2017/4/9
 */
public class ContractModeDirGenerator extends BaseDirGenerator {

    public ContractModeDirGenerator(AnActionEvent actionEvent, String prefix) {
        super(actionEvent, prefix);
    }

    /**
     * start generation
     */
    @Override
    public void start() {
        generateDirsBasedOnSuffix("contract");

        new JavaModeFileGenerator(myProject, myContractDir, myModelDir, myPresenterDir, myPrefix).start();
    }

    @Override
    protected void onGenerateForkDirs(@NotNull String subPackage) {
        String prefix = subPackage.replace("contract", "");
        String subPackageM = prefix + "model";
        String subPackageP = prefix + "presenter";//if prefix exist.
        myContractDir = myCurrentDir.findSubdirectory(subPackage);
        myModelDir = moveDirPointer(myCurrentDir, subPackageM);
        myPresenterDir = moveDirPointer(myCurrentDir, subPackageP);
    }
}
