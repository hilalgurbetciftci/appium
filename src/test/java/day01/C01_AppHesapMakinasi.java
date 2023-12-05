package day01;

import com.google.common.annotations.VisibleForTesting;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class C01_AppHesapMakinasi {

    // AndroidDriver <AndroidElement>
    // androinDriver sadece android cihazlar icin olusturulmus bir driverdir.
    // Android cihazlar icin gerekli olan hazir methodlari
    // bu driver ile birlikte kullanarak cihaz ile ilgili islemler yapilabilir
    AndroidDriver <AndroidElement> driver;


    // AppiumDriver<MobileElement> ise hem android hem de Ios isletim sistemine sahip
    // cihazlar icin tasarlanmistir
    // iki farkli platformda islemler gerceklestirilebilir

    // her ikisi arasindaki en temel fark
    // daha zengin oldugundan android driver sadece android cihazlara özel tasarlanmış
    // bu da bize android cihazlar daha kullanisli bir driver kullanmamıza fayda sağlar
   // AppiumDriver<MobileElement> driver2;



@Test
public void hesapMakinasiTest() throws MalformedURLException {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 2");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.0");
    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");

    // iki farkli UiAutomator mevcut UiAutomator ve UiAutomator2
    // kullanilan cihazin android surumu 6.0'dan buyukse
    //UiAutomator2 kullanilmasi gerekir.
    // kullanilan cihazin android surumu 6.0'dan kucukse
    //    //UiAutomator kullanilmasi gerekir.
   capabilities.setCapability("app","/Users/hilalciftci/Library/CloudStorage/OneDrive-Kişisel/2023/Appium_T-120/APS/Calculator_8.4 (503542421)_Apkpure.apk");

    driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);



    // dil degisikliginden dolayi  driver.findElementByAccessibilityId her zaman olmayabilir
    // bu sebeple  driver.findElementById("com.google.android.calculator:id/op_mul"); by id kullanilabilir
    driver.findElementById("com.google.android.calculator:id/op_mul");

    // kullanici gerekli kurulumlari yapar
// uygulamanin yuklendigini dogrular(isInstalled)
// uygulamanin acildigini dogrular
// 100 un 5 katininin 500 oldugunu hesap makinasindan dogrulayalim


    Assert.assertTrue(driver.isAppInstalled("com.google.android.calculator"));
    driver.findElementByAccessibilityId("2").click();
    driver.findElementByAccessibilityId("3").click();
    driver.findElementByAccessibilityId("multiply").click();
    driver.findElementByAccessibilityId("2").click();
    driver.findElementByAccessibilityId("2").click();


    // cikan sonucun dogrulugunu test etmek icin

    String sonucElement= driver.findElementById("com.google.android.calculator:id/result_preview").getText();

    Assert.assertEquals(Integer.parseInt(sonucElement),506);



}


}
