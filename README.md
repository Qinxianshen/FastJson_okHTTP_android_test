# FastJson_okHTTP_android_test
用FastJson okHTTP 在android上实现POST 和 GET请求

### 实现步骤：

> * 添加FastJSON 和 okhttp的库到 Gradle
> * 根据对应的JSON字段创建对应的类 写好 Getter 和 Setter的方法
> * 在对应的Activity里写好进程 来发送请求 见代码里的MainActivity
> * 不要忘记在Manifest文件里添加好权限：<uses-permission android:name="android.permission.INTERNET"/>

![添加FastJSON 和 okhttp的库到 Gradle](.pic/1.png)
![根据对应的JSON字段创建对应的类 写好 Getter 和 Setter的方法](.pic/2.png)
![根据对应的JSON字段创建对应的类 写好 Getter 和 Setter的方法](.pic/3.png)
![在对应的Activity里写好进程 来发送请求 见代码里的MainActivity](.pic/4.png)

### 1. FastJson 序列化一个对象成JSON字符串

```java
User user = new User();
user.setName("校长");
user.setAge(3);
user.setSalary(new BigDecimal("123456789.0123"));
String jsonString = JSON.toJSONString(user);
System.out.println(jsonString);
// 输出 {"age":3,"name":"校长","old":false,"salary":123456789.0123}
```

### 2. FastJson 反序列化一个JSON字符串成Java对象

```java
 String jsonString = "{\"age\":3,\"birthdate\":1496738822842,\"name\":\"校长\",\"old\":true,\"salary\":123456789.0123}";
 User u = JSON.parseObject(jsonString ,User.class);
 System.out.println(u.getName());
 // 输出 校长

String jsonStringArray = "[{\"age\":3,\"birthdate\":1496738822842,\"name\":\"校长\",\"old\":true,\"salary\":123456789.0123}]";
List<User> userList = JSON.parseArray(jsonStringArray, User.class);
System.out.println(userList.size());
// 输出 1
```

### 3. okhttp Get方法

```java
OkHttpClient client = new OkHttpClient();

String run(String url) throws IOException {
  Request request = new Request.Builder()
      .url(url)
      .build();

  Response response = client.newCall(request).execute();
  return response.body().string();
}
```

### 4. okhttp POST方法

```java
public static final MediaType JSON
    = MediaType.parse("application/json; charset=utf-8");

OkHttpClient client = new OkHttpClient();

String post(String url, String json) throws IOException {
  RequestBody body = RequestBody.create(JSON, json);
  Request request = new Request.Builder()
      .url(url)
      .post(body)
      .build();
  Response response = client.newCall(request).execute();
  return response.body().string();
}
```

### 参考教程
[FastJSON仓库][2]
[okhttp仓库][3]
[FastJSON教程][4]
[okhttp教程][1]

[1]: https://square.github.io/okhttp/
[2]: https://github.com/alibaba/fastjson
[3]: https://github.com/square/okhttp
[4]: https://github.com/alibaba/fastjson/wiki/Samples-DataBind
