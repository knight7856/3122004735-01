
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Main {
    public static void main(String[] args) throws NoSuchFileException {
        if (args.length != 3) {
            System.out.println("Usage: java -jar main.jar <original_file_path> <plagiarized_file_path> <output_file_path>");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String outputFilePath = args[2];

        // 创建各个类的实例
        FileHandler fileHandler = new FileHandler();
        TextPreprocessor textPreprocessor = new TextPreprocessor();
        SimilarityCalculator similarityCalculator = new SimilarityCalculator();

        try {
            // 读取原文和抄袭文件
            String originalText = fileHandler.readFile(originalFilePath);
            String plagiarizedText = fileHandler.readFile(plagiarizedFilePath);

            // 文本预处理
            originalText = textPreprocessor.preprocess(originalText);
            plagiarizedText = textPreprocessor.preprocess(plagiarizedText);

            // 计算相似度
            double similarityRate = similarityCalculator.calculateSimilarity(originalText, plagiarizedText);

            // 输出结果
            fileHandler.writeResult(outputFilePath, similarityRate);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}