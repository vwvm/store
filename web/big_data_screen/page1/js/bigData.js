function getTime() {
    const date = new Date();
    let hour = date.getHours(); //获取小时
    hour = hour < 10 ? '0' + hour : hour;
    let minute = date.getMinutes(); // 获取分
    minute = minute < 10 ? '0' + minute : minute;
    let seconds = date.getSeconds(); //获取秒
    seconds = seconds < 10 ? '0' + seconds : seconds;
    return  hour + ':' + minute + ':' + seconds;
}
function getData() {
    const date = new Date();
    const year = date.getFullYear(); //获取年份
    const month = date.getMonth() + 1; //获取月份
    const day = date.getDate(); //获取日期
    return  year + '-' + month + '-' + day;
}

function getWeek() {
    const weekDays = ['日', '一', '二', '三', '四', '五', '六'];
    const today = new Date();
    return "星期" + weekDays[today.getDay()];
}
setInterval(function() {
    $("#header-text-right-time").text(getTime())
}, 500)


$("#header-text-right-data").text(getData())
setInterval(function () {
    $("#header-text-right-data").text(getData())
}, 5000)

$("#header-text-right-week").text(getWeek())
setInterval(function () {
    $("#header-text-right-week").text(getWeek())
}, 5000)

const windowHeight = window.screen.height / 1080
const windowWidth = window.screen.width / 1920
const differenceValueWidth = window.screen.width - 1920
const differenceValueHeight = window.screen.height - 1920
console.log(windowHeight)
document.querySelector('body').style.transform = `scale(${windowWidth}, ${windowHeight})`;
document.querySelector('body').style.transformOrigin = "0 0"
//translateX(12%) translateY(12%)
//translateX(${differenceValueWidth/3}px) translateY(${-differenceValueHeight/3}px)
// $("body").css("transform", `scale(1.3,1.3})`)

