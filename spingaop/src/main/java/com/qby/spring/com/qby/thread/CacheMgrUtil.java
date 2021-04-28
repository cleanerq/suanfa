package com.qby.spring.com.qby.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CacheMgrUtil {
    private static Logger log = LoggerFactory.getLogger(CacheMgrUtil.class);
    /**
     * 线程本地 公司ID 注意 请求结束后要调用清空方法
     */
    private static ThreadLocal<String> companyIdLocal = new ThreadLocal<String>();

    /**
     * 线程本地接口ID
     */
    private static ThreadLocal<String> interfaceIdLocal = new ThreadLocal<String>();
    private static String CAISSA_COMPANY = "caissa";

    public static String getInterfaceIdLocal() {
        return interfaceIdLocal.get();
    }

    public static String getCompanyIdLocal() {
        return companyIdLocal.get();
    }

    /**
     * 设置本地线程的公司ID和支付接口ID
     *
     * @param companyId
     * @param interfaceId
     */
    public static void setIdLocal(String companyId, String interfaceId) {
        companyIdLocal.set(companyId);
        interfaceIdLocal.set(interfaceId);
    }

    public static void clearIdLocal() {
        companyIdLocal.remove();
        interfaceIdLocal.remove();
    }

    /**
     * 1、取得公司ID
     * <p>
     * 2、判断如果是caissa或者是空值则使用默认取值
     * <p>
     * 3、如果不是caissa则使用公司ID参数取值
     *
     * @param key
     * @return
     */
    public static String getSystemPropertyValue(String key) {
        String companyId = getCompanyIdLocal();
        String interfaceId = getInterfaceIdLocal();
        log.info("companyId:{} interfaceId：{} 使用公司ID缓存取值", companyId,
                interfaceId);
        return companyId + " " + interfaceId;
    }

    public static String getSystemPropertyValue(String key, String companyId,
                                                String interfaceId) {
        return "";
    }

    public static List<String> getSystemProperties(String key) {

        return null;
    }
}
