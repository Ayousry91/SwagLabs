package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.ConnectionType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DriverFactory {

    public static WebDriver getNewInstance(String browserName) {
        ChromeOptions chromeOptions;
        DesiredCapabilities capabilities;
        Map<String, Object> prefs;
        switch (browserName.toLowerCase()) {
            case "chrome-headless":
                chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--no-proxy-server");
                chromeOptions.addArguments("--remote-allow-origins=*");
                return new ChromeDriver(chromeOptions);
            case "firefox":
                return new FirefoxDriver();
            case "firefox-headless":
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                firefoxBinary.addCommandLineOptions("--window-size=1280x720");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                return new FirefoxDriver(firefoxOptions);
            case "edge":
                return new EdgeDriver();
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                return new SafariDriver(safariOptions);
            case "mobile":
                // Configure Chrome for mobile emulation
                ChromeOptions mobileOptions = new ChromeOptions();
                Map<String, Object> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone 12 Pro"); // Emulate iPhone X
                mobileOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                WebDriver mobileDriver = new ChromeDriver(mobileOptions);
                return mobileDriver;
            case "slow-3g":
                // Configure Chrome for Slow 3G network
                ChromeOptions slow3gOptions = new ChromeOptions();
                WebDriver slow3gDriver = new ChromeDriver(slow3gOptions);
                // Enable DevTools
                DevTools devTools = ((ChromeDriver) slow3gDriver).getDevTools();
                devTools.createSession();
                devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                // Set network conditions to Slow 3G
                devTools.send(Network.emulateNetworkConditions(
                        false, // offline
                        500,  // latency (ms)
                        50000, // download throughput (bytes/sec)
                        50000, // upload throughput (bytes/sec)
                        Optional.of(ConnectionType.CELLULAR3G)
                ));
                return slow3gDriver;
            case "fast-3g":
                // Configure Chrome for Fast 3G network
                ChromeOptions fast3gOptions = new ChromeOptions();
                WebDriver fast3gDriver = new ChromeDriver(fast3gOptions);
                // Enable DevTools
                DevTools devTools2 = ((ChromeDriver) fast3gDriver).getDevTools();
                devTools2.createSession();
                devTools2.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
                // Set network conditions to Fast 3G
                devTools2.send(Network.emulateNetworkConditions(
                        false, // offline
                        150,  // latency (ms)
                        1500000, // download throughput (bytes/sec)
                        750000, // upload throughput (bytes/sec)
                        Optional.of(ConnectionType.CELLULAR3G)
                ));

                return fast3gDriver;
            default:
                chromeOptions = new ChromeOptions();
                // TODO: handle browsers options
                prefs = new HashMap<String, Object>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.default_content_setting_values.notifications", 2);
                // Disable Chrome automation detection
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                // Disable loading images for faster crawling
                //chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
                // Optionally add more obfuscation, like custom user agent
                chromeOptions.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--incognito");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--no-proxy-server");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.setExperimentalOption("prefs", prefs);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                chromeOptions.merge(capabilities);
                return new ChromeDriver(chromeOptions);
        }
    }


}