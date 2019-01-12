package com.liger.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.liger.practice.base.BaseActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http://www.runoob.com/java/java-regular-expressions.html
 *
 * @author zs
 * @date 2018/8/21 0021.
 */
public class RegexActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regex);
        mTextView = findViewById(R.id.activity_test_tv);

//        regex1();
//        regex2();
//        regex3();
        regex4();
//        regex5();
//        regex6();
    }

    private void regex6() {
//        Pattern mEmojiPattern = Pattern.compile("\\[([\u4e00-\u9fa5\\w])+\\]");
        String source = "abc[笑哭]abc[笑哭]edf[笑哭]hg";
        Pattern pattern = Pattern.compile("\\[([\\u4e00-\\u9fa5])+\\]");
        String s = "[()]";
        Matcher matcher = pattern.matcher(source);
        if (matcher.find()) {
            String result = matcher.group();
            int count = matcher.groupCount();
            Log.d(TAG, "regex6: result = " + result + " count = " + count);
        }

        if (matcher.matches()) {
            Log.d(TAG, "regex6: matches");
        }
    }

    /**
     * 一个普通的反斜杠是 \\\\
     */
    private void regex5() {
        Pattern pattern = Pattern.compile(".*\\\\.*");
        Matcher matcher = pattern.matcher("abc\\def");
        if (matcher.find()) {
            Log.d(TAG, "regex5: " + matcher.group());
        }
    }

    /**
     * 捕获组
     * group(0) 代表整个表达式，不包括在 groupCount 的返回值中
     */
    private void regex4() {
        // 按指定模式在字符串查找
        String line = "This 200 order was placed for QT3000! OK?";
//        String patternStr = "(\\D*)(\\d+)(.*)";
//        String patternStr = "(\\D)(\\d+)(.)";
        String patternStr = "([^0-9]*)([0-9]+)(.*)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(line);
        int groupCount = matcher.groupCount();
        Log.d(TAG, "regex4: groupCount = " + groupCount);

        if (matcher.find()) {
            for (int i = 0; i <= groupCount; i++) {
                Log.d(TAG, "regex4: group" + i + " " + matcher.group(i));
            }
        }
    }

    private void regex3() {
        Pattern pattern = Pattern.compile(".*abcde.*");
        Matcher matcher = pattern.matcher("字符串中是否包含了abcde字符串");
        if (matcher.matches()) {
            Log.d(TAG, "regex3: matches " + matcher.group());
        }
        if (matcher.find()) {
            Log.d(TAG, "regex3: find " + matcher.group());
        }
        boolean isMatch = Pattern.matches(".*abcde.*", "字符串中是否包含了abcde字符串");
    }

    private void regex2() {
        String input = "12345asd";
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(input);
        String result = matcher.replaceAll("#");
        Log.d(TAG, "regex2: result = " + result);

        if (matcher.matches()) {
            String str = matcher.group();
            Log.d(TAG, "regex2: str = " + str);
        }
        if (matcher.find()) {
            String str2 = matcher.group();
            Log.d(TAG, "regex2: str2 = " + str2);
        }
    }

    private void regex1() {
        String url = "<a href=\"https://google.com\"</a>";
        String s = "<a\\s+href";
        String patternStr = "<a\\s+href\\s*=\\s*(\"[^\\s>]*)\\s*>";
        Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String match = url.substring(start, end);
            Log.d(TAG, "onCreate: " + match);
        }
    }
}
