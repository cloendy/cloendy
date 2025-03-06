<dependencies>
    <!-- Apache POI 依赖 -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.3</version> <!-- 根据需要选择版本 -->
    </dependency>

    <!-- Jsoup 依赖 -->
    <dependency>
        <groupId>org.jsoup</groupId>
        <artifactId>jsoup</artifactId>
        <version>1.15.3</version> <!-- 根据需要选择版本 -->
    </dependency>

</dependencies>



package com.example.demoMybatis.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HtmlToExcel {
    // Excel 文件路径
    private static final String EXCEL_FILE = "src/main/resources/doc/a.xlsx";
    // HTML 文件路径
    private static final String HTML_FILE = "src/main/resources/doc/example1.html";

    public static void main(String[] args) {
        // 从文件读取 HTML 内容
        String htmlContent = readHtmlFromFile(HTML_FILE);

        if (htmlContent != null) {
            // 调用方法将 HTML 提取的数据保存到 Excel 文件
            extractAndSaveToExcel(htmlContent);
        }
    }

    // 从文件中读取 HTML 内容
    public static String readHtmlFromFile(String filePath) {
        try {
            if(filePath==null){
                return new String(Files.readAllBytes(Paths.get(HTML_FILE)));
            }
            else {
                // 读取文件内容并返回字符串
                return new String(Files.readAllBytes(Paths.get(filePath)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void extractAndSaveToExcel(String htmlContent) {
        // 使用 Jsoup 解析 HTML 内容
        Document document = Jsoup.parse(htmlContent);

        // 提取 <a> 标签
        List<Element> links = document.select("a.module-poster-item");

        // 如果 Excel 文件存在，则读取文件
        File file = new File(EXCEL_FILE);
        Workbook workbook;
        Sheet sheet;

        try {
            if (file.exists()) {
                // 文件存在，读取并追加数据
                FileInputStream fileInputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(fileInputStream);
                sheet = workbook.getSheetAt(0);
                fileInputStream.close();
            } else {
                // 文件不存在，创建新的 Excel 文件
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Extracted Data");
                // 创建表头
                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Title");
                headerRow.createCell(1).setCellValue("Image URL");
            }

            // 获取现有行数
            int rowNum = sheet.getPhysicalNumberOfRows();

            // 遍历每个 <a> 标签并提取数据
            for (Element link : links) {
                // 提取 title 属性
                String title = link.attr("title");

                // 提取 <img> 标签的 src 属性
                Element imgTag = link.select("img").first();
                String imgSrc = imgTag != null ? imgTag.attr("data-original") : "";

                // 创建新行并写入数据
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(title);
                row.createCell(1).setCellValue(imgSrc);
            }

            // 保存到 Excel 文件
            try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE)) {
                workbook.write(fileOut);
            }

            // 关闭工作簿
            workbook.close();
            System.out.println("数据成功保存到 Excel 文件！");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
