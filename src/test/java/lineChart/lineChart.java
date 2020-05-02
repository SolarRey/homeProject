package lineChart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.poi.xddf.usermodel.chart.AxisPosition.BOTTOM;
import static org.apache.poi.xddf.usermodel.chart.AxisPosition.LEFT;
import static org.apache.poi.xddf.usermodel.chart.ChartTypes.LINE;
import static org.apache.poi.xddf.usermodel.chart.LegendPosition.TOP_RIGHT;

public class lineChart {
    @Test
    void asda() throws IOException {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {

            String sheetName = "CountryLineChart";

            XSSFSheet sheet = wb.createSheet(sheetName);

            // Country Names
            Row row = sheet.createRow((short) 0);

            Cell cell = row.createCell((short) 0);
            cell.setCellValue("Russia");

            cell = row.createCell((short) 1);
            cell.setCellValue("Canada");

            cell = row.createCell((short) 2);
            cell.setCellValue("USA");

            cell = row.createCell((short) 3);
            cell.setCellValue("China");

            cell = row.createCell((short) 4);
            cell.setCellValue("Brazil");

            cell = row.createCell((short) 5);
            cell.setCellValue("Australia");

            cell = row.createCell((short) 6);
            cell.setCellValue("India");

            // Country Area
            row = sheet.createRow((short) 1);

            cell = row.createCell((short) 0);
            cell.setCellValue(17098242);

            cell = row.createCell((short) 1);
            cell.setCellValue(9984670);

            cell = row.createCell((short) 2);
            cell.setCellValue(9826675);

            cell = row.createCell((short) 3);
            cell.setCellValue(9596961);

            cell = row.createCell((short) 4);
            cell.setCellValue(8514877);

            cell = row.createCell((short) 5);
            cell.setCellValue(7741220);

            cell = row.createCell((short) 6);
            cell.setCellValue(3287263);

            // Country Population
            row = sheet.createRow((short) 2);

            cell = row.createCell((short) 0);
            cell.setCellValue(14590041);

            cell = row.createCell((short) 1);
            cell.setCellValue(35151728);

            cell = row.createCell((short) 2);
            cell.setCellValue(32993302);

            cell = row.createCell((short) 3);
            cell.setCellValue(14362887);

            cell = row.createCell((short) 4);
            cell.setCellValue(21172141);

            cell = row.createCell((short) 5);
            cell.setCellValue(25335727);

            cell = row.createCell((short) 6);
            cell.setCellValue(13724923);

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 4, 7, 26);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("Area-wise Top Seven Countries");
            chart.setTitleOverlay(false);

            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(TOP_RIGHT);

            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(BOTTOM);
            bottomAxis.setTitle("Country");
            XDDFValueAxis leftAxis = chart.createValueAxis(LEFT);
            leftAxis.setTitle("Area & Population");

            XDDFDataSource<String> countries = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                    new CellRangeAddress(0, 0, 0, 6));

            XDDFNumericalDataSource<Double> area = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                    new CellRangeAddress(1, 1, 0, 6));

            XDDFNumericalDataSource<Double> population = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                    new CellRangeAddress(2, 2, 0, 6));

            XDDFLineChartData data = (XDDFLineChartData) chart.createData(LINE, bottomAxis, leftAxis);

            XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(countries, area);
            series1.setTitle("Area", null);
            series1.setSmooth(false);
            series1.setMarkerStyle(MarkerStyle.STAR);

            XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(countries, population);
            series2.setTitle("Population", null);
            series2.setSmooth(true);
            series2.setMarkerSize((short) 6);
            series2.setMarkerStyle(MarkerStyle.SQUARE);

            chart.plot(data);

            // Write output to an excel file
            String filename = "G:/line-chart-top-seven-countries.xlsx";
            try (FileOutputStream fileOut = new FileOutputStream(filename)) {
                wb.write(fileOut);
            }
        }
    }

    @Test
    void wqe() throws IOException {
        FileInputStream fileInputStream=new FileInputStream("G:\\работа ТН\\chart.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet=workbook.getSheetAt(0);

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 10, 7, 26);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Среднее время отрабатывания интента");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(TOP_RIGHT);

        XDDFDataSource<String> intent = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                new CellRangeAddress(0, 0, 0, 3));

        XDDFNumericalDataSource<Double> time = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(1, 1, 0, 3));

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(BOTTOM);
        bottomAxis.setTitle("Интент");

        XDDFValueAxis leftAxis = chart.createValueAxis(LEFT);
        leftAxis.setTitle("Время");

        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFChartData.Series series1 = data.addSeries(intent,time);

//        XDDFLineChartData data = (XDDFLineChartData) chart.createData(LINE, bottomAxis, leftAxis);
//
//
//        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(intent,time);
        series1.setTitle("Сред время", null);
//        series1.setSmooth(false);
//        series1.setMarkerStyle(MarkerStyle.STAR);

        XDDFBarChartData bar = (XDDFBarChartData) data;
        bar.setBarDirection(BarDirection.COL);

        chart.plot(data);

        // Write output to an excel file
        String filename = "G:\\работа ТН\\test1.xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filename)) {
            workbook.write(fileOut);
        }
    }

    @Test
    void  aa123sda(){
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("barchart");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow((short) rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell((short) colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1.0));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XSSFChart chart = drawing.createChart(anchor);
            chart.setTitleText("x = 2x and x = 3x");
            chart.setTitleOverlay(false);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP_RIGHT);

            // Use a category axis for the bottom axis.
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("x"); // https://stackoverflow.com/questions/32010765
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("f(x)");
            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

            XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
            XDDFChartData.Series series1 = data.addSeries(xs, ys1);
            series1.setTitle("2x", null); // https://stackoverflow.com/questions/21855842
            XDDFChartData.Series series2 = data.addSeries(xs, ys2);
            series2.setTitle("3x", null);
            chart.plot(data);

            // in order to transform a bar chart into a column chart, you just need to change the bar direction
            XDDFBarChartData bar = (XDDFBarChartData) data;
            bar.setBarDirection(BarDirection.COL);
            // looking for "Stacked Bar Chart"? uncomment the following line
            // bar.setBarGrouping(BarGrouping.STACKED);

            solidFillSeries(data, 0, PresetColor.CHARTREUSE);
            solidFillSeries(data, 1, PresetColor.TURQUOISE);

            // Write the output to a file
            try (FileOutputStream fileOut = new FileOutputStream("G:\\работа ТН\\ooxml-bar-chart.xlsx")) {
                wb.write(fileOut);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void solidFillSeries(XDDFChartData data, int index, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFChartData.Series series = data.getSeries().get(index);
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setFillProperties(fill);
        series.setShapeProperties(properties);
    }

}
