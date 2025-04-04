package com.example;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.*;
import java.util.List;

public class ReportCommand {
    public void execute(Repository repo, String outputPath) {
        try {

            InputStream test = getClass().getResourceAsStream("/vtemplates/report.ftl");
            if (test == null) throw new FileNotFoundException("Template not found");
            test.close();

            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setClassForTemplateLoading(getClass(), "/vtemplates");
            cfg.setDefaultEncoding("UTF-8");

            Map<String, Object> data = new HashMap<>();
            data.put("repoName", repo.getRepoName());

            List<Map<String, Object>> imagesData = new ArrayList<>();
            for (Image image : repo.getImages()) {
                File file = new File(image.path());
                imagesData.add(Map.of(
                        "name", image.name(),
                        "tags", image.tags(),
                        "size", formatSize(file),
                        "lastModified", file.exists() ?
                                new Date(file.lastModified()).toString() : "N/A"
                ));
            }
            data.put("files", imagesData);

            try (Writer writer = new FileWriter(outputPath)) {
                cfg.getTemplate("report.ftl").process(data, writer);
                System.out.println("Report generated: " + outputPath);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String formatSize(File file) {
        if (!file.exists()) return "N/A";
        long bytes = file.length();
        if (bytes < 1024) return bytes + " B";
        return String.format("%.1f KB", bytes / 1024.0);
    }
}