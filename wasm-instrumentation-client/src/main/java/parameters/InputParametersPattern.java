package parameters;

public enum InputParametersPattern {

    WASM_FILE {
        @Override
        public String getPattern() {
            return "-input_wasm_file|-iwf";
        }
        @Override
        public boolean isRequired() {
            return true;
        }
    }, OUTPUT_DIRECTORY {
        @Override
        public String getPattern() {
            return "-output_directory|-od";
        }
        @Override
        public boolean isRequired() {
            return false;
        }
    };

    public abstract String getPattern();
    public abstract boolean isRequired();

    public static InputParametersPattern getPatternByValue(String value) {
        for (InputParametersPattern parametersPattern : values()) {
            if (value.matches(parametersPattern.getPattern())) {
                return parametersPattern;
            }
        }
        throw new IllegalArgumentException("Parameter " + value + " is not supported");
    }

    public static boolean checkValidatePattern(String value) {
        for (InputParametersPattern parametersPattern : values()) {
            if (value.matches(parametersPattern.getPattern())) {
                return true;
            }
        }
        return false;
    }
}
