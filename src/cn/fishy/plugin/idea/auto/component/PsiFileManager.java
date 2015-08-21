package cn.fishy.plugin.idea.auto.component;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.codeStyle.CodeStyleManager;
import org.jetbrains.annotations.NotNull;

/**
 * PsiClassManager
 *
 * User: duxing
 * Date: 2015.8.17 12:09:18
 */
public class PsiFileManager implements ProjectComponent {

    Project project;
    PsiManager psiManager;
    CodeStyleManager codeStyleManager;
    LocalFileSystem localFileSystem;

    public PsiFileManager(Project project,
                          PsiManager psiManager,
                          CodeStyleManager codeStyleManager,
                          LocalFileSystem localFileSystem) {
        this.project = project;
        this.psiManager = psiManager;
        this.localFileSystem = localFileSystem;
        this.codeStyleManager = codeStyleManager;
    }

    public static PsiFileManager getInstance(Project project){
        return project.getComponent(PsiFileManager.class);
    }

    public PsiClass findPrimaryClass(String filePath) {
        debug("findPrimaryClass filePath=" + filePath);
        if (filePath == null) return null;
        VirtualFile vFile = localFileSystem.findFileByPath(filePath);
        if (vFile == null) return null;
        PsiFile psiFile = psiManager.findFile(vFile);
        return findPrimaryClass(psiFile);
    }

    public PsiFile findPsiFile(String filePath) {
        debug("findPsiFile filePath=" + filePath);
        if (filePath == null) return null;
        VirtualFile vFile = localFileSystem.findFileByPath(filePath);
        if (vFile == null) return null;
        PsiFile psiFile = psiManager.findFile(vFile);
        return psiFile;
    }

    public PsiClass findPrimaryClass(PsiFile psiFile) {
        if (psiFile == null || !(psiFile instanceof PsiJavaFile)) return null;
        PsiJavaFile javaFile = (PsiJavaFile) psiFile;
        PsiClass[] classes = javaFile.getClasses();
        String filePrimaryClassName = getPrimaryClassNameFromJavaFileName(javaFile.getName());
        for (int i = 0; i < classes.length; i++) {
            PsiClass aClass = classes[i];
            if (filePrimaryClassName.equals(aClass.getName())) {
                return aClass;
            }
        }
        return null;
    }

    public static String getPrimaryClassNameFromJavaFileName(String name) {
        return name.substring(0, name.length() - ".java".length());
    }



    private void debug(String message) {
        if (LOG.isDebugEnabled()) {
            LOG.debug(message);
        }
    }

    public PsiManager getPsiManager() {
        return psiManager;
    }

    private static final Logger LOG = Logger.getInstance("cn.fishy.plugin.idea.auto.component.PsiClassManager");

    @Override
    public void projectOpened() {

    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "cn.fishy.plugin.idea.auto.component.PsiClassManager";
    }
}
