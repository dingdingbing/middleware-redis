package com.dingfubing.redispractice;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 方法区OOM
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * @author dingfubing
 * @since 2022/4/19 19:47
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            // 如果为true，则不会创建对象
            // enhancer.setUseCache(true);
            enhancer.setUseCache(false);
            enhancer.setCallback(
                (MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, objects));
            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
