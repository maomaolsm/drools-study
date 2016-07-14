package com.drools.test;

import com.jjcargo.localcharge.domain.Cargo;
import com.jjcargo.localcharge.service.LocalChargeService;
import com.jjcargo.localcharge.support.Expression;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Lingfeng on 2016/4/21.
 */
public class CargoTest {

    public static void main(String[] args) throws IOException {
        Cargo cargo = new Cargo(1L, 0L, 0.0, 0.0, false, true);

        Expression expression = new Expression(LocalChargeService.initRules());
        LocalChargeService.initFees(expression);
        LocalChargeService.initCargo(expression, cargo);
        /*expression.setPrecision(2);*/

        BigDecimal result = expression.eval();
        System.out.println(result);
    }
}
