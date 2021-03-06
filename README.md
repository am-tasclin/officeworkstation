# OfficeWorkStation
Офісна робоча станція (**ОРС - [OfficeWorkStation, OWS](http://jast002.algoritmed.com/)**) - офісний пакет програм і сервісів в [розпреділеній базі даних](https://uk.wikipedia.org/wiki/Розподілена_база_даних) на відкритих веб технологіях.

Забезпечує документообіг підриємства в единій інфраструктурі бази даних, з уніфікованим контентом і парадигмі файлової системи.  

# База даних
База даних ОРС є [реляційна база даних](https://uk.wikipedia.org/wiki/Реляційна_база_даних) (БД) змодельована як [документо-орієнтована](https://uk.wikipedia.org/wiki/Документо-орієнтована_система_керування_базами_даних) БД з підтримкою стандартів SQL:2016 ([ISO/IEC 9075:2016](https://uk.wikipedia.org/wiki/SQL:2016)) та реєстрації метаданих ([ISO/IEC 11179](https://en.wikipedia.org/wiki/ISO/IEC_11179)). Внутрішня розподілена база даних підтримується через таблицю зв'язків локальних ідентифікаторів. Цілісність гетерогенної розподіленої бази даних супроводжується SQL-скриптом перетворення даних, або галузевим стандартом як [FHIR](https://en.wikipedia.org/wiki/Fast_Healthcare_Interoperability_Resources) в [eHealth](https://en.wikipedia.org/wiki/EHealth). 

Стартова БД ([Postgres](http://algoritmed.com/db/mis001pg/)) для інсталяції **офісної робочої станції**.

# Документ
Документ в ОРС - зміст БД об'єднаний під единим началом і прив'язаний до ОРС документу формата.

## Електроні таблиці
Електронна таблиця іноді табличний процесор ОРС, застосунок який додає формули і формат до табличних даних зібраних у вигляді документа.
Демо строчних та стовбчастих функції на прикладі [транспортно-заготівельних витрат](http://jast002.algoritmed.com/f/c/11/eh004.html?doc2doc=369426,369343&views=doc).

## Текстовий редактор
Розширений редактор HTML сторінки з можливістью поєднання ОРС документа форми з ОРС документом змісту.
Зміст [документа ОРС](https://github.com/am-tasclin/officeworkstation/blob/master/README.md#документ) переноситься в шаблон [Microsoft Word](https://uk.wikipedia.org/wiki/Microsoft_Word) документа з використанням програмних бібліотек [Apache POI](https://en.wikipedia.org/wiki/Apache_POI) з метою друку, створеня pdf документа або обміну з іншими системами.

## WIKI документ
Вбудований редактор HTML сторінки з підтримкою строчного [Markdown](https://uk.wikipedia.org/wiki/Markdown) та HTML синтаксису. HTML шаблони та [AngularJS](https://uk.wikipedia.org/wiki/AngularJS) програмні бібліотеки додають динамічний контент. 

# Портфоліо
Документи, шаблони, програмні бібліотеки, екранні форми, зібрані в окремий пакет для забезпечення роботи за означеною тематикою, постачаються як портфоліо.

## Економічне портфоліо ОРС
Набір шаблонів документів для вирішеня бухгалтерских обрахунків, складских операцій і аналізу роботи підприемства.

## E-Health портфоліо ОРС
Набір шаблонів документів обміну медичною інформацією в [FHIR](https://en.wikipedia.org/wiki/Fast_Healthcare_Interoperability_Resources) форматі. 
