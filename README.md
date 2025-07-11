# Приложение BIN-Checker 💳🔍

Приложение **BIN-Checker** разработано для быстрой и удобной проверки банковских идентификационных номеров (BIN). 

Введя первые 6-8 цифр номера банковской карты, пользователи могут получить подробную информацию о:
* **Схеме карты** (Visa, Mastercard, Maestro и т.д.).
* **Типе карты** (дебетовая, кредитная).
* **Бренде карты**.
* **Статусе предоплаты**.
* **Банке-эмитенте** (название, URL, номер телефона, город).
* **Стране происхождения** (название, emoji-флаг, валюта, географические координаты).

**Интерактивные детали:**
Для удобства пользователя, приложение позволяет взаимодействовать с полученными данными:
* **Банковский URL:** При нажатии на URL банка, открывается веб-браузер с соответствующей страницей банка.
* **Номер телефона банка:** Клик по номеру телефона банка предлагает совершить звонок через стандартное приложение телефона.
* **Географические координаты:** Нажатие на широту и долготу страны открывает карту (например, Google Maps).

## 🚀 Технологии

* **Язык программирования:** Kotlin;
* **UI:** Jetpack Compose;
* **Архитектурный шаблон:** MVVM (Model-View-ViewModel) и Clean Architecture;
* **Асинхронные операции и потоки данных:** Kotlin Coroutines и Flow.
* **Локальное хранение данных:** Room Database
* **Сетевые запросы:** Retrofit для выполнения HTTP-запросов к внешним API и Gson для сериализации/десериализации JSON.
* **Dependency Inversion:** Hilt

* **Использумое API:** [Binlist API](https://binlist.net/) для получения всей информации о BIN.


## Пример работы приложения

https://github.com/user-attachments/assets/f79303bf-6d47-4a83-92b2-b016ff36cd38
