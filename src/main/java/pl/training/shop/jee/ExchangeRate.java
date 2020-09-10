package pl.training.shop.jee;

import org.javamoney.moneta.FastMoney;

import javax.ejb.Local;

@Local
public interface ExchangeRate {

    double get(FastMoney value);

}
