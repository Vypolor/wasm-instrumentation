package com.ssau.instrumentation.handler;

import com.ssau.instrumentation.model.Global;
import com.ssau.instrumentation.model.TypeDefinition;
import com.ssau.instrumentation.model.enums.Type;
import com.ssau.instrumentation.model.section.impl.Table;
import com.ssau.instrumentation.model.segments.*;

import java.util.*;

public class Module {

    private List<MemorySegment> memories;
    private List<Global> globals;
    private List<DataSegment> datas;
    private List<ElementSegment> elements;
    private List<TypeDefinition> types;
    private List<ExportSegment> exportSegments;
    private List<Integer> functionTypes;
    private List<FunctionSegment> functionSegments;
    private List<Table> tables;

    public Module() {
        //EMPTY
    }

    public Module(List<MemorySegment> memories, List<Global> globals, List<DataSegment> datas
            , List<ElementSegment> elements, List<TypeDefinition> types
            , List<ExportSegment> exportSegments, List<Integer> functionTypes
            , List<FunctionSegment> functionSegments, List<Table> tableSegments) {
        this.memories = memories;
        this.globals = globals;
        this.datas = datas;
        this.elements = elements;
        this.types = types;
        this.exportSegments = exportSegments;
        this.functionTypes = functionTypes;
        this.functionSegments = functionSegments;
        this.tables = tableSegments;
    }

    public Instance instantiate() {
        Evaluator evaluator = new Evaluator();

        List<Memory> memories = new ArrayList<>(this.memories.size());
        for (MemorySegment memory : this.memories) {
            Memory mem = new Memory(memory.limits());
            memories.add(mem);
        }

        List<WasmValue> globals = new ArrayList<>(this.globals.size());
        for (Global global : this.globals) {
            WasmValue result = evaluator.evaluate(global.expression(), new Type[0], null, global.type());
            globals.add(result);
        }

        for (var data : this.datas) {
            byte[] bytes = data.getData();
            byte[] expression = data.getExpression();
            int memoryIdx = data.getMemoryIdx();
            var index = evaluator.evaluate(expression, new Type[0], null, Type.I32);
            Memory memory = memories.get(memoryIdx);
            memory.copy(bytes, (Integer) index.value());
        }

        List<Function> functions = new ArrayList<>(functionTypes.size());
        for (int fnIdx = 0; fnIdx < functionTypes.size(); fnIdx++) {
            Integer typeidx = functionTypes.get(fnIdx);
            TypeDefinition type = types.get(typeidx);
            FunctionSegment code = functionSegments.get(fnIdx);
            Function function = new Function(type.asFunction(), code.getLocals(), code.opcodes());
            functions.add(function);
        }

        Map<String, ExportSegment> exports = new HashMap<>(this.exportSegments.size());
        for (var export : this.exportSegments) {
            String name = export.name();
            exports.put(name, export);
        }


        return new Instance(evaluator, memories, globals, functions, exports);
    }

    public List<MemorySegment> getMemories() {
        return memories;
    }

    public void setMemories(List<MemorySegment> memories) {
        this.memories = memories;
    }

    public List<Global> getGlobals() {
        return globals;
    }

    public void setGlobals(List<Global> globals) {
        this.globals = globals;
    }

    public List<DataSegment> getDatas() {
        return datas;
    }

    public void setDatas(List<DataSegment> datas) {
        this.datas = datas;
    }

    public List<ElementSegment> getElements() {
        return elements;
    }

    public void setElements(List<ElementSegment> elements) {
        this.elements = elements;
    }

    public List<TypeDefinition> getTypes() {
        return types;
    }

    public void setTypes(List<TypeDefinition> types) {
        this.types = types;
    }

    public List<ExportSegment> getExportSegments() {
        return exportSegments;
    }

    public void setExportSegments(List<ExportSegment> exportSegments) {
        this.exportSegments = exportSegments;
    }

    public List<Integer> getFunctionTypes() {
        return functionTypes;
    }

    public void setFunctionTypes(List<Integer> functionTypes) {
        this.functionTypes = functionTypes;
    }

    public List<FunctionSegment> getFunctionSegments() {
        return functionSegments;
    }

    public void setFunctionSegments(List<FunctionSegment> functionSegments) {
        this.functionSegments = functionSegments;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}
