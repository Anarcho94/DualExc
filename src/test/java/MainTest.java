import com.example.dualex.service.BusRecordService;
import com.example.dualex.service.InputFileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MainTest {

    private BusRecordService recordService;
    private InputFileService fileService;

    private static final String INPUT_PATH = "./testInputFile.txt";
    private static final String STANDARD_PATH = "./testStandardFile.txt";
    private static final String OUTPUT_PATH = "./output.txt";

    @Before
    public void initTest() {
        recordService = new BusRecordService();
        fileService = new InputFileService();
    }

    @Test(expected = IOException.class)
    public void testWrongPath() throws IOException {
        recordService.makeBusSchedule(".../wrongPath");
    }

    @Test
    public void testOutputData() throws IOException {
        recordService.makeBusSchedule(INPUT_PATH);
        List<String> standardData = fileService.fileToArrayList(STANDARD_PATH);
        List<String> outputData = fileService.fileToArrayList(OUTPUT_PATH);
        Assert.assertEquals(standardData, outputData);

    }
}
