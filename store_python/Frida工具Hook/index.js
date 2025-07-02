// import Java from "frida-java-bridge";

// hook_increment.js
Java.perform(function () {
    // 查找 MainActivity 类
    var MainActivityStr = "com.zotci.zhook.MainActivity"
    var MainActivity = Java.use(MainActivityStr);

    Java.choose(MainActivityStr, {
        onMatch: function (instance) {
            console.log("[+] 找到 MainActivity 实例:", instance);

            // 获取 Context 以访问资源
            var context = instance.getApplicationContext();
            var resources = context.getResources();
            var packageName = context.getPackageName();

            // 获取目标视图的资源ID
            var targetViewId = resources.getIdentifier("my_text", "id", packageName);

            if (targetViewId !== 0) {
                // 调用 findViewById 获取视图
                var view = instance.findViewById(targetViewId);
                console.log("[+] 成功获取视图:", view);

                if (view) {
                    var TextView = Java.use("android.widget.TextView");
                    var textView = Java.cast(view, TextView);

                    // 创建 Java 字符串对象
                    var String = Java.use("java.lang.String");
                    var text = String.$new("1312313"); // 使用 $new() 创建 Java 字符串

                    // 显式调用重载方法
                    textView.setText.overload("java.lang.CharSequence").call(textView, text);
                    console.log("[+] 成功修改 TextView 内容");
                }
            } else {
                console.error("[-] 未找到目标视图资源ID，请检查名称");
            }
        },
        onComplete: function () {
            console.log('[-] 搜索完成');
        }
    });
});