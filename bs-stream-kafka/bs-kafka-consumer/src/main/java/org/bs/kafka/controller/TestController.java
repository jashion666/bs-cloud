package org.bs.kafka.controller;

import org.bs.kafka.producer.LogProducer;
import org.bs.kafka.producer.SmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :wkh
 */
@RestController
public class TestController {

    @Autowired
    private LogProducer logProducer;

    @Autowired
    private SmsProducer smsProducer;

    /**
     * 初始化页面
     * @return page
     */
    @RequestMapping({"/test"})
    public String index() {
        logProducer.send("111");
        smsProducer.send("222");
        return "";
    }
}
