import java.io.*;
import java.util.*;

public class CodeSplitter {
    // 分割代码的方法
    public static void splitCode(String inputFilePath, int maxLines) {
        try {
            // 读取输入文件
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            List<String> lines = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // 计算需要多少个文件
            int totalLines = lines.size();
            int numFiles = (totalLines / maxLines) + (totalLines % maxLines == 0 ? 0 : 1);

            // 分割并写入文件
            int start = 0;
            for (int i = 0; i < numFiles; i++) {
                // 计算每个文件的结束行
                int end = Math.min((i + 1) * maxLines, totalLines);
                
                // 确保不分割类或方法
                end = adjustSplitPoint(lines, start, end);

                // 获取当前文件的代码块
                List<String> chunk = lines.subList(start, end);
                
                // 构造输出文件名
                String outputFilePath = "split_" + (i + 1) + ".java";
                writeToFile(outputFilePath, chunk);
                System.out.println("文件 " + outputFilePath + " 已保存，包含 " + chunk.size() + " 行代码.");

                // 更新下一个文件的起始位置
                start = end;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 调整分割点，避免分割在类或方法的内部
    private static int adjustSplitPoint(List<String> lines, int start, int end) {
        // 确保分割点在方法或类的完整边界上
        boolean inMethod = false;
        boolean inClass = false;

        for (int i = end - 1; i >= start; i--) {
            String line = lines.get(i).trim();
            // 检查是否遇到类或方法的结束
            if (line.endsWith("}")) {
                if (inMethod) {
                    inMethod = false;
                } else if (inClass) {
                    inClass = false;
                } else {
                    inClass = true; // 找到类的结束
                }
            }
            // 如果我们已经结束了方法或类的声明，停止调整
            if (!inMethod && !inClass && (line.startsWith("class") || line.contains("}"))) {
                return i + 1; // 找到合适的分割点
            }
        }
        return end; // 如果没有发现合适的结束点，返回原始结束点
    }

    // 将分割后的代码写入文件
    private static void writeToFile(String filePath, List<String> chunk) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : chunk) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 使用示例：分割代码文件 large_code.java，每个文件最多包含1000行代码
        splitCode("large_code.java", 1000);
    }
}
