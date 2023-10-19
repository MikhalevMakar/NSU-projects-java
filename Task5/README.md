# Задание №5: Сетевое программирование, сериализация, XML

## Описание задания

В данной задаче вам предстоит создать программу для общения через Интернет, состоящую из серверной и клиентской частей. Сервер запускается как отдельное приложение на определенном порту (конфигурируемом значении), а клиентская часть представляет собой приложение на Swing, которое соединяется с сервером, используя имя сервера и номер порта.

### Минимальные возможности чата

1. Каждый участник чата имеет собственный ник, который указывается при присоединении к серверу.
2. Пользователи могут просматривать список участников чата.
3. Пользователи могут отправлять сообщения в чат для всех участников.
4. Клиент отображает все сообщения, отправленные в чат с момента подключения, а также некоторое количество предыдущих сообщений, и обновляет список сообщений в реальном времени.
5. Сервер логгирует все события, происходящие на его стороне, и это можно включать или отключать в конфигурационном файле.
6. Чат работает через TCP/IP протокол.

### Два варианта реализации

Необходимо создать две версии клиента/сервера: одну, использующую сериализацию/десериализацию Java-объектов для отправки/получения сообщений, и вторую, использующую XML-сообщения.

### Протокол XML-сообщений

Клиент и сервер должны поддерживать стандартный протокол для варианта XML. Протокол описан ниже. В начале XML-сообщения содержатся 4 байта (Java int) с длиной сообщения в байтах, затем считывается само сообщение и обрабатывается как XML-документ.

#### Регистрация
a. **Сообщение от клиента**

      ```xml
      <command name="login">
          <name>USER_NAME</name>
          <type>CHAT_CLIENT_NAME</type>
      </command>
      ```

      * `USER_NAME` - имя пользователя
      * `CHAT_CLIENT_NAME` - тип клиента

b. **Ответ от сервера (ошибка)**

      ```xml
      <error>
          <message>REASON</message>
      </error>
      ```

      * `REASON` - причина ошибки

c. **Ответ от сервера (успех)**

      ```xml
      <success>
          <session>UNIQUE_SESSION_ID</session>
      </success>
      ```

      * `UNIQUE_SESSION_ID` - уникальный идентификатор сессии

2. **Запрос списка пользователей чата**

   a. **Сообщение от клиента**

      ```xml
      <command name="list">
          <session>UNIQUE_SESSION_ID</session>
      </command>
      ```

    * `UNIQUE_SESSION_ID` - уникальный идентификатор сессии

   b. **Ответ от сервера (ошибка)**

      ```xml
      <error>
          <message>REASON</message>
      </error>
      ```

    * `REASON` - причина ошибки

   c. **Ответ от сервера (успех)**

      ```xml
      <success>
          <listusers>
              <user>
                  <name>USER_1</name>
                  <type>CHAT_CLIENT_1</type>
              </user>
              <!-- Добавьте других пользователей, если есть -->
          </listusers>
      </success>
      ```

    * `USER_1`, `CHAT_CLIENT_1` - имя и тип пользователя

3. **Сообщение от клиента серверу**

   a. **Сообщение от клиента**

      ```xml
      <command name="message">
          <message>MESSAGE</message>
          <session>UNIQUE_SESSION_ID</session>
      </command>
      ```

    * `MESSAGE` - текст сообщения
    * `UNIQUE_SESSION_ID` - уникальный идентификатор сессии

   b. **Ответ от сервера (ошибка)**

      ```xml
      <error>
          <message>REASON</message>
      </error>
      ```

    * `REASON` - причина ошибки

   c. **Ответ от сервера (успех)**

      ```xml
      <success>
      </success>
      ```

4. **Сообщение от сервера клиенту**

   a. **Сообщение от сервера**

      ```xml
      <event name="message">
          <message>MESSAGE</message>
          <name>CHAT_NAME_FROM</name>
      </event>
      ```

    * `MESSAGE` - текст сообщения
    * `CHAT_NAME_FROM` - имя отправителя

5. **Отключение**
   a. **Сообщение от клиента серверу**

      ```xml
      <command name="logout">
          <session>UNIQUE_SESSION_ID</session>
      </command>
      ```

    * `UNIQUE_SESSION_ID` - уникальный идентификатор сессии

   b. **Ответ от сервера (ошибка)**

      ```xml
      <error>
          <message>REASON</message>
      </error>
      ```

    * `REASON` - причина ошибки

   c. **Ответ от сервера (успех)**

      ```xml
      <success>
      </success>
      ```

6. **Новый клиент**
   a. **Сообщение от сервера**

      ```xml
      <event name="userlogin">
          <name>USER_NAME</name>
      </event>
      ```

    * `USER_NAME` - имя нового участника

7. **Клиент отключился**
   a. **Сообщение от сервера**

      ```xml
      <event name="userlogout">
          <name>USER_NAME</name>
      </event>
      ```

    * `USER_NAME` - имя отключившегося участника



![login.png](src%2Fmain%2Fresources%2Fassets%2Flogin.png)
![chat.png](src%2Fmain%2Fresources%2Fassets%2Fchat.png)