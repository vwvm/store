<template>
  <div style="width: 100vw; height: 100vh">
    <canvas id='myCanvas' width="120" height="120" style='border:2px solid red;'></canvas>
    <div class="main">
      <el-input placeholder="要输入密码哦" v-model="input">请输入你的名字</el-input>
      <el-button @click="cInput">确定</el-button>
    </div>
  </div>
</template>

<script setup>
import {onMounted, reactive, nextTick, ref} from "vue";
import {ElMessage} from "element-plus";
const input = ref();
const cInput = () => {
  if (input.value !== '123'){
    ElMessage.error("坏蛋坏蛋")
  }
}


const state = reactive({
  height: 1200,
  width: 1200,
})
// 屏幕宽度
let ctx;

// 画文字
const drawText = (text, x, y) => {
  ctx.font = '150px 微软雅黑'
  ctx.fillStyle = '#000'
  ctx.textAlign = 'center'
  ctx.textBaseline = 'middle'
  ctx.fillText(text, x, y, state.width)
}

// 画线
const drawLine = () => {
  ctx.beginPath()
  ctx.moveTo(100, 100)
  ctx.lineTo(200, 200)
  ctx.strokeStyle = 'green'
  ctx.lineWidth = 5
  ctx.stroke()
  ctx.closePath()
}

// 画圆
const drawCircle = (x, y, r, color) => {
  ctx.beginPath()
  ctx.arc(x, y, r, 0, 2 * Math.PI, true)
  ctx.fillStyle = color || '#000'
  ctx.fill()
  ctx.closePath()
}

// 画图片
const drawImg = (imgData) => {
  const img = new Image()
  img.src = 'vwvm.png'
  img.onload = function () {
    ctx.drawImage(img, 0, 0)
    const width = img.width;
    const height = img.height;
    const copy = ctx.getImageData(0, 0, width, height)
    // ctx.clearRect(0, 0, width, height)
  }
}

// 定义小球类
const ball = (xEnd, yEnd) => {
  // 起始位置，随机

}

const startCanvas = () => {
  // drawLine()
  drawText('小公主 无', state.width / 2, state.height / 2)
  // drawImg()
  const leap = 3
  const copy = ctx.getImageData(0, 0, state.width, state.height)
  ctx.clearRect(0, 0, state.width, state.height)
  for (let i = 0; i < state.height; i += leap) {
    for (let j = 0; j < state.width; j += leap) {
      const index = j + i * state.width
      const a = copy.data[index * 4 + 3]
      if (a > 128) {
        drawCircle(j, i, 1, '#f00')
      }
    }

  }
}

// 初始化canvas
const initCanvas = () => {
  const c = document.getElementById("myCanvas");
  ctx = c.getContext('2d')
  startCanvas()
}


// 页面加载完获取页面大小
nextTick(() => {
  state.height = document.documentElement.clientHeight;
  state.width = document.documentElement.clientWidth;
  console.log(state.width, state.height)
})

onMounted(() => {
  initCanvas()
})
</script>

<style scoped lang="scss">

canvas {

}

.main {
  text-align: center; /*让div内部文字居中*/
  background-color: #fff;
  border-radius: 20px;
  width: 300px;
  height: 350px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>