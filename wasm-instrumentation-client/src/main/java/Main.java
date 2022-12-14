import com.ssau.instrumentation.handler.Instance;
import com.ssau.instrumentation.handler.Module;
import com.ssau.instrumentation.handler.ModuleBuildingHandler;
import com.ssau.instrumentation.handler.WasmValue;
import com.ssau.instrumentation.parser.Parser;
import parameters.InputParametersPattern;
import parameters.ParametersParser;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public class Main {

    //For test
    private static final String TEST_FILE_PATH = "D:\\wasm-tutor\\hello-world\\a.out.wasm";

    public static void main(String[] args) throws IOException {
        Map<InputParametersPattern, String> parameters = ParametersParser.parseInputParameters(args);
        Path wasmFile = Paths.get(TEST_FILE_PATH);
        FileChannel fileChannel = FileChannel.open(wasmFile, StandardOpenOption.READ);
        MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
        ModuleBuildingHandler handler = new ModuleBuildingHandler();
        new Parser().parse(buffer, handler);
        Module wasmModule = handler.build();
    }


}
