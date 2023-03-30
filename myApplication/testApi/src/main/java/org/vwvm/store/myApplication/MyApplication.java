package org.vwvm.store.myApplication;

import org.vwvm.store.mySpringBoot.MySpringApplication;
import org.vwvm.store.mySpringBoot.MySpringBootApplication;

/**
 * <h3>store</h3>
 * <p>启动类</p>
 *
 * @author : BlackBox
 * @date : 2023-03-23 22:08
 **/
@MySpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        MySpringApplication.run(MyApplication.class);
    }
}
