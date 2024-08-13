package org.faya.sensei.creational.builder;

import java.util.List;

public interface IElement {

    List<IElementStyle> getStyles();

    float[] getRect();

    void setX(int x);

    void setY(int y);

    void setWidth(int width);

    void setHeight(int height);

    void setMargin(int margin);

    void setPadding(int padding);

    void setBorderWidth(int borderWidth);
}
