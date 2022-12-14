/*
 * Copyright (C) 2022 albert
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pmproject.pm.data;

import java.io.Serializable;
import java.math.BigDecimal;
import static java.math.RoundingMode.HALF_UP;
import java.time.LocalDate;
import java.util.Objects;
import static pmproject.pm.data.Rating.*;

/**
 * (@code Product) class represents propierties and behaiours of product objects
 * in the Product Management System. <br>
 * Each product has an id, name and price.<br>
 * Each product can have a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate} <br>
 *
 * @version 4.0 (My version 1.0)
 * @author albert
 */
public abstract class Product implements Rateable<Product>, Serializable {

    /**
     * A constant that defines a {@link java.math.BigDecimal} value of the
     * discount rate.<br>
     * Dscount rate is 10%.
     */
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

//    Product() {
//        this(0, "no name", BigDecimal.ZERO);
//    }
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

//    Product(int id, String name, BigDecimal price) {
//        this(id, name, price, NOT_RATED);
//    }
    public int getId() {
        return id;
    }

//    public void setId(final int id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }

//    public void setName(final String name) {
//        this.name = name;
//    }
    public BigDecimal getPrice() {
        return price;
    }

//    public void setPrice(final BigDecimal price) {
////        price = BigDecimal.ONE;
//        this.price = price;
//    }
    /**
     * Calculates discount based on a product price and
     * {@link DISCOUNT_RATE discount rate}<br>
     *
     * @return {@link java.math.BigDecimal BigDecimal} value of the discount
     */
    public BigDecimal getDiscount() {
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);

    }

    @Override
    public Rating getRating() {
        return rating;
    }

//    public abstract Product applyRating(Rating newRating);
//    {
//        return new Product(this.id, this.name, this.price, newRating);
//    }
    /**
     * Assumes that the bestBefore date is today
     *
     * @return the current date
     */
    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + price + ", " + getDiscount() + ", " + rating.getStars() + ", " + getBestBefore();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj instanceof Product) {
//            final Product other = (Product) obj;
//            return this.id == other.id && Objects.equals(this.name, other.name);
//        }
//        return false;
//    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
//        if (obj!=null && getClass()==obj.getClass()){
//            final Product other = (Product)obj;
//            return this.id==other.id && Objects.equals(this.name, other.name);
//        }
        if (obj instanceof Product) {
            final Product other = (Product) obj;
            return this.id == other.id; // && Objects.equals(this.name, other.name);
        }
        return false;
    }

}
