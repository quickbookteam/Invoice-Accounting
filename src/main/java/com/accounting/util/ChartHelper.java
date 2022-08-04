package com.accounting.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.accounting.modal.Data;

public class ChartHelper {

	public void generatePieChart(List<Data> list, String location) {

		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Data ob : list) {
			dataset.setValue(String.valueOf(UtilConstants.months.get(ob.getCreateTime().getMonth())),
					Double.valueOf(ob.getCount()));
		}

		JFreeChart chart = ChartFactory.createPieChart3D("Customer pieChart", dataset);

		try {
			ChartUtils.saveChartAsJPEG(new File(location + "/Customerpie.jpg"), chart, 400, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
