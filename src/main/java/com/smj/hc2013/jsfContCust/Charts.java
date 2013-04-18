/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smj.hc2013.jsfContCust;

/**
 *
 * @author deb
 */
import java.io.Serializable;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

public class Charts implements Serializable {

    private CartesianChartModel linearModelYear;

    public Charts() {
        createLinearModelYear();

    }

    public CartesianChartModel getLinearModel() {
        return linearModelYear;
    }

    private void createLinearModelYear() {
        linearModelYear = new CartesianChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(3, 1);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
        series2.setMarkerStyle("diamond");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        linearModelYear.addSeries(series1);
        linearModelYear.addSeries(series2);
    }
}
