<template>
  <div class="container">
    <div class="typing" v-show="isTest">{{ tpTest }}</div>
    <div class="main">
      <div ref="myBox" class="myBox" @click="jj" :style="'top: '+ move +'px;'">
        <ul v-for="(i, index1) in boxList">
          <li v-for="(j, index2) in i" class="box" :style="'background-color: ' + j +';'" @click="clickEvent(index1,index2)">
          </li>
        </ul>
      </div>
    </div>
    <div style="">
      <el-button type="primary" @click="gameStart()"> 开始游戏</el-button>
      <h2>分数：{{ yourScore }}</h2>
    </div>
  </div>

</template>

<script>
import {$get, $post} from "@/utils/request.js";

export default {
  name: "Home2",
  data() {
    return {
      boxList: [["antiquewhite", "antiquewhite", "antiquewhite", "antiquewhite"],
        ["antiquewhite", "antiquewhite", "antiquewhite", "antiquewhite"],
        ["antiquewhite", "antiquewhite", "antiquewhite", "red"],
        ["violet", "violet", "violet", "red"],
        ["violet", "violet", "violet", "red"],
        ["violet", "violet", "violet", "red"]],
      sleepTime: 40,
      move: -180,
      startSign: false, //开始标记
      selectS: ["#68937f", "#1b6d85", "#2b2b2b", "#3b3b3b", "#4bad4b", "#5bc0de", "#F72862"],
      yourScore: 0,
      leve: 2,
      tpTest: "",
      isTest: false
    }
  },

  methods: {
    gameInit() {
      for (let i = 0; i < 6; i++) {
        const k = Math.floor(Math.random() * 4)
        const l = Math.floor(Math.random() * 7)
        for (let j = 0; j < 4; j++) {
          this.boxList[i][j] = "write"
        }
        this.boxList[i][k] = this.selectS[l]
      }
    },
    // 开始游戏
    gameStart() {
      $get("/user/start")
      if (this.startSign) {
        return
      }
      let ss = this
      window.setInterval(function () {
        for (let i = 0; i < ss.leve; i++) {
          const indexX = ["write", "write", "write", "write"]
          ss.move = ss.move + 1
          if (ss.move === -70) {
            const newArrays = ss.boxList[5].filter(function (res) {
              return res === "write";
            });
            if (newArrays.length !== 4) {
              ss.yourScore--
            }
            ss.move = -180
            ss.boxList.pop()
            const k = Math.floor(Math.random() * 4)
            const l = Math.floor(Math.random() * 7)
            for (let j = 0; j < 4; j++) {
              indexX[j] = "write"
            }
            indexX[k] = ss.selectS[l]
            ss.boxList.unshift(indexX)
          }
        }
        if (ss.yourScore === 5) {
          ss.tpTest = "小公主，试炼开始，加速"
          ss.isTest = true
          ss.leve = 3
          setTimeout(function () {ss.isTest = false}, 5000)
        }
        if (ss.yourScore === 15) {
          ss.tpTest = "小公主，可以哦，要加速了哦"
          ss.isTest = true
          ss.leve = 4
          setTimeout(function () {ss.isTest = false}, 5000)
        }
        if (ss.yourScore === 30) {
          ss.tpTest = "小公主，不错不错，还能继续吗，再次加速"
          ss.isTest = true
          ss.leve = 5
          setTimeout(function () {ss.isTest = false}, 5000)
        }
        if (ss.yourScore === 60) {
          ss.tpTest = "小公主，你还不来，我怎敢老去"
          ss.isTest = true
          ss.leve = 6
          setTimeout(function () {ss.isTest = false}, 10000)
        }
        if (ss.yourScore === 100) {
          ss.tpTest = "小公主，树在，山在，大地在，岁月在，我在"
          $get("/user/to100")
          ss.isTest = true
          ss.leve = 7
          setTimeout(function () {ss.isTest = false}, 10000)
        }
        if (ss.yourScore === 200) {
          ss.tpTest = "小公主，爱你，我一直在"
          $get("/user/to200")
          ss.isTest = true
          ss.leve = 8
          setTimeout(function () {ss.isTest = false}, 10000)
        }

        if (ss.yourScore === -5) {
          // setTimeout(, delay)
        }

      }, this.sleepTime);
    },

    //点击事件
    clickEvent(i, j) {
      console.log("xy", i, j)
      if (this.boxList[i][j] !== "write") {
        this.yourScore++
        this.boxList[i][j] = "write"
      }else {
        this.yourScore--
      }

    },

    // 底部事件
    oEvent() {

    }

  },
  mounted() {
    this.gameInit();
    $get("/user/te")
  }
}
</script>

<style scoped lang="scss">
.container {
  width: 360px;
  height: 512px;
  margin: 20% auto;
  //background-color: antiquewhite;


}

.main {
  overflow: hidden;
  border: 3px solid red;
  height: 512px;

  background: url("./img_1.png")  no-repeat ;
  background-size: 100% 100%;

  .myBox {
    position: relative;
  }

  .box {
    width: 85px;
    height: 110px;
    float: left;
    opacity: 50%;
  }

  html {
    background-color: black;
  }
}

.typing {
  color: orangered;
  font-size: 1em;
  //width: 21em;
  height: 1.5em;
  border-right: 1px solid transparent;
  animation: typing 2s steps(42, end), blink-caret .75s step-end infinite;
  font-family: Consolas, Monaco;
  word-break: break-all;
  overflow: hidden;
}

/* 打印效果 */
@keyframes typing {
  from {
    width: 0;
  }

  to {
    width: 100%;
  }
}

/* 光标 */
@keyframes blink-caret {

  from,
  to {
    border-color: transparent;
  }

  50% {
    border-color: currentColor;
  }
}

</style>