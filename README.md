
# danke_bot 🤖

基于 [simbot (simpler-bot) ](https://github.com/ForteScarlet/simpler-robot) 框架的QQ机器人，感谢这个框架的作者，大家可以去给他点点星，很好的框架。

## 如何使用 👇
1. 将代码 clone 到本地：https://github.com/strive-zwy/danke_bot.git。
2. 打开 src --> main --> resources --> simbot-bots --> danke.bot 文件，将内容改为自己的QQ号和密码，code=QQ号，password=密码。
3. 因为网站端是QQ扫码登录的，所以需要去QQ互联去申请应用，申请成功后会得到APP ID和APP KEY，然后打开 src --> main --> java --> com.danke.controller --> LoginController.java，将申请好的clientId（APP ID）、clientSecret（APP KEY）、redirectUri（回调地址）填写进去。
![img.png](https://obohe.com/i/2022/02/26/z6o842.png)
4. 将 application.yml 中数据库相关配置修改为自己的，flyway 的 url 也要修改，这样代码运行后就会在你自己的数据库中自动建好所需的表了。
5. 然后就可以运行啦。
6. 网站演示地址：[danke-bot主页 (dankebot.top)](http://www.dankebot.top/)
![danke_bot 主页](https://obohe.com/i/2022/02/26/z7ue14.jpg)