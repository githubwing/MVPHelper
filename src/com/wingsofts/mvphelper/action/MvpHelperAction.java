package com.wingsofts.mvphelper.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.wingsofts.mvphelper.biz.checker.Checker;
import com.wingsofts.mvphelper.biz.checker.impl.EnvironmentChecker;

/**
 * The entry of this plugin.
 * <p>
 * If you want to contribute to this myProject, please follow
 * <a href="http://www.jetbrains.org/intellij/sdk/docs/">official tutorial</a>.<br/>
 * <a href="https://upsource.jetbrains.com/idea-ce">Online source code</a> is also available.<br/>
 * <a href="https://upsource.jetbrains.com/idea-ce/file/idea-ce-10df87d7a9840e5901d4901ac4fff7ba035501c2/java/java-impl/src/com/intellij/ide/actions/CreateClassAction.java">
 * CreateClassAction</a>
 * </p>
 *
 * @author DengChao
 * @since 2017/4/9
 */
public class MvpHelperAction extends AnAction {

    @Override
    public void update(AnActionEvent event) {//before actionPerformed(AnActionEvent)
        super.update(event);
        //Set visibility only in case of existing project and editor
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        Project project = event.getData(CommonDataKeys.PROJECT);
        //Set visibility only in '*.java' files.
        PsiFile psiFile = event.getData(CommonDataKeys.PSI_FILE);

        Presentation presentation = event.getPresentation();
        if (editor != null && project != null && psiFile instanceof PsiJavaFile) {
            presentation.setEnabledAndVisible(true);
        } else {
            presentation.setEnabledAndVisible(false);
        }
    }

    @Override
    public void actionPerformed(AnActionEvent event) {
        Checker checker = new EnvironmentChecker(event);
        if (!checker.hasSuffix()) {
            Messages.showErrorDialog(
                    "Generation failed, " +
                            "your class name MUST END WITH 'Contract' or 'Presenter'.",
                    "Class Name Error");
            return;
        } else if (!checker.isInRightPlace()) {
            Messages.showErrorDialog(
                    "You didn't place the 'Contract' or 'Presenter' under a right package.",
                    "Package Error");
            return;
        }
        checker.start();
    }
}
