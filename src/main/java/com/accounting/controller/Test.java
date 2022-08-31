package com.accounting.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Test {
	
	
	@GetMapping("/test")
    public File test() throws IOException {

    	CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        BufferedImage bufferedImage = chart.createBufferedImage(500, 500);
        File file = new File("myimage.png");
		ImageIO.write(bufferedImage, "png", file);

		// Save as JPEG
		file = new File("myimage.jpg");
		ImageIO.write(bufferedImage, "jpg", file);
		return file;
        
    }

  

    private CategoryDataset createDataset() {

    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(46, "Invoice Graph", "USA");
        dataset.setValue(38, "Gold medals", "China");
        dataset.setValue(29, "Gold medals", "UK");
    

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Invoice Graph",
                "",
                "Gold medals",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        return barChart;
    }
}
