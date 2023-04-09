package org.example.practice2.list;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс представляющий собой единицу мебели
 */
public final class Furniture {
    private final String partNumber;
    private final String title;
    private final float price;
    private final Date productionDate;

    private final int copiesCount;
    private final Color color;


    public Furniture(String partNumber, String title, float price, Date productionDate, int copiesCount, Color color) {
        this.partNumber = partNumber;
        this.title = title;
        this.price = price;
        this.productionDate = productionDate;
        this.copiesCount = copiesCount;
        this.color = color;
    }

    public Furniture(String partNumber, String title, float price, Date productionDate, int copiesCount) {
        this.partNumber = partNumber;
        this.title = title;
        this.price = price;
        this.productionDate = productionDate;
        this.copiesCount = copiesCount;
        this.color = Color.BLACK;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    @NotNull
    public Date getProductionDate() {
        return productionDate;
    }

    @NotNull
    public String getPartNumber() {
        return partNumber;
    }

    public int getCopiesCount() {
        return copiesCount;
    }

    @NotNull
    public Color getColor() {
        return color;
    }

    @Override
    @NotNull
    public String toString() {
        final DecimalFormat format = new DecimalFormat( "#.##");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        final String formattedColor = "{" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + "}";
        return "(" +
                "" + partNumber +
                ", " + title +
                ", " + format.format(price) +
                ", " + dateFormat.format(productionDate) +
                ", " + copiesCount +
                ", " + formattedColor + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Furniture furniture = (Furniture) o;
        return Float.compare(furniture.price, price) == 0
                && copiesCount == furniture.copiesCount
                && partNumber.equals(furniture.partNumber)
                && title.equals(furniture.title)
                && productionDate.equals(furniture.productionDate)
                && color.equals(furniture.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partNumber, title, price, productionDate, copiesCount, color);
    }
}
