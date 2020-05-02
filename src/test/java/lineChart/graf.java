package lineChart;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileOutputStream;

import static org.apache.poi.xddf.usermodel.chart.AxisCrosses.AUTO_ZERO;
import static org.apache.poi.xddf.usermodel.chart.AxisPosition.BOTTOM;
import static org.apache.poi.xddf.usermodel.chart.AxisPosition.LEFT;
import static org.apache.poi.xddf.usermodel.chart.LegendPosition.TOP_RIGHT;

public class graf {

    @Test
    public static void asd() throws Exception {
        try (XSSFWorkbook wb = new XSSFWorkbook()) {
            XSSFSheet sheet = wb.createSheet("linechart");
            final int NUM_OF_ROWS = 3;
            final int NUM_OF_COLUMNS = 10;

            // Create a row and put some cells in it. Rows are 0 based.
            Row row;
            Cell cell;
            for (int rowIndex = 0; rowIndex < NUM_OF_ROWS; rowIndex++) {
                row = sheet.createRow(rowIndex);
                for (int colIndex = 0; colIndex < NUM_OF_COLUMNS; colIndex++) {
                    cell = row.createCell(colIndex);
                    cell.setCellValue(colIndex * (rowIndex + 1.0));
                }
            }

            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 15);

            XSSFChart chart = drawing.createChart(anchor);
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(TOP_RIGHT);

            // Use a category axis for the bottom axis.
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(BOTTOM);
            bottomAxis.setTitle("x"); // https://stackoverflow.com/questions/32010765
            XDDFValueAxis leftAxis = chart.createValueAxis(LEFT);
            leftAxis.setTitle("f(x)");
            leftAxis.setCrosses(AUTO_ZERO);

            XDDFDataSource<Double> xs = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(0, 0, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, NUM_OF_COLUMNS - 1));
            XDDFNumericalDataSource<Double> ys2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, NUM_OF_COLUMNS - 1));

            XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
            XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(xs, ys1);
            series1.setTitle("2x", null); // https://stackoverflow.com/questions/21855842
            series1.setSmooth(false); // https://stackoverflow.com/questions/29014848
            series1.setMarkerStyle(MarkerStyle.STAR); // https://stackoverflow.com/questions/39636138
            XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(xs, ys2);
            series2.setTitle("3x", null);
            series2.setSmooth(true);
            series2.setMarkerSize((short) 6);
            series2.setMarkerStyle(MarkerStyle.TRIANGLE); // https://stackoverflow.com/questions/39636138
            chart.plot(data);

            // if your series have missing values like https://stackoverflow.com/questions/29014848
            // chart.displayBlanksAs(DisplayBlanks.GAP);

            // https://stackoverflow.com/questions/24676460
            solidLineSeries(data, 0, PresetColor.CHARTREUSE);
            solidLineSeries(data, 1, PresetColor.TURQUOISE);

            try (FileOutputStream fileOut = new FileOutputStream("G:/ooxml-line-chart.xlsx")) {
                wb.write(fileOut);
            }
        }
    }

    private static void solidLineSeries(XDDFChartData data, int index, PresetColor color) {
        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
        XDDFLineProperties line = new XDDFLineProperties();
        line.setFillProperties(fill);
        XDDFChartData.Series series = data.getSeries().get(index);
        XDDFShapeProperties properties = series.getShapeProperties();
        if (properties == null) {
            properties = new XDDFShapeProperties();
        }
        properties.setLineProperties(line);
        series.setShapeProperties(properties);
    }
}
