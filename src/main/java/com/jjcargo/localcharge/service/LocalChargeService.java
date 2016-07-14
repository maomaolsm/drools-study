package com.jjcargo.localcharge.service;

import com.jjcargo.localcharge.domain.Cargo;
import com.jjcargo.localcharge.support.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Lingfeng on 2016/4/21.
 */
public class LocalChargeService {

    /**
     * 初始化计算规则
     * @return
     */
    public static String initRules() {
        String agent = "agent*bl";
        String loading = "MAX(IF(lbs/100<25000,0.0,lbs/100*loading1),BETWEEN(lbs/100*loading2,97.1,512))";
        String dad = "dad*bl";
        String doc = "doc*bl";
        String insurance = "insurance*bl";
        String warehouse = "warehouse*bl";
        String equipment = "MAX(15.0,equipment*rt)";
        String chassis = "MAX(15.0,chassis*rt)";
        String fuel = "MAX(15.0,fuel*rt)";
        String cbp = "cbp*bl";
        String tollCharge = "MAX(10.0,tollCharge*rt)";
        String portSecurity = "portSecurity*bl";
        String itFree = "IF(ipi,itFree*bl,0.0)";

        List<String> rules = new ArrayList<>();
        rules.add(agent);
        rules.add(loading);
        rules.add(dad);
        rules.add(doc);
        rules.add(insurance);
        rules.add(warehouse);
        rules.add(equipment);
        rules.add(chassis);
        rules.add(fuel);
        rules.add(cbp);
        rules.add(tollCharge);
        rules.add(portSecurity);
        rules.add(itFree);

        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = rules.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append("+");
            }
        }

        return builder.toString();
    }

    public static void initFees(Expression expression) {
        expression.setVariable("agent", BigDecimal.valueOf(75.0));
        expression.setVariable("loading1", BigDecimal.valueOf(3.69));
        expression.setVariable("loading2", BigDecimal.valueOf(5.12));
        expression.setVariable("dad", BigDecimal.valueOf(35.0));
        expression.setVariable("doc", BigDecimal.valueOf(23.5));
        expression.setVariable("insurance", BigDecimal.valueOf(20.0));
        expression.setVariable("warehouse", BigDecimal.valueOf(23.5));
        expression.setVariable("equipment", BigDecimal.valueOf(5.0));
        expression.setVariable("chassis", BigDecimal.valueOf(5.0));
        expression.setVariable("fuel", BigDecimal.valueOf(5.0));
        expression.setVariable("cbp", BigDecimal.valueOf(30.0));
        expression.setVariable("tollCharge", BigDecimal.valueOf(3.0));
        expression.setVariable("portSecurity", BigDecimal.valueOf(35.0));
        expression.setVariable("itFree", BigDecimal.valueOf(35.0));
    }

    /**
     * 初始化货物信息
     * @param expression
     * @param cargo
     */
    public static void initCargo(Expression expression, Cargo cargo) {
        expression.setVariable("bl", BigDecimal.valueOf(cargo.getBillNum()));
        expression.setVariable("pkg", BigDecimal.valueOf(cargo.getPackageNum()));
        expression.setVariable("cbm", BigDecimal.valueOf(cargo.getCbm()));
        expression.setVariable("kgs", BigDecimal.valueOf(cargo.getWeightKgs()));
        expression.setVariable("lbs", BigDecimal.valueOf(cargo.getWeightLbs()));
        expression.setVariable("rt", BigDecimal.valueOf(cargo.getRevenueTon()));
        expression.setVariable("ipi", String.valueOf(cargo.isTranshipment()));
    }
}
