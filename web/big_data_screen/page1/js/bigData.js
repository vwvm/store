function getTime() {
    const date = new Date();
    const year = date.getFullYear(); //获取年份
    const month = date.getMonth() + 1; //获取月份
    const day = date.getDate(); //获取日期
    let hour = date.getHours(); //获取小时
    hour = hour < 10 ? '0' + hour : hour;
    let minute = date.getMinutes(); // 获取分
    minute = minute < 10 ? '0' + minute : minute;
    let seconds = date.getSeconds(); //获取秒
    seconds = seconds < 10 ? '0' + seconds : seconds;
    return  hour + ':' + minute + ':' + seconds;
}

setInterval(function() {
    $("#header-text-right-time").text(getTime())
}, 500)
