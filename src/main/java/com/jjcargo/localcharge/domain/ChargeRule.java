package com.jjcargo.localcharge.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Lingfeng on 2016/4/21.
 */
@Data
public class ChargeRule {

    private String name;

    private String currency;

    private Map<String, BigDecimal> terminalClientFees;

    private Map<String, BigDecimal> coLoaderFees;

    private String rule;
}
