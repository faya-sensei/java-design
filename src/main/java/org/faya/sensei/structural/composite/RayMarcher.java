package org.faya.sensei.structural.composite;

public class RayMarcher {

    private static final float maxDistance = 100.0f;
    private static final int maxSteps = 100;

    private static final float epsilon = 0.01f;

    public boolean march(IShape shape, float[] start) {
        float x = start[0], y = start[1];

        for (int i = 0; i < maxSteps; i++) {
            final float distance = shape.getSDF(x, y);
            if (distance < epsilon) return true;
            if (Math.sqrt(x * x + y * y) > maxDistance) return false;
        }

        return false;
    }

    public boolean[][] render(IShape scene, int width, int height, float[] start, float stepSize) {
        boolean[][] result = new boolean[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                float x = start[0] + j * stepSize;
                float y = start[1] + i * stepSize;
                result[i][j] = march(scene, new float[] { x, y });
            }
        }

        return result;
    }
}
