package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/8 10:23
 */
public abstract class AbstractFactory {

    abstract BenzCar getBenzCar();
    abstract TeslaCar getTeslaCar();
}
