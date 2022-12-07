import parameters.InputParametersPattern;
import parameters.ParametersParser;

import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<InputParametersPattern, String> parameters = ParametersParser.parseInputParameters(args);
        //Module module = new Module();
        //module.parseModuleFromFile("D:\\wasabi\\crates\\hello_world.wasm");
        System.out.println();
    }
}
