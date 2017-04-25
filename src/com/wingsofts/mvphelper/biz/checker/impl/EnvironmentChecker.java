package com.wingsofts.mvphelper.biz.checker.impl;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiJavaFile;
import com.wingsofts.mvphelper.biz.checker.Checker;
import com.wingsofts.mvphelper.biz.dir.generator.DirGenerator;
import com.wingsofts.mvphelper.biz.dir.generator.impl.ContractModeDirGenerator;
import com.wingsofts.mvphelper.biz.dir.generator.impl.PresenterModeDirGenerator;

/**
 * The class used to check generation conditions and start file generation.
 *
 * @author Administrator
 * @since 2017/4/9
 */
@SuppressWarnings("ConstantConditions")
public class EnvironmentChecker implements Checker {
    private static final String CONTRACT = "Contract";
    private static final String PRESENTER = "Presenter";
    private final AnActionEvent actionEvent;
    private String className = "";//Never NPE, =)
    private String packageName = "";
    private Mode mode;
    private String prefix;

    public EnvironmentChecker(AnActionEvent actionEvent) {
        this.actionEvent = actionEvent;
        PsiJavaFile javaFile = (PsiJavaFile) actionEvent.getData(CommonDataKeys.PSI_FILE);
        packageName = javaFile.getPackageName();
        String fileName = javaFile.getName();
        className = fileName.substring(0, fileName.indexOf(".java"));
    }

    @Override
    public boolean hasSuffix() {
        if (className.endsWith(CONTRACT)) {
            mode = Mode.CONTRACT;
            prefix = className.replace(CONTRACT, "");
            return true;
        } else if (className.endsWith(PRESENTER)) {
            mode = Mode.PRESENTER;
            prefix = className.replace(PRESENTER, "");
            return true;
        }
        return false;
    }

    @Override
    public boolean isInRightPlace() {//split packageName by '.'
        String[] subPackages = packageName.split("\\.");
        if (mode == Mode.CONTRACT) {
            for (String subPackage : subPackages) {
                if (subPackage.endsWith("contract")) {
                    return true;
                }
            }
        } else if (mode == Mode.PRESENTER) {
            for (String subPackage : subPackages) {
                if (subPackage.endsWith("presenter")) {
                    return true;
                }
            }
        }//A Presenter-class not in presenter-package
        return false;
    }

    @Override
    public void start() {
        DirGenerator dirGenerator;
        if (mode == Mode.CONTRACT) {
            dirGenerator = new ContractModeDirGenerator(actionEvent, prefix);
        } else {// mode == Mode.PRESENTER
            dirGenerator = new PresenterModeDirGenerator(actionEvent, prefix);
        }
        dirGenerator.start();
    }

}
