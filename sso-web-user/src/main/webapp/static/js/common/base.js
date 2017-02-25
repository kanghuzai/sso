/**
 * 自动消失的消息提示
 * @param text 消息文本
 * @param delayTime 显示时间
 */
function minShow(text, delayTime) {
    var d = dialog({
        content: '<p class="red">' + text + '</p>',
        fixed: true
    });
    d.show();
    if(delayTime != undefined) {
        var waiting = delayTime;
    } else {
        var waiting = 2000;
    }
    setTimeout(function() {
        d.close().remove();
    }, waiting);
}

/**
 * 弹出提示信息
 * @param {Object} msg
 */
function art(msg) {
    var d = dialog({
        title: '提示信息',
        width: 158,
        content: msg,
        okValue: '确定',
        ok: function() {
            this.close();
        }
    });
    d.show();
    setTimeout(function() {
        d.close().remove();
    }, 3000);
}
