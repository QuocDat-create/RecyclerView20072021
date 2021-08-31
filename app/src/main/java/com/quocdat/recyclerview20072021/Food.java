package com.quocdat.recyclerview20072021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Food {
    private String name;
    private long price;

    public Food( String name, long price) {
        this.name = name;
        this.price = price;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Food{" +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static List<Food> getMock(){
        return new ArrayList<>(Arrays.asList(
                new Food("Bún bò Huế", 40000),
                new Food( "Trà sữa trân châu đường đen", 30000),
                new Food("Hủ tiếu Nam Vang", 50000),
                new Food( "Cơm sườn", 60000),
                new Food("Bún đậu mắm tôm", 45000)
        ));
    }
}
