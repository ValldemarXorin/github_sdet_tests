package com.github_tst_sdet.tests.api;

import com.github_tst_sdet.configuration.Provider;
import com.github_tst_sdet.configuration.convention.PathResolver;
import com.github_tst_sdet.configuration.loader.Loader;
import com.github_tst_sdet.configuration.object_creator.ObjectCreator;
import com.github_tst_sdet.model.Browser;
import com.github_tst_sdet.model.ApplicationConfig;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

    @org.junit.jupiter.api.Test
    @DisplayName("Should load Browser configuration")
    void shouldLoadBrowserConfiguration() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        System.out.println("Browser: " + browser);

        assertNotNull(browser);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should map primitive fields")
    void shouldMapPrimitiveFields() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertEquals("chrome", browser.getBrowser());
        assertFalse(browser.isHeadless());

        assertTrue(browser.isVideo());
        assertTrue(browser.isScreenshots());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should create nested Window object")
    void shouldCreateWindow() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertNotNull(browser.getWindow());

        assertEquals(1920, browser.getWindow().getWidth());
        assertEquals(1080, browser.getWindow().getHeight());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should create nested Timeouts object")
    void shouldCreateTimeouts() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertNotNull(browser.getTimeouts());

        assertEquals(10000, browser.getTimeouts().getTimeout());
        assertEquals(30000, browser.getTimeouts().getPageLoad());
        assertEquals(200, browser.getTimeouts().getPolling());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should create nested Remote object")
    void shouldCreateRemote() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertNotNull(browser.getRemote());

        assertFalse(browser.getRemote().isEnabled());
        assertEquals(
                "http://localhost:4444/wd/hub",
                browser.getRemote().getUrl()
        );
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should populate all nested objects")
    void shouldPopulateEntireObjectGraph() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertAll(

                () -> assertNotNull(browser),

                () -> assertNotNull(browser.getWindow()),
                () -> assertNotNull(browser.getTimeouts()),
                () -> assertNotNull(browser.getRemote())

        );
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should return new object")
    void shouldReturnObject() {

        Object object = Provider.provide(Browser.class);

        assertInstanceOf(Browser.class, object);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should create valid object graph")
    void shouldCreateCompleteObjectGraph() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertAll(

                () -> assertEquals("chrome", browser.getBrowser()),

                () -> assertEquals(1920,
                        browser.getWindow().getWidth()),

                () -> assertEquals(1080,
                        browser.getWindow().getHeight()),

                () -> assertEquals(10000,
                        browser.getTimeouts().getTimeout()),

                () -> assertEquals(30000,
                        browser.getTimeouts().getPageLoad()),

                () -> assertEquals(200,
                        browser.getTimeouts().getPolling()),

                () -> assertFalse(browser.getRemote().isEnabled()),

                () -> assertEquals(
                        "http://localhost:4444/wd/hub",
                        browser.getRemote().getUrl()),

                () -> assertTrue(browser.isVideo()),

                () -> assertTrue(browser.isScreenshots())

        );
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should use cache")
    void shouldUseCache() {

        Browser first = (Browser) Provider.provide(Browser.class);
        Browser second = (Browser) Provider.provide(Browser.class);

        assertSame(first, second);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Should not contain null nested objects")
    void shouldNotContainNullObjects() {

        Browser browser = (Browser) Provider.provide(Browser.class);

        assertAll(

                () -> assertNotNull(browser.getWindow()),
                () -> assertNotNull(browser.getTimeouts()),
                () -> assertNotNull(browser.getRemote())

        );
    }
}
