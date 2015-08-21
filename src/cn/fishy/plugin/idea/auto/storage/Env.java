package cn.fishy.plugin.idea.auto.storage;

import cn.fishy.plugin.idea.auto.domain.Encoding;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.openapi.vfs.encoding.EncodingManager;
import com.intellij.openapi.vfs.encoding.EncodingProjectManager;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;

/**
 * User: duxing
 * Date: 2015-08-15 14:49
 */
public class Env {
    public static String sp = System.getProperty("file.separator");
    public static Project project;

    public static Charset encodeTo = Charset.forName(Encoding.UTF8.getName());
    public static Charset encodeFrom = Charset.forName(Encoding.UTF8.getName());

    @NotNull
    public static Charset getProjectCharset() {
        try {
            return EncodingProjectManager.getInstance(project).getDefaultCharset();
        }catch (Exception e){
            try{
                return Env.project.getBaseDir().getCharset();
            }catch (Exception e1){
                return Charset.forName("UTF-8");
            }
        }
    }

    @NotNull
    public static Charset getIDECharset() {
        Charset ideCharset;
        try {
            ideCharset = EncodingManager.getInstance().getDefaultCharset();
        }catch (Exception e){
            try {
                ideCharset = CharsetToolkit.getDefaultSystemCharset();
            }catch (Exception e1){
                ideCharset = Charset.forName("UTF-8");
            }
        }
        return ideCharset;
    }

    @NotNull
    public static Charset getCharsetFromEncoding(String encoding) {
        try {
            return Charset.forName(encoding);
        }catch (Exception e){
            return Charset.defaultCharset();
        }
    }
}
