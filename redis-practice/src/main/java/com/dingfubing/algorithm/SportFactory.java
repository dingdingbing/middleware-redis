package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/8 10:24
 */
public class SportFactory extends AbstractFactory{

    @Override
    BenzCar getBenzCar() {
        return new BenzSportCar();
    }

    @Override
    TeslaCar getTeslaCar() {
        return new TeslaSportCar();
    }
}
