package org.example.mower;

import lombok.Data;

/**
 * Application configuration class
 */
@Data
public class ApplicationOptions {

    /**
     * Input file
     */
    private String fileName = "./resources/inputFile.txt";

}
