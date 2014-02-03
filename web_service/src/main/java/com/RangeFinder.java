package com;

import java.util.Collections;
import java.util.List;

/**
 * Author: Monika
 * Date  : 2/2/14
 * Time  : 11:16 PM
 */
public class RangeFinder {

    public Range findRange(List<Integer> numbers) {
        Integer min = Collections.min(numbers);
        Integer max = Collections.max(numbers);
        return new Range(min, max);
    }

    public static class Range {
        int min;
        int max;

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

        public Range(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Range range = (Range) o;

            if (max != range.max) return false;
            if (min != range.min) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = min;
            result = 31 * result + max;
            return result;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }

}
