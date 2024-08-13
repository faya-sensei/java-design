package org.faya.sensei.creational.builder;

import org.faya.sensei.structural.bridge.IStyle;

public interface IElementBuilder {

    IElementBuilder addStyle(IStyle style);

    IElement build();
}
