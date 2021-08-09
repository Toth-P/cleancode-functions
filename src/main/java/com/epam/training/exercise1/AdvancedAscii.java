package com.epam.training.exercise1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AdvancedAscii {

    public static final int MIN = 255 * 3;
    public static final int HEIGHT_DIVIDER = 60;
    public static final int WIDTH_DIVIDER = 200;

    private AdvancedAscii() {

    }

    public static void main(String[] args) {
        Image image = Image.createImage("pair_hiking.png");
        char[] charsByDarkness = {'#', '@', 'X', 'L', 'I', ':', '.', ' '};
        Details details = new Details(0, MIN, image.getHeight() / HEIGHT_DIVIDER, image.getWidth() / WIDTH_DIVIDER);

        printImageToConsole(image, charsByDarkness, details);
    }

    private static void printImageToConsole(Image image, char[] charsByDarkness, Details details) {
        adjustSize(image, details);
        printImage(image, charsByDarkness, details);
    }

    private static void printImage(Image image, char[] charsByDarkness, Details details) {

        for (int y = 0; y < image.getHeight() - details.getStepY(); y += details.getStepY()) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < image.getWidth() - details.getStepX(); x += details.getStepX()) {
                int sum = getSum(image, details, y, x);
                sum = sum / details.getStepY() / details.getStepX();

                line.append(getCharsByDarkness(charsByDarkness, details, sum));
            }
            Logger logger = LoggerFactory.getLogger(AdvancedAscii.class);
            logger.info(line.toString());
        }
    }

    private static char getCharsByDarkness(char[] charsByDarkness, Details details, int sum) {
        return charsByDarkness[(sum - details.getMin()) * charsByDarkness.length / (details.getMax() - details.getMin() + 1)];
    }

    private static void adjustSize(Image image, Details details) {
        for (int y = 0; y < image.getHeight(); y += details.getStepY()) {
            for (int x = 0; x < image.getWidth(); x += details.getStepX()) {
                int sum = getSum(image, details, y, x);
                sum = sum / details.getStepY() / details.getStepX();
                details.setMax(changeMaxIfNeeded(details, sum));
                details.setMin(changeMinIfNeeded(details, sum));
            }
        }
    }

    private static Integer changeMaxIfNeeded(Details details, int sum) {
        int result = details.getMax();
        if (result < sum) {
            result = sum;
        }
        return result;
    }

    private static Integer changeMinIfNeeded(Details details, int sum) {
        int result = details.getMin();
        if (result > sum) {
            result = sum;
        }
        return result;
    }

    private static int getSum(Image image, Details details, int y, int x) {
        int sum = 0;
        for (int avgY = 0; avgY < details.getStepY(); avgY++) {
            for (int avgX = 0; avgX < details.getStepX(); avgX++) {
                sum = getSumPlusRBG(image, y, x, sum);
            }
        }
        return sum;
    }

    private static int getSumPlusRBG(Image image, int y, int x, int sum) {
        return sum + (image.getRed(new Coordinate(x, y)) + image.getBlue(new Coordinate(x, y)) + image.getGreen(new Coordinate(x, y)));
    }
}
