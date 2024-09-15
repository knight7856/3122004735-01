import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class MainTest {

    // 工具方法：创建文件并写入内容
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("temp", ".txt");
        Files.write(tempFile.toPath(), content.getBytes());
        return tempFile;
    }

    // 工具方法：读取输出文件的相似度
    private double readSimilarityFromFile(String filePath) throws IOException {
        return Double.parseDouble(new String(Files.readAllBytes(Paths.get(filePath))));
    }

    // 测试：完全相同
    @Test
    public void testExactSame() throws Exception {
        String originalText = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String copiedText = "今天是星期天，天气晴，今天晚上我要去看电影。";

        File origFile = createTempFile(originalText);
        File copiedFile = createTempFile(copiedText);
        File outputFile = File.createTempFile("output", ".txt");

        Main.main(new String[] { origFile.getAbsolutePath(), copiedFile.getAbsolutePath(), outputFile.getAbsolutePath() });
        double similarity = readSimilarityFromFile(outputFile.getAbsolutePath());

        // 输出相似度
        System.out.println("测试完全相同的内容: " + similarity);
    }

    // 测试：高相似度
    @Test
    public void testHighSimilarity() throws Exception {
        String originalText = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String copiedText = "今天是周天，天气晴朗，我晚上要去看电影。"; // 少量修改

        File origFile = createTempFile(originalText);
        File copiedFile = createTempFile(copiedText);
        File outputFile = File.createTempFile("output", ".txt");

        Main.main(new String[] { origFile.getAbsolutePath(), copiedFile.getAbsolutePath(), outputFile.getAbsolutePath() });
        double similarity = readSimilarityFromFile(outputFile.getAbsolutePath());

        // 输出相似度
        System.out.println("测试高相似度: " + similarity);
    }

    // 测试：中等相似度
    @Test
    public void testMediumSimilarity() throws Exception {
        String originalText = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String copiedText = "今天我在家休息，天气很好，晚上可能会去散步。"; // 有明显的修改，但有部分相似

        File origFile = createTempFile(originalText);
        File copiedFile = createTempFile(copiedText);
        File outputFile = File.createTempFile("output", ".txt");

        Main.main(new String[] { origFile.getAbsolutePath(), copiedFile.getAbsolutePath(), outputFile.getAbsolutePath() });
        double similarity = readSimilarityFromFile(outputFile.getAbsolutePath());

        // 输出相似度
        System.out.println("测试中等相似度: " + similarity);
    }

    // 测试：低相似度
    @Test
    public void testLowSimilarity() throws Exception {
        String originalText = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String copiedText = "今天我读了一本书，觉得非常有趣，然后去参加了一个讲座。"; // 有极少部分相似

        File origFile = createTempFile(originalText);
        File copiedFile = createTempFile(copiedText);
        File outputFile = File.createTempFile("output", ".txt");

        Main.main(new String[] { origFile.getAbsolutePath(), copiedFile.getAbsolutePath(), outputFile.getAbsolutePath() });
        double similarity = readSimilarityFromFile(outputFile.getAbsolutePath());

        // 输出相似度
        System.out.println("测试低相似度: " + similarity);
    }

    // 测试：完全不同
    @Test
    public void testCompletelyDifferent() throws Exception {
        String originalText = "今天是星期天，天气晴，今天晚上我要去看电影。";
        String copiedText = "编程是一项有趣的活动，能够帮助解决很多问题，并提升我们的逻辑思维。"; // 完全无关

        File origFile = createTempFile(originalText);
        File copiedFile = createTempFile(copiedText);
        File outputFile = File.createTempFile("output", ".txt");

        Main.main(new String[] { origFile.getAbsolutePath(), copiedFile.getAbsolutePath(), outputFile.getAbsolutePath() });
        double similarity = readSimilarityFromFile(outputFile.getAbsolutePath());

        // 输出相似度
        System.out.println("测试完全不同: " + similarity);
    }

    //测试: 文件不存在
    @Test
    public void testFileNotFound() {
        // 测试当文件不存在时，程序是否能正确处理
        String[] args = {"non_existent_orig.txt", "non_existent_plagiarized.txt", "output.txt"};
        try {
            Main.main(args);
        } catch (NoSuchFileException e) {
            System.out.println("文件不存在");
        }
    }

}
