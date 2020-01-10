package com.example.demo.model.warehouse;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.UUID;

public final class CreateNumber {
    /**
     * 商品编号号生成计数器
     */
    private static long numberCount = 0L;
    /**
     * 每毫秒生成商品编号数量最大峰值
     */
    private static final int maxPerMSECSize = 20000;

    /**
     * 生成编号
     *
     * @param lock 生成的UUID32位参数
     * @return
     */
    public static String make() {
        ReferenceQueue<StringBuilder> queue = new ReferenceQueue<StringBuilder>();
        WeakReference<StringBuilder> weakRef = new WeakReference<StringBuilder>(new StringBuilder(15), queue);
        synchronized (weakRef) {
            if (null == weakRef.get()) {
                weakRef = new WeakReference<StringBuilder>(new StringBuilder(15), queue);
            }
            if (numberCount >= maxPerMSECSize) { // 计数器到最大值归零,目前1毫秒处理峰值1个
                numberCount = 0L;
            }
            weakRef.get().append(Math.abs(UUID.randomUUID().toString().hashCode()));// HASH-CODE
            weakRef.get().append(numberCount++);// 计数器的值
            return weakRef.get().toString();
        }
    }
}
