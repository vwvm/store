<template>
  <div class="wrap">
    <div class="cube">
      <div class="liang-one">
        <img
            src="./img/img1.jpg"
            class="photo"
        />
      </div>
      <div class="liang-two">
        <img
            src="./img/img2.jpg"
            class="photo"
        />
      </div>
      <div class="liang-three">
        <img
            src="./img/img3.jpg"
            class="photo"
        />
      </div>
      <div class="liang-four">
        <img
            src="./img/img4.jpg"
            class="photo"
        />
      </div>
      <div class="liang-five">
        <img
            src="./img/img5.jpg"
            class="photo"
        />
      </div>
      <div class="liang-six">
        <img
            src="./img/img6.jpg"
            class="photo"
        />
      </div>
      <span class="liang-front">
          <img
              src="./img/me.jpg"
              class="liang-photo"
          />
        </span>
      <span class="liang-back">
          <img
              src="./img/img7.jpg"
              class="liang-photo"
          />
        </span>
      <span class="liang-left">
          <img
              src="./img/img8.jpg"
              class="liang-photo"
          />
        </span>
      <span class="liang-right">
          <img
              src="./img/img9.jpg"
              class="liang-photo"
          />
        </span>
      <span class="liang-top">
          <img
              src="./demo.jpg"
              class="liang-photo"
          />
        </span>
      <span class="liang-bottom">
          <img
              src="./demo.jpg"
              class="liang-photo"
          />
        </span>
    </div>
  </div>

</template>
<script>
import {$post} from "@/utils/request.js";

export default {
  methods: {
    returnFalse(e) {
      e.preventDefault()
    }
  },
  mounted() {
    $post("/user/start", {"相册":"相册"})
    var album = this.$refs.album
    var imgs = this.$refs.album.children
    var len = imgs.length
    var unitDeg = 360 / len
    var newX, newY, lastX, lastY, minusX, minusY
    var rotX = 0
    var rotY = 0
    this.$nextTick(() => {
      Array.prototype.forEach.call(imgs, (it, i, arr) => {
        it.style.transform = `rotateY(${i * unitDeg}deg) translateZ(300px)`
        it.style.transition = `1s ${(len - i) * 0.1}s`
        it.onmousedown = this.returnFalse
      })
      //   鼠标移动 相册旋转 改变album的transform 的rotateX 和 rotateY的值
      document.onmousedown = function (e) { // 鼠标点击事件
        lastX = e.clientX
        lastY = e.clientY
        this.onmousemove = (e) => { // 鼠标移动事件
          newX = e.clientX
          newY = e.clientY

          // 假设 并求值 两次鼠标移动的差值就是移动的距离 和相册旋转的角度成正比
          minusX = newX - lastX
          minusY = newY - lastY

          rotX += minusY
          rotY += minusX

          album.style.transform = `rotateX(${-rotX * 0.2}deg) rotateY(${rotY * 0.2}deg)`

          // 新的值用完之后就成了旧的值
          lastX = newX
          lastY = newY
        }
        this.onmouseup = (e) => { // 鼠标松开事件
          console.log('松开')
          this.onmousemove = null
        }
      }
    })
  },
}
</script>

<style lang="scss" scoped>


.wrap {
  position: relative;
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  width: 200px;
  height: 200px;
  margin: auto;
}

.cube {
  width: 200px;
  height: 200px;
  margin: 0 auto;
  transform-style: preserve-3d;
  transform: rotateX(-30deg) rotateY(-80deg);
  animation: rotate 20s infinite;
  animation-timing-function: linear;
}

@keyframes rotate {
  from {
    transform: rotateX(0deg) rotateY(0deg);
  }
  to {
    transform: rotateX(360deg) rotateY(360deg);
  }
}

.cube div {
  position: absolute;
  width: 200px;
  height: 200px;
  opacity: 0.8;
  transition: all .4s;
}


.photo {
  width: 200px;
  height: 200px;
}

.cube .liang-one {
  transform: rotateY(0deg) translateZ(100px);
}

.cube .liang-two {
  transform: translateZ(-100px) rotateY(180deg);
}

.cube .liang-three {
  transform: rotateY(90deg) translateZ(100px);
}

.cube .liang-four {
  transform: rotateY(-90deg) translateZ(100px);
}

.cube .liang-five {
  transform: rotateX(90deg) translateZ(100px);
}

.cube .liang-six {
  transform: rotateX(-90deg) translateZ(100px);
}


.cube span {
  display: bloack;
  width: 100px;
  height: 100px;
  position: absolute;
  top: 50px;
  left: 50px;
}

.cube .liang-photo {
  width: 100px;
  height: 100px;
}

.cube .liang-front {
  transform: rotateY(0deg) translateZ(50px);
}

.cube .liang-back {
  transform: translateZ(-50px) rotateY(180deg);
}

.cube .liang-left {
  transform: rotateY(90deg) translateZ(50px);
}

.cube .liang-right {
  transform: rotateY(-90deg) translateZ(50px);
}

.cube .liang-top {
  transform: rotateX(90deg) translateZ(50px);
}

.cube .liang-bottom {
  transform: rotateX(-90deg) translateZ(50px);
}



.cube:hover .liang-one {
  transform: rotateY(0deg) translateZ(200px);
}

.cube:hover .liang-two {
  transform: translateZ(-200px) rotateY(180deg);
}

.cube:hover .liang-three {
  transform: rotateY(90deg) translateZ(200px);
}

.cube:hover .liang-four {
  transform: rotateY(-90deg) translateZ(200px);
}

.cube:hover .liang-five {
  transform: rotateX(90deg) translateZ(200px);
}

.cube:hover .liang-six {
  transform: rotateX(-90deg) translateZ(200px);
}

.container {
  height: 100%;
  background: black;
  perspective: 1000px; /*场景景深*/
  &:before {
    content: "";
    display: table;
  }

  .album {
    width: 150px;
    height: 200px;
    // border: solid 1px red;
    margin: 200px auto;
    position: relative;
    transform-style: preserve-3d; /*设置场景风格*/
    img {
      position: absolute;
      -webkit-box-reflect: below 5px -webkit-linear-gradient(top, rgba(250, 250, 250, 0), rgba(250, 250, 250, .0) 30%, rgba(250, 250, 250, 0.3));
    }
  }
}

</style>