package cn.fishy.plugin.idea.auto.util;

import java.util.regex.Pattern;

/**
 * 内容匹配类
 * User: duxing
 * Date: 2015.07.26 23:51
 */
public class RegexUtil {
    public static Pattern[] compilePatterns(String[] inputs) {
        if (inputs == null) return null;
        Pattern[] patterns = new Pattern[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = escapeStringLiteralForWildCard(inputs[i].trim());
            inputs[i] = inputs[i].replace("*", "(.*)").replace("?", "(.{1})");
            Pattern p = Pattern.compile(inputs[i], Pattern.CASE_INSENSITIVE);
            patterns[i] = p;
        }
        return patterns;
    }

    /**
     * 判断传入的uri是否满足patter
     *
     * @param exclusionPatterns
     * @param uri
     * @return
     */
    public static boolean isMatched(Pattern[] exclusionPatterns, String uri) {
        if (exclusionPatterns != null) {
            uri = uri.trim();
            for (Pattern exclusionPattern : exclusionPatterns) {
                if (isWildCardMatched(uri, exclusionPattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 对指定的文本进行模糊匹配，支持* 和?，不区分大小写
     *
     * @param text 要进行模糊匹配的文本
     * @param pattern 模糊匹配表达式
     * @return
     */
    public static boolean isWildCardMatched(String text, Pattern pattern) {
        java.util.regex.Matcher m = pattern.matcher(text);
        return m.matches();
    }

    public static String escapeStringLiteralForWildCard(String original) {
        if (original == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < original.length(); i++) {
            char curChar = original.charAt(i);
            boolean stillAppend = true;
            switch (curChar) {
                case '$':
                case '(':
                case ')':
                case '+':
                case '.':
                case '[':
                case '\\':
                case '^':
                case '{':
                case '|':
                    result.append('\\');
                    break;
                case '\r':
                    result.append('\\').append('r');
                    stillAppend = false;
                    break;
                case '\n':
                    result.append('\\').append('n');
                    stillAppend = false;
                    break;
                case '\t':
                    result.append('\\').append('t');
                    stillAppend = false;
                    break;
                case '\f':
                    result.append('\\').append('f');
                    stillAppend = false;
                    break;
                case '\000':
                    result.append('\\').append('0');
                    stillAppend = false;
            }

            if (stillAppend) {
                result.append(curChar);
            }
        }
        return result.toString();
    }
}