/**
 *  发送GET请求，以url带参编码请求参数，返回JSON数据类型
 * @param url 请求地址
 * @param req 发送的数据
 * @param bizSuccessCallBack 业务成功回调函数
 * @param bizFailCallBack 业务失败回调函数
 * @param head Http请求头
 */
function getJson(url, req, bizSuccessCallBack, bizFailCallBack, head) {
    var _settings = {};
    if(req != null || req != undefined){
        _settings.data = req;
    }
    _settings.type = "GET";
    _settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    _settings.dataType = "json"
    if(head != null || head != undefined){
        _settings.dataType = head.dataType;
    }
    _settings.success = function (res) {
        if(commonCodeFilter(res)){
            return;
        }
        if(res.code == "common-base-1"){
            if(bizSuccessCallBack != null || bizSuccessCallBack != undefined){
                bizSuccessCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
        else{
            if(bizFailCallBack != null || bizFailCallBack != undefined){
                bizFailCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
    }
    _settings.error = function (res) {
        alert(JSON.stringify(res));
    }
    $.ajax(url, _settings);
}

/**
 * 发送post请求，用application/x-www-form-urlencoded编码请求数据，返回JSON数据类型
 * @param url  请求的url
 * @param req  请求的json对象
 * @param bizSuccessCallBack 业务成功回调函数
 * @param bizFailCallBack 业务失败回调函数
 */
function postForm(url, req, bizSuccessCallBack, bizFailCallBack) {
    var _settings = {};
    if(req != null || req != undefined){
        _settings.data = req;
    }
    _settings.type = "POST";
    _settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    _settings.dataType = "json"
    _settings.success = function (res) {
        if(commonCodeFilter(res)){
            return;
        }
        if(res.code == "common-base-1"){
            if(bizSuccessCallBack != null || bizSuccessCallBack != undefined){
                bizSuccessCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
        else{
            if(bizFailCallBack != null || bizFailCallBack != undefined){
                bizFailCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
    }
    _settings.error = function (res) {
        alert(JSON.stringify(res));
    }
    $.ajax(url, _settings);
}

/**
 * 发送post请求，用application/json编码请求数据，返回JSON数据类型
 * @param url  请求的url
 * @param req  请求的json对象
 * @param bizSuccessCallBack 业务成功回调函数
 * @param bizFailCallBack 业务失败回调函数
 */
function postJson(url, req, bizSuccessCallBack, bizFailCallBack) {
    var _settings = {};
    if(req != null || req != undefined){
        _settings.data = req;
    }
    _settings.type = "POST";
    _settings.contentType = "application/json;charset=utf-8";
    _settings.dataType = "json";
    _settings.success = function (res) {
        if(commonCodeFilter(res)){
            return;
        }
        if(res.code == "common-base-1"){
            if(bizSuccessCallBack != null || bizSuccessCallBack != undefined){
                bizSuccessCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
        else{
            if(bizFailCallBack != null || bizFailCallBack != undefined){
                bizFailCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
    }
    _settings.error = function (res) {
        alert(JSON.stringify(res));
    }
    $.ajax(url, _settings);
}

/**
 * 发送jsonp（GET）请求，用url带参的方式编码请求数据，返回JSON数据类型
 * @param url  请求的url
 * @param req  请求的json对象
 * @param bizSuccessCallBack 业务成功回调函数
 * @param bizFailCallBack 业务失败回调函数
 */
function getJsonp(url, req, bizSuccessCallBack, bizFailCallBack) {
    var _settings = {};
    if(req != null || req != undefined){
        _settings.data = req;
    }
    _settings.type = "GET";
    _settings.contentType = "application/x-www-form-urlencoded; charset=UTF-8";
    _settings.cache = false;
    _settings.crossDomain = true;
    _settings.dataType =  "jsonp";
    _settings.scriptCharset = "utf-8";
    _settings.jsonp = "jsonpcallback";
    _settings.success = function (res) {
        if(commonCodeFilter(res)){
            return;
        }
        if(res.code == "common-base-1"){
            if(bizSuccessCallBack != null || bizSuccessCallBack != undefined){
                bizSuccessCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
        else{
            if(bizFailCallBack != null || bizFailCallBack != undefined){
                bizFailCallBack(res.data, res.page);
            }
            else{
                minShow(JSON.stringify(res));
            }
        }
    }
    _settings.error = function (res) {
        alert(JSON.stringify(res));
    }
    $.ajax(url, _settings);
}

/**
 * 过滤系统非成功的通用业务码
 * @param res
 * @returns {boolean}
 */
function commonCodeFilter(res) {
    if(res.code == "common-base-2"){
        console.log(JSON.stringify(res));
        minShow(res.msg, 5000);
        return true;
    }
    else if(res.code == "common-base-3"){
        console.log(JSON.stringify(res));
        minShow(res.msg, 5000);
        return true;
    }
    else if(res.code == "common-base-4"){
        console.log(JSON.stringify(res));
        redictLoginUrl();
        return true;
    }
    else if(res.code == "common-base-5"){
        console.log(JSON.stringify(res));
        minShow(res.msg, 5000);
        return true;
    }
    else if(res.code == "common-base-6"){
        console.log(JSON.stringify(res));
        minShow(res.msg, 5000);
        return true;
    }
    else if(res.code == "common-base-7"){
        console.log(JSON.stringify(res));
        minShow(res.msg, 5000);
        return true;
    }
    return false;
}

/**
 * 获取Url参数值
 * @param name
 * @returns {null}
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if(r != null) return unescape(r[2]);
    return null; //返回参数值
}

/**
 * 重定向到业务url地址
 */
function redictBackUrl() {
    var currUrl = location.href;
    var clearUrl = currUrl.substring(0, currUrl.indexOf('?')) + "?redirectUrl=";
    location.href = currUrl.replace(clearUrl, "");
}

/**
 * 重定向到ssouser的登入页
 */
function redictLoginUrl() {
    var currUrl= location.href;
    if(currUrl.indexOf("redirectUrl") == -1) {
        location.href = "http://www.ssouser.com:8080/login.html" + "?redirectUrl=" + self.location.href;
    }
}

