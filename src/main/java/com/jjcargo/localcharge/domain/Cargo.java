package com.jjcargo.localcharge.domain;

import com.jjcargo.localcharge.support.Expression;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Lingfeng on 2016/4/21.
 */
@Data
public class Cargo {

    public static final double KG_TO_LBS_CHANGE = 2.20462;

    // 票数
    private long billNum = 0L;

    // 件数
    private long packageNum = 0L;

    // 体积
    private double cbm = 0.0;

    // 毛重(单位: kgs)
    private double weightKgs = 0.0;

    // 毛重(单位: lbs)
    private double weightLbs = 0.0;

    // 计费重量
    private double revenueTon = 0.0;

    // 是否托盘货
    private boolean pallet = false;

    // 是否转运
    private boolean transhipment = false;

    private BigDecimal totalCharge = BigDecimal.ZERO;

    public Cargo() {
    }

    /**
     * 构造方法
     *
     * @param billNum 票数
     * @param packageNum 件数
     * @param cbm 体积
     * @param weightKgs 毛重
     * @param pallet 是否托盘
     * @param transhipment 是否转运
     */
    public Cargo(long billNum, long packageNum,
                 double cbm, double weightKgs,
                 boolean pallet, boolean transhipment) {
        this.billNum = billNum;
        this.packageNum = packageNum;
        this.cbm = cbm;
        this.weightKgs = weightKgs;
        this.pallet = pallet;
        this.transhipment = transhipment;

        Expression kgs2LbsExp = new Expression("kgs*change");
        kgs2LbsExp.setVariable("kgs", BigDecimal.valueOf(this.weightKgs));
        kgs2LbsExp.setVariable("change", BigDecimal.valueOf(KG_TO_LBS_CHANGE));
        this.weightLbs = kgs2LbsExp.eval().doubleValue();

        Expression revenueTonExp = new Expression("MAX(1,MAX(kgs/1000,cbm))");
        revenueTonExp.setVariable("kgs", BigDecimal.valueOf(this.weightKgs));
        revenueTonExp.setVariable("cbm", BigDecimal.valueOf(this.cbm));
        this.revenueTon = revenueTonExp.eval().doubleValue();
    }

    private double getMax(double v1, double v2) {
        if (v1 > v2) {
            return v1;
        } else {
            return v2;
        }
    }
}
