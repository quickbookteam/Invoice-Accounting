package com.accounting.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



public class ChartHelper {
	
	public void generatePieChart(List<Data> list, String location) {
        // a. Create DataSet and read List<Object[]> into DataSet values

        DefaultPieDataset dataset = new DefaultPieDataset();
    
        // get value from list
        for (Data ob : list) {
            dataset.setValue(String.valueOf(UtilContants.months.get(ob.getCreateTime().getMonth())), Double.valueOf(ob.getCount()));
        }

        // b. Convert DataSet data into JFreeChart object using ChartFactory class
        JFreeChart chart = ChartFactory.createPieChart3D("Customer pieChart", dataset);
        
        //// c. Convert JFreeChart object into one Image using ChartUtil class
        try {
            ChartUtils.saveChartAsJPEG(new File(location + "/Customerpie.jpg"), chart, 400, 300);        
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
