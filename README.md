# Лабораторные работы по Java SE
Лабораторные работы представляют собой реализацию проекта жилищного здания для изучения основных механизмов Java.

Проект состоит из пакета Buildings жилых зданий, в которых содержится 2 пакета Dwelling и Office, содержащие классы для создания помещений, этажей и зданий.

Возможно создать жилое помещение, офис и отель, которые объединённые одними интерфейсами Space, Floor, Building. Реализация этих классов соответствуют общей функциональности классов помещений, этажей и зданий.

Что реализовано в проекте:
1) Обработка исключений, в случае их возникновения.
2) Реализация классов офисного этажа и офисного здания основано на односвязном и двусвязном циклических списках с выделенной головой.
3) Принцип создания динамических структур и концепций интерфейсов.
4) Механизм систем ввода/вывода данных в Java. (Запись/чтение из байтового потока, запись/чтение из символьного потока, сериализация)
4) В классах зданий реализованы методы toString(), equals(), hashcode() и clone().
5) Применены механизмы наследования классов, переопределения методов, а также реализованы паттерны проектирования «Comparator», «Iterator» и «Abstract factory».
6) Реализована базовая многопоточность. Создано два потокобезопасных класса (SequentalRepairer и SequentalCleaner) с помощью вспомогательного класса-семафора. Также реализован класс SynchronizedFloor - класс-декоратор, который имплементирует методы интерфейса Floor и Object, обеспечивая безопасность с точки зрения многопоточности.