## <a href="https://escape-room-neon.vercel.app/"><img alt="EscapeRoom" height="52" src="images/logo/logo.svg" width="134"/></a>
# Проект по автоматизации тестирования для мобильного приложения [Wikipedia](https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0)

* [Репозиторий с проектом](https://github.com/nryazanov13/my_project_18_mob/tree/emulator)

## **Содержание:**
____

* <a href="#tools">Технологии и инструменты</a>

* <a href="#cases">Примеры автоматизированных тест-кейсов</a>

* <a href="#jenkins">Сборка в Jenkins</a>

* <a href="#console">Запуск из терминала</a>

* <a href="#allure">Allure отчет</a>

* <a href="#telegram">Уведомление в Telegram при помощи бота</a>

* <a href="#video">Примеры видео выполнения тестов на Selenoid</a>
____
<a id="tools"></a>
## <a name="Технологии и инструменты">**Технологии и инструменты:**</a>

<p align="center">  
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>  
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="GitHub"/></a>  
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>  
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>  
<a href="https://selenide.org/"><img src="images/logo/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>  
<a href="https://aerokube.com/selenoid/"><img src="images/logo/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>  
<a href="https://allurereport.org/"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>  
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://appium.io/"><img src="images/logo/Appium.svg" width="50" height="50"  alt="Appium"/></a>
</p>

____
<a id="cases"></a>
## <a name="Примеры автоматизированных тест-кейсов">**Примеры автоматизированных тест-кейсов:**</a>
____
- ✓ *Проверка роботоспособности функциональности поиска*
- ✓ *Проверка поиска по определенному значению == Appium*

____
<a id="jenkins"></a>
## <img alt="Jenkins" height="25" src="images/logo/Jenkins.svg" width="25"/></a><a name="Сборка"></a>Сборка в [Jenkins](https://jenkins.autotests.cloud/job/037-sandraboticelli-escaperoom-12/)</a>
____
<p align="center">  
<a href="https://jenkins.autotests.cloud/job/my_project_12/"><img src="images/screen/jenkins.png" alt="Jenkins" width="950"/></a>  
</p>

### Параметры сборки в Jenkins
- **deviceHost** – указывает, на каком стенде будет запускаться тесты  
  Возможные значения: `browserstack` (удалённый запуск на BrowserStack), `emulation` (эмулятор) или `real` (реальное устройство).
- **platform** – платформа устройства, на которой выполняются тесты  
  Возможные значения: `android` или `ios`.

---

### Команды для запуска из терминала
Для локального и удалённого (через Jenkins) запуска необходимо передать те же параметры `deviceHost` и `platform`.

#### Локальный запуск всех тестов
```bash
./gradlew clean test -DdeviceHost=${deviceHost} -Dplatform=${platform}
```
___
<a id="allure"></a>
## <img alt="Allure" height="25" src="images/logo/Allure.svg" width="25"/></a> <a name="Allure"></a>Allure [отчет](https://jenkins.autotests.cloud/job/nryazanov_my_project_18_mob/4/allure/#suites)</a>
___

### *Основная страница отчёта*

<p align="center">  
<img title="Allure Overview Dashboard" src="images/screen/allure_report_1.png" width="850">  
</p>  

### *Тест-кейсы*

<p align="center">  
<img title="Allure Tests" src="images/screen/allure_report_4.png" width="850">  
</p>

### *Графики*

  <p align="center">  
<img title="Allure Graphics" src="images/screen/allure_report_2.png" width="850">

<img title="Allure Graphics" src="images/screen/allure_report_3.png" width="850">  
</p>

____
<a id="video"></a>
## <img alt="Appium" height="25" src="images/logo/Appium.svg" width="25"/></a> Пример видео выполнения тестов на эмуляторе андроид

<p align="center">
<img title="Selenoid Video" src="images/video/video_report.gif" width="300" height=auto  alt="video">   
</p>