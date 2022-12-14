package parameters;

import java.util.EnumMap;
import java.util.Map;

public class ParametersParser {

    public static Map<InputParametersPattern, String> parseInputParameters(String []args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("Parameters is always a pair of name and value");
        }

        Map<InputParametersPattern, String> parameters = new EnumMap(InputParametersPattern.class);
        for (int i = 0; i < args.length; i += 2) {
            if ((InputParametersPattern.checkValidatePattern(args[i]))
                    && !(InputParametersPattern.checkValidatePattern(args[i+1]))) {
                parameters.put(InputParametersPattern.getPatternByValue(args[i]), args[i+1]);
            }
            else {
                throw new IllegalArgumentException("Invalid parameters + value format");
            }
        }
        return parameters;
    }
}
