package ru.meldren.lab3;

import io.github.bonigarcia.seljup.BrowserBuilder;
import io.github.bonigarcia.seljup.BrowsersTemplate;

import java.util.function.Supplier;

public enum Browser {

    CHROME(BrowserBuilder::chrome);
    //FIREFOX(BrowserBuilder::firefox);

    private final Supplier<BrowserBuilder> builderGetter;

    Browser(Supplier<BrowserBuilder> builderGetter) {
        this.builderGetter = builderGetter;
    }

    public BrowsersTemplate.Browser createBrowser() {
        return builderGetter.get().build();
    }
}
