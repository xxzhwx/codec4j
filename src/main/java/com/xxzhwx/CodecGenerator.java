package com.xxzhwx;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CodecGenerator {
    private static final String CODEC_REGISTRY_NAME = "CodecRegistry";
    private static final String CODEC_REPO = "com.xxzhwx.CodecRepository.I";

    private String rootPath;
    private String rootPackageName;
    private Map<String, PacketInfo> packetInfoMap = new HashMap<>();

    public void findOutPackets(String rootPath, String rootPackageName) throws ClassNotFoundException {
        this.rootPath = rootPath;
        this.rootPackageName = rootPackageName;

        List<String> classNames = Utils.listClassNames(rootPackageName, rootPath);
        System.out.println(classNames);

        if (classNames.isEmpty()) {
            return;
        }

        for (String name : classNames) {
            Class<?> clazz = Class.forName(name);
            PacketClass pc = clazz.getAnnotation(PacketClass.class);
            if (pc == null) {
                continue;
            }

            Field[] fields = clazz.getDeclaredFields();
            Map<Integer, Field> fieldMap = new HashMap<>();
            for (Field f : fields) {
                PacketField pf = f.getAnnotation(PacketField.class);
                if (pf == null) {
                    continue;
                }
                fieldMap.put(pf.order(), f);
            }

            if (fieldMap.isEmpty()) {
                System.err.println("[warn] packet class has no field: " + name);
                continue;
            }

            if (packetInfoMap.containsKey(clazz.getSimpleName())) {
                System.err.println("[warn] packet class name duplicated: " + name);
                continue;
            }

            packetInfoMap.put(clazz.getSimpleName(), new PacketInfo(clazz, fieldMap));
        }
    }

    public void genCodec() throws IOException {
        String toPath = new StringJoiner(File.separator).add(rootPath).add("gen").add("codec").toString();
        String packageName = new StringJoiner(".").add(rootPackageName).add("gen").add("codec").toString();

        if (!Utils.mkdirsIfNotExists(toPath)) {
            return;
        }

        System.out.println("gen Codec to: " + toPath);

        for (PacketInfo packetInfo : packetInfoMap.values()) {
            packetInfo.genCodec(toPath, packageName);
        }
    }

    public void genCodecRegistry() throws IOException {
        String toPath = new StringJoiner(File.separator).add(rootPath).add("gen").toString();
        String packageName = new StringJoiner(".").add(rootPackageName).add("gen").toString();

        if (!Utils.mkdirsIfNotExists(toPath)) {
            return;
        }

        System.out.println("gen CodecRegistry to: " + toPath);

        List<String> lines = new ArrayList<>();
        lines.add("package " + packageName + ";");
        Utils.addAutoGeneratedNote(lines);
        lines.add("public final class " + CODEC_REGISTRY_NAME +" {");
        lines.add("public static void register(){");
        lines.addAll(packetInfoMap.values().stream()
                .map(pi -> CODEC_REPO + ".setCodec(" + pi.clazz.getName() + ".class, new " + pi.getCodecFullName() + "());")
                .collect(Collectors.toList()));
        lines.add("}");
        lines.add("}");

        Utils.saveToFile(lines,
                new StringJoiner(File.separator)
                        .add(toPath)
                        .add(CODEC_REGISTRY_NAME + ".java")
                        .toString());
    }

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        String rootPath = "F:\\work\\codec4j\\src\\main\\java\\com\\xxzhwx";
        String rootPackageName = "com.xxzhwx";

        CodecGenerator generator = new CodecGenerator();
        generator.findOutPackets(rootPath, rootPackageName);
        generator.genCodec();
        generator.genCodecRegistry();
    }
}
