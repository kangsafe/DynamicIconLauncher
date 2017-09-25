package com.ks.dynamic.icon.launcher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText numInput = (EditText) findViewById(R.id.numInput);

        Button button = (Button) findViewById(R.id.btnSetBadge);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(numInput.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
                }

                boolean success = ShortcutBadger.applyCount(MainActivity.this, badgeCount);

                Toast.makeText(getApplicationContext(), "Set count=" + badgeCount + ", success=" + success, Toast.LENGTH_SHORT).show();
            }
        });

        Button launchNotification = (Button) findViewById(R.id.btnSetBadgeByNotification);
        launchNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int badgeCount = 0;
                try {
                    badgeCount = Integer.parseInt(numInput.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Error input", Toast.LENGTH_SHORT).show();
                }

                finish();
                startService(
                        new Intent(MainActivity.this, BadgeIntentService.class).putExtra("badgeCount", badgeCount)
                );
            }
        });

        Button removeBadgeBtn = (Button) findViewById(R.id.btnRemoveBadge);
        removeBadgeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean success = ShortcutBadger.removeCount(MainActivity.this);

                Toast.makeText(getApplicationContext(), "success=" + success, Toast.LENGTH_SHORT).show();
            }
        });


        //find the home launcher Package
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfo = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String currentHomePackage = resolveInfo.activityInfo.packageName;

        TextView textViewHomePackage = (TextView) findViewById(R.id.textViewHomePackage);
        textViewHomePackage.setText("launcher:" + currentHomePackage);

//        try {
//            Class clazz = Class.forName("com.lenovo.launcherhd");
//            System.out.println("------获取类名-----------");
//            System.out.println(clazz.toString());// 获取类的完整名
//            System.out.println(clazz.getSimpleName());// 获取类名
//            System.out.println("------获取类中的构造方法-----------");
//            // 获得所有构造方法
//            Constructor[] cons = clazz.getDeclaredConstructors();
//            for (int i = 0; i < cons.length; i++) {
//                System.out.println("所有构造方法之" + cons[i].getName());
//                // 方法修饰符
//                System.out.println("修饰符类型：" + cons[i].getModifiers());
//            }
//            // 获得所有公共构造方法
//            Constructor[] cons1 = clazz.getConstructors();
//            for (int i = 0; i < cons1.length; i++) {
//                System.out.println("公共构造方法之" + cons1[i].getName() + "--");
//                System.out.println("修饰符类型：" + cons1[i].getModifiers());
//            }
//            System.out.println("-----获取类中定义的方法------------");
//            Method[] mths = clazz.getDeclaredMethods();
//            for (int i = 0; i < mths.length; i++) {
//                System.out.println("所有方法之" + mths[i].getName());
//                System.out.println("修饰符类型：" + mths[i].getModifiers());
//            }
//            Method[] mths1 = clazz.getMethods();
//            for (int i = 0; i < mths1.length; i++) {
//                System.out.println("公有方法之" + mths1[i].getName());
//                System.out.println("修饰符类型：" + mths1[i].getModifiers());
//            }
//            System.out.println("-------获取所有的属性------------");
//            Field[] fls = clazz.getFields();
//            for (int i = 0; i < fls.length; i++) {
//                System.out.println("公有属性之" + fls[i].getName());
//                System.out.println("修饰符类型：" + fls[i].getModifiers());
//            }
//            Field[] fls1 = clazz.getDeclaredFields();
//            for (int i = 0; i < fls1.length; i++) {
//                System.out.println("所有属性之" + fls1[i].getName());
//                System.out.println("修饰符类型：" + fls1[i].getModifiers());
//            }
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        List<String> arr = Collections.singletonList("com.lenovo.launcherhd");
//        for (String m : arr
//                ) {
//            System.out.println("-------" + m + "------------");
//        }
    }
}
