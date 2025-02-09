package com.example.rating;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Analyzer {
    public static void main(String[] args) throws Exception {
        String code = new String(Files.readAllBytes(Paths.get("/Users/in45828930/Downloads/rating/src/main/java/com/example/rating/RatingService.java")));
        CompilationUnit cu = new JavaParser().parse(code).getResult().orElseThrow();

        cu.findAll(MethodDeclaration.class).forEach(method -> {
            System.out.println("Method Name: " + method.getName());
        });
    }
}

