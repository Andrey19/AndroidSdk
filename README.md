# AndroidSdk
Приложение "Android SDK" - 4 задачи


#Задача 1. Навигация между 3 фрагментами
Где: task1/

Что сделано:

Router.kt - интерфейс

FragmentRouter.kt - роутер на FragmentManager (без библиотек)

FirstFragment, SecondFragment, ThirdFragment - три экрана с кнопками

Как работает: Роутер переключает фрагменты через replace() + addToBackStack()

#Задача 2. WorkManager
Где: task2/

Что сделано:

ChargingWorker.kt - проверяет зарядку и шлет уведомление

Task2Fragment.kt - кнопка для планирования

Как работает: WorkManager с условием setRequiresCharging(true) выполняется один раз при подключении зарядки

#Задача 3. Кастомная View
Где: task3/

Что сделано:

FillableRectangleView.kt - прямоугольник, при клике +10% заливки, цвет рандомный

Как работает: При клике увеличивается процент, цвет меняется через Random, при 100% - сброс

#Задача 4. Чат

Что сделано:

ChatMessage.kt - модель

ChatAdapter.kt - адаптер RecyclerView (свои/чужие сообщения)

ChatViewModel.kt - хранение сообщений

Task4Fragment.kt - UI с полем ввода и кнопкой

Ответ на задачу 4 (текст)

Нужно:

RecyclerView + Adapter с двумя типами сообщений (свои/чужие)

ViewModel с LiveData для хранения списка

EditText + Button для отправки

Библиотеки: RecyclerView, Lifecycle, Material

Алгоритм: Пользователь пишет → добавляем в список → отправляем на сервер → обновляем статус

Структура
text
app/src/main/java/.../
├── MainActivity.kt
├── task1/ (роутер + 3 фрагмента)
├── task2/ (WorkManager)
├── task3/ (кастомная View)
└── task4/ (чат)
