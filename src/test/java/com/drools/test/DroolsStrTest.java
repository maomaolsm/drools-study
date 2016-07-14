package com.drools.test;

import com.drools.str.service.HelloDroolsService;

/**
 * String写引擎规则测试
 *
 * Created by Lingfeng on 2016/4/20.
 */
public class DroolsStrTest {

    public static void main(String[] args) {
        HelloDroolsService.init();
        HelloDroolsService.initMessage();
        HelloDroolsService.fireRules();
    }
}
