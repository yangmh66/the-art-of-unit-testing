package com.osherove.ch2;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class LogAnalyzerTest {

    public static class NonParameterizedTest {
        @Test
        public void IsValidLogFileName_BadExtension_ReturnsFalse() {
            // 單元測試包含了三個行為 (3A)
            // 準備 (Arrange) 物件
            LogAnalyzer analyzer = new LogAnalyzer();

            // 操作 (Act) 物件
            boolean result = analyzer.isValidLogFileName("filewithbadextension.foo");

            // 驗證 (Assert) 某件事符合預期
            Assert.assertFalse(result);
        }

        @Test
        public void IsValidLogFileName_GoodExtensionUppercase_ReturnsTrue() {
            LogAnalyzer analyzer = new LogAnalyzer();

            boolean result = analyzer.isValidLogFileName("filewithgoodextension.SLF");

            Assert.assertTrue(result);
        }

        @Test
        public void IsValidLogFileName_GoodExtensionLowercase_ReturnsTrue() {
            LogAnalyzer analyzer = new LogAnalyzer();

            boolean result = analyzer.isValidLogFileName("filewithgoodextension.slf");

            Assert.assertTrue(result);
        }
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedTest {
        private String file;

        public ParameterizedTest(String file) {
            this.file = file;
        }

        @Parameters
        public static Collection<String> data() {
            return Arrays.asList(
                "filewithgoodextension.SLF",
                "filewithgoodextension.slf"
            );
        }

        @Test
        public void IsValidLogFileName_ValidExtensions_ReturnsTrue() {
            LogAnalyzer analyzer = new LogAnalyzer();

            boolean result = analyzer.isValidLogFileName(this.file);

            Assert.assertTrue(result);
        }
    }

}
