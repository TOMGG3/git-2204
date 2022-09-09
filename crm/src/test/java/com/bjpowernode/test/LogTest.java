package com.bjpowernode.test;

import org.apache.log4j.Logger;
import org.junit.Test;


public class LogTest {

    private Logger logger = Logger.getLogger(LogTest.class);

    @Test
    public void run(){
        //记录日志
        logger.error("错误日志...");
        logger.warn("警告日志...");
        logger.info("信息日志...");
        logger.debug("调试日志...");
        logger.trace("底层日志...");
    }
}
