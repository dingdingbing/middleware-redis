package com.dingfubing.algorithm;

/**
 * TODO
 *
 * @author dingfubing
 * @since 2023/2/8 10:25
 */
public class BenzBusinessCar implements BenzCar{

    @Override
    public void gasUp() {
        System.out.println("benz business car need low gas");
    }
}
