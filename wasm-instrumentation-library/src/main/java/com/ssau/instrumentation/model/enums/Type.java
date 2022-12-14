package com.ssau.instrumentation.model.enums;

public enum Type {
    I32(Type.TYPE_IDX_I32),
    I64(Type.TYPE_IDX_I64),
    F32(Type.TYPE_IDX_F32),
    F64(Type.TYPE_IDX_F64),
    Table(Type.TYPE_IDX_TABLE),
    V128(Type.TYPE_IDX_V128),
    FuncRef(Type.TYPE_IDX_FUNC_REF),
    ExternRef(Type.TYPE_IDX_EXTERN_REF),
    Func(Type.TYPE_IDX_FUNC),
    EmptyBlockType(Type.TYPE_IDX_EMPTY_BLOCK),
    Void(Type.TYPE_IDX_VOID);

    public static final byte TYPE_IDX_I32 = 0x7F;
    public static final byte TYPE_IDX_I64 = 0x7E;
    public static final byte TYPE_IDX_F32 = 0x7D;
    public static final byte TYPE_IDX_F64 = 0x7C;
    public static final byte TYPE_IDX_TABLE = 0x70;
    public static final byte TYPE_IDX_V128 = 0x05;
    public static final byte TYPE_IDX_FUNC_REF = 0x60;
    public static final byte TYPE_IDX_EXTERN_REF = 0x11;
    public static final byte TYPE_IDX_FUNC = 0x20;
    public static final byte TYPE_IDX_EMPTY_BLOCK = 0x40;

    public static final byte TYPE_IDX_VOID = (byte) 0x000000FF;

    private final byte idx;

    Type(byte idx) {
        this.idx = idx;
    }

    public static Type valueOf(byte typeIdx) {
        for (Type type : Type.values())
            if (typeIdx == type.idx)
                return type;

        throw new IllegalArgumentException(String.format("Could not find Type with IDX: %08X", typeIdx));
    }

    public byte getIdx() {
        return idx;
    }
}
