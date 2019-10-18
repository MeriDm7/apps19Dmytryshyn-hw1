package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    double[] list;
    int len = 10;
    int mintemp = -273;

    public TemperatureSeriesAnalysis() {
        this.list = new double[10];
        this.len = 10;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        this.len = temperatureSeries.length;
        this.list = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        minTemp(this.list);
    }

    public  void minTemp(double[] lst) {
        for (int i = 0; i < lst.length; i++) {
            if (lst[i] < mintemp) {
                throw new InputMismatchException();
            }
        }
    }

    public double average() {
        if (this.len > 0) {
            double aver;
            double sum = 0;
            for (int i = 0; i < this.len; i++) {
                sum += this.list[i];
            }
            aver = sum / this.len;
            return aver;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public double deviation() {
        if (this.len > 0) {
            double av = this.average();
            double divd = 0;
            for (int i = 0; i < this.len; i++) {
                divd += (this.list[i] - av)*(this.list[i] - av);
            }
            double dev;
            dev = Math.sqrt(divd / this.len);
            return dev;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public double min() {
        if (this.len > 0) {
            double min = this.list[0];
            for (int i = 0; i < this.len; i++) {
                if (this.list[i] < min) {
                    min = this.list[i];
                }
            }
            return min;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public double max() {
        if (this.len > 0) {
            double max = this.list[0];
            for (int i = 0; i < this.len; i++) {
                if (this.list[i] > max) {
                    max = this.list[i];
                }
            }
            return max;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public double findTempClosestToZero() {
        if (this.len > 0) {
            double clos = this.list[0];
            for (double i : this.list) {
                if (i == 0) {
                    return 0;
                }
                if (Math.abs(clos) > Math.abs(i)) {
                    clos = i;
                }
                if (Math.abs(clos) == Math.abs(i)) {
                    if (i > clos) {
                    clos = Math.abs(i);
                }
                }
            }
            return clos;
            }
        else {
            throw new IllegalArgumentException();
                }
    }

    public double findTempClosestToValue(double tempValue) {
        if (this.len > 0) {
            double close = this.list[0];
            double disf = Math.abs(close - tempValue);
            for (double i : this.list) {
                double diss = Math.abs(i - tempValue);
                if (disf > diss) {
                    disf = diss;
                    close = i;
                }
                if (disf == diss) {
                    if (i > close) {
                        close = Math.abs(i);
                    }
                }
                }
            return close;
            }
        else {
            throw new IllegalArgumentException();

        }
    }

    public double[] findTempsLessThen(double tempValue) {
        if (this.len == 0) {
            throw new IllegalArgumentException();
        }
        int counter = 0;
        for (double i: this.list) {
            if (i < tempValue) {
                counter += 1;
            }
        }
        double[] res = new double[counter];
        int conew = 0;
        for (double i : this.list) {
            if (i < tempValue) {
                res[conew] = i;
                conew += 1;
            }
        }
        return res;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (this.len == 0) {
            throw new IllegalArgumentException();
        }
        int countf = 0;
        for (double i: this.list) {
            if (i >= tempValue) {
                countf += 1;
            }
        }
        int counter = 0;
        double[] res = new double[countf];
        for (double i: this.list) {
            if (i >= tempValue) {
                res[counter] = i;
                counter += 1;
            }
        }
        return res;
    }

    public TempSummaryStatistics summaryStatistics() {
        if (this.len == 0) {
            throw new IllegalArgumentException();
        }
        else {
            TempSummaryStatistics sts = new TempSummaryStatistics(average(),
                    deviation(), min(), max());
            return sts;
        }
    }

    public int addTemps(double... temps) {
        minTemp(temps);
        double[] nlist = Arrays.copyOf(this.list, this.len);
        int ln = this.len;
        list = new double[ln * 2];
        len = list.length;
        for (int i = 0; i < ln; i++) {
            list[i] = nlist[i];
        }
        for (int i = 0; i < temps.length; i++) {
            list[ln + i] = temps[i];
        }
        int res = ln + temps.length;
        return  res;
    }
}
