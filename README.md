基于springmvc和jsonp技术实现单点登录

## 1、系统配置
- 修改本机host的文件,模拟多个域名网站，不能直接用ip加端口（jessionid在浏览器中会覆盖）
```
192.168.50.68 www.ssouser.com
192.168.50.68 www.domain1.com
192.168.50.68 www.domain2.com
```
备注：hosts文件路径【C:\Windows\System32\drivers\etc】

- Redis配置
```
// 443行，默认是不加密码认证的
requirepass 123456
```
- tomcat端口配置
```
sso-web-user        8080   http://www.ssouser.com:8080
sso-web-domain1     8081   http://www.domain1.com:8081
sso-web-domain1     8081   http://www.domain2.com:8082
```
备注：端口和域名目前也在代码中写死了，如果要自定义请同步修改代码。

