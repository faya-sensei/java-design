package org.faya.sensei.creational.builder;

public interface IShader {

    void useProgram();

    void deleteProgram();

    void setUniform(String name, float value);

    void setUniform(String name, float[] value);

    void loadShader(String[] filePath);
}
