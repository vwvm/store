<template>
  <p v-if="isShowInput">收到一封信哦</p>
  <div @click="xxx">
  <canvas></canvas>
  <canvas></canvas>
  <canvas></canvas>
  <canvas id="pinkboard"></canvas>
  </div>
  <div class="main" v-if="isShowInput">
    <el-input placeholder="要输入密码哦" v-model="input">请输入你的名字</el-input>
    <el-button @click="cInput">确定</el-button>
  </div>
</template>

<script setup>
import {onMounted, ref} from "vue";
import {$get} from "@/utils/request.js";
import {ElMessage} from "element-plus";

const input = ref();
const playNum = ref(1);

const playMusic = () => {

  if (playNum.value++ !== 1){
    return
  }
  const audio = new Audio('/src/views/love/music/开开 - 繁花落岸.mp3');
  audio.play();
  console.log(audio)
  // 播放完毕事件
  audio.addEventListener('ended', () => {
    // dosomething...
  });
}


const cInput = () => {
  if (input.value !== '宋艳利' && input.value !== '小公主'){
    ElMessage.error("坏蛋坏蛋")
  }else {
    isShowInput.value = false
    startLove2()
    startLove()
    $get("/user/te", {msg: input.value})
  }
}

let counter = 0;
const rockets = [];
let isShow = ref(false);
let isShowInput = ref(true);
const xxx = () => {
  console.log('点击了')
  isShow.value = true
  playMusic()
}
const startLove2 = () => {
  // CLASSES
  class Shard {
    constructor(x, y, hue) {
      this.x = x;
      this.y = y;
      this.hue = hue;
      this.lightness = 50;
      this.size = 15 + Math.random() * 10;
      const angle = Math.random() * 2 * Math.PI;
      const blastSpeed = 1 + Math.random() * 6;
      this.xSpeed = Math.cos(angle) * blastSpeed;
      this.ySpeed = Math.sin(angle) * blastSpeed;
      this.target = getTarget();
      this.ttl = 100;
      this.timer = 0;
    }

    draw() {
      ctx2.fillStyle = `hsl(${this.hue}, 100%, ${this.lightness}%)`;
      ctx2.beginPath();
      ctx2.arc(this.x, this.y, this.size, 0, 2 * Math.PI);
      ctx2.closePath();
      ctx2.fill();
    }

    update() {
      if (this.target) {
        const dx = this.target.x - this.x;
        const dy = this.target.y - this.y;
        const dist = Math.sqrt(dx * dx + dy * dy);
        const a = Math.atan2(dy, dx);
        const tx = Math.cos(a) * 5;
        const ty = Math.sin(a) * 5;
        this.size = lerp(this.size, 1.5, 0.05);

        if (dist < 5) {
          this.lightness = lerp(this.lightness, 100, 0.01);
          this.xSpeed = this.ySpeed = 0;
          this.x = lerp(this.x, this.target.x + fidelity / 2, 0.05);
          this.y = lerp(this.y, this.target.y + fidelity / 2, 0.05);
          this.timer += 1;
        } else if (dist < 10) {
          this.lightness = lerp(this.lightness, 100, 0.01);
          this.xSpeed = lerp(this.xSpeed, tx, 0.1);
          this.ySpeed = lerp(this.ySpeed, ty, 0.1);
          this.timer += 1;
        } else {
          this.xSpeed = lerp(this.xSpeed, tx, 0.02);
          this.ySpeed = lerp(this.ySpeed, ty, 0.02);
        }
      } else {
        this.ySpeed += 0.05;
        //this.xSpeed = lerp(this.xSpeed, 0, 0.1);
        this.size = lerp(this.size, 1, 0.05);

        if (this.y > c2.height) {
          shards.forEach((shard, idx) => {
            if (shard === this) {
              shards.splice(idx, 1);
            }
          });
        }
      }
      this.x = this.x + this.xSpeed;
      this.y = this.y + this.ySpeed;
    }
  }

  class Rocket {
    constructor() {
      const quarterW = c2.width / 4;
      this.x = quarterW + Math.random() * (c2.width - quarterW);
      this.y = c2.height - 15;
      this.angle = Math.random() * Math.PI / 4 - Math.PI / 6;
      this.blastSpeed = 6 + Math.random() * 7;
      this.shardCount = 15 + Math.floor(Math.random() * 15);
      this.xSpeed = Math.sin(this.angle) * this.blastSpeed;
      this.ySpeed = -Math.cos(this.angle) * this.blastSpeed;
      this.hue = Math.floor(Math.random() * 360);
      this.trail = [];
    }

    draw() {
      ctx2.save();
      ctx2.translate(this.x, this.y);
      ctx2.rotate(Math.atan2(this.ySpeed, this.xSpeed) + Math.PI / 2);
      ctx2.fillStyle = `hsl(${this.hue}, 100%, 50%)`;
      ctx2.fillRect(0, 0, 5, 15);
      ctx2.restore();
    }

    update() {
      this.x = this.x + this.xSpeed;
      this.y = this.y + this.ySpeed;
      this.ySpeed += 0.1;
    }

    explode() {
      for (let i = 0; i < 70; i++) {
        shards.push(new Shard(this.x, this.y, this.hue));
      }
    }
  }

  // 初始化
  const [c1, c2, c3] = document.querySelectorAll('canvas');
  const [ctx1, ctx2, ctx3] = [c1, c2, c3].map(c => c.getContext('2d'));

  let fontSize = 200;

  const shards = [];
  const targets = [];
  const fidelity = 3;

  c2.width = c3.width = window.innerWidth;
  c2.height = c3.height = window.innerHeight;
  ctx1.fillStyle = '#000';


  //中间的字改这里
  const text = 'I love you';

  let textWidth = 99999999;

  while (textWidth > window.innerWidth) {
    ctx1.font = `900 ${fontSize--}px Arial`;
    textWidth = ctx1.measureText(text).width;
  }

  c1.width = textWidth;
  c1.height = fontSize * 1.5;
  ctx1.font = `900 ${fontSize}px Arial`;
  ctx1.fillText(text, 0, fontSize);
  const imgData = ctx1.getImageData(0, 0, c1.width, c1.height);
  for (let i = 0, max = imgData.data.length; i < max; i += 4) {
    const alpha = imgData.data[i + 3];
    const x = Math.floor(i / 4) % imgData.width;
    const y = Math.floor(i / 4 / imgData.width);

    if (alpha && x % fidelity === 0 && y % fidelity === 0) {
      targets.push({x, y});
    }
  }

  //这里是修改字的颜色
  ctx3.fillStyle = '#FFF';
  ctx3.shadowColor = '#FFF';
  ctx3.shadowBlur = 25;

  // ANIMATION LOOP
  (function loop() {
    ctx2.fillStyle = "rgba(0, 0, 0, .1)";
    ctx2.fillRect(0, 0, c2.width, c2.height);
    //ctx2.clearRect(0, 0, c2.width, c2.height);
    counter += 1;
    if (isShow.value){
      rockets.push(new Rocket());
      isShow.value = false
    }
    if (counter % 200 === 0) {
      rockets.push(new Rocket());
    }
    rockets.forEach((r, i) => {
      r.draw();
      r.update();
      if (r.ySpeed > 0) {
        r.explode();
        rockets.splice(i, 1);
      }
    });

    shards.forEach((s, i) => {
      s.draw();
      s.update();

      if (s.timer >= s.ttl || s.lightness >= 99) {
        ctx3.fillRect(s.target.x, s.target.y, fidelity + 1, fidelity + 1);
        shards.splice(i, 1);
      }
    });

    requestAnimationFrame(loop);
  })();

// HELPER FUNCTIONS
  const lerp = (a, b, t) => Math.abs(b - a) > 0.1 ? a + t * (b - a) : b;

  function getTarget() {
    if (targets.length > 0) {
      const idx = Math.floor(Math.random() * targets.length);
      let {x, y} = targets[idx];
      targets.splice(idx, 1);

      x += c2.width / 2 - textWidth / 2;
      y += c2.height / 2 - fontSize / 2;

      return {x, y};
    }
  }
}
const startLove = () => {
  var settings = {
    particles: {
      length: 700,  // 爱心的大小
      duration: 2,  // 爱心扩散速度，越小速度越快
      velocity: 100,  // 爱心扩散速度，越小速度越慢
      effect: -0.75, // 爱心收缩效果，比如：1扩散，-2收缩
      size: 32, // 爱心数量
    },
  };
  (function () {
    var b = 0;
    var c = ["ms", "moz", "webkit", "o"];
    for (var a = 0; a < c.length && !window.requestAnimationFrame; ++a) {
      window.requestAnimationFrame = window[c[a] + "RequestAnimationFrame"];
      window.cancelAnimationFrame = window[c[a] + "CancelAnimationFrame"] || window[c[a] + "CancelRequestAnimationFrame"]
    }
    if (!window.requestAnimationFrame) {
      window.requestAnimationFrame = function (h, e) {
        var d = new Date().getTime();
        var f = Math.max(0, 16 - (d - b));
        var g = window.setTimeout(function () {
          h(d + f)
        }, f);
        b = d + f;
        return g
      }
    }
    if (!window.cancelAnimationFrame) {
      window.cancelAnimationFrame = function (d) {
        clearTimeout(d)
      }
    }
  }());

  var Point = (function () {
    function Point(x, y) {
      this.x = (typeof x !== 'undefined') ? x : 0;
      this.y = (typeof y !== 'undefined') ? y : 0;
    }

    Point.prototype.clone = function () {
      return new Point(this.x, this.y);
    };
    Point.prototype.length = function (length) {
      if (typeof length == 'undefined')
        return Math.sqrt(this.x * this.x + this.y * this.y);
      this.normalize();
      this.x *= length;
      this.y *= length;
      return this;
    };
    Point.prototype.normalize = function () {
      var length = this.length();
      this.x /= length;
      this.y /= length;
      return this;
    };
    return Point;
  })();
  var Particle = (function () {
    function Particle() {
      this.position = new Point();
      this.velocity = new Point();
      this.acceleration = new Point();
      this.age = 0;
    }

    Particle.prototype.initialize = function (x, y, dx, dy) {
      this.position.x = x;
      this.position.y = y;
      this.velocity.x = dx;
      this.velocity.y = dy;
      this.acceleration.x = dx * settings.particles.effect;
      this.acceleration.y = dy * settings.particles.effect;
      this.age = 0;
    };
    Particle.prototype.update = function (deltaTime) {
      this.position.x += this.velocity.x * deltaTime;
      this.position.y += this.velocity.y * deltaTime;
      this.velocity.x += this.acceleration.x * deltaTime;
      this.velocity.y += this.acceleration.y * deltaTime;
      this.age += deltaTime;
    };
    Particle.prototype.draw = function (context, image) {
      function ease(t) {
        return (--t) * t * t + 1;
      }

      var size = image.width * ease(this.age / settings.particles.duration);
      context.globalAlpha = 1 - this.age / settings.particles.duration;
      context.drawImage(image, this.position.x - size / 2, this.position.y - size / 2, size, size);
    };
    return Particle;
  })();
  var ParticlePool = (function () {
    var particles,
        firstActive = 0,
        firstFree = 0,
        duration = settings.particles.duration;

    function ParticlePool(length) {
      // create and populate particle pool
      particles = new Array(length);
      for (var i = 0; i < particles.length; i++)
        particles[i] = new Particle();
    }

    ParticlePool.prototype.add = function (x, y, dx, dy) {
      particles[firstFree].initialize(x, y, dx, dy);
      // handle circular queue
      firstFree++;
      if (firstFree == particles.length) firstFree = 0;
      if (firstActive == firstFree) firstActive++;
      if (firstActive == particles.length) firstActive = 0;
    };
    ParticlePool.prototype.update = function (deltaTime) {
      var i;
      // update active particles
      if (firstActive < firstFree) {
        for (i = firstActive; i < firstFree; i++)
          particles[i].update(deltaTime);
      }
      if (firstFree < firstActive) {
        for (i = firstActive; i < particles.length; i++)
          particles[i].update(deltaTime);
        for (i = 0; i < firstFree; i++)
          particles[i].update(deltaTime);
      }
      // remove inactive particles
      while (particles[firstActive].age >= duration && firstActive != firstFree) {
        firstActive++;
        if (firstActive == particles.length) firstActive = 0;
      }
    };
    ParticlePool.prototype.draw = function (context, image) {
      // draw active particles
      let i;
      if (firstActive < firstFree) {
        for (i = firstActive; i < firstFree; i++)
          particles[i].draw(context, image);
      }
      if (firstFree < firstActive) {
        for (i = firstActive; i < particles.length; i++)
          particles[i].draw(context, image);
        for (i = 0; i < firstFree; i++)
          particles[i].draw(context, image);
      }
    };
    return ParticlePool;
  })();
  (function (canvas) {
    let context = canvas.getContext('2d'),
        particles = new ParticlePool(settings.particles.length),
        particleRate = settings.particles.length / settings.particles.duration, // particles/sec
        time;

    // get point on heart with -PI <= t <= PI
    function pointOnHeart(t) {
      return new Point(
          160 * Math.pow(Math.sin(t), 3),
          130 * Math.cos(t) - 50 * Math.cos(2 * t) - 20 * Math.cos(3 * t) - 10 * Math.cos(4 * t) + 25
      );
    }

    // creating the particle image using a dummy canvas
    var image = (function () {
      var canvas = document.createElement('canvas'),
          context = canvas.getContext('2d');
      canvas.width = settings.particles.size;
      canvas.height = settings.particles.size;

      // helper function to create the path
      function to(t) {
        var point = pointOnHeart(t);
        point.x = settings.particles.size / 2 + point.x * settings.particles.size / 350;
        point.y = settings.particles.size / 2 - point.y * settings.particles.size / 350;
        return point;
      }

      // create the path
      context.beginPath();
      var t = -Math.PI;
      var point = to(t);
      context.moveTo(point.x, point.y);
      while (t < Math.PI) {
        t += 0.01; // baby steps!
        point = to(t);
        context.lineTo(point.x, point.y);
      }
      context.closePath();
      // create the fill
      context.fillStyle = '#ea80b0';
      context.fill();
      // create the image
      var image = new Image();
      image.src = canvas.toDataURL();
      return image;
    })();

    // render that thing!
    function render() {
      // next animation frame
      requestAnimationFrame(render);
      // update time
      var newTime = new Date().getTime() / 1000,
          deltaTime = newTime - (time || newTime);
      time = newTime;
      // clear canvas
      context.clearRect(0, 0, canvas.width, canvas.height);
      // create new particles
      var amount = particleRate * deltaTime;
      for (var i = 0; i < amount; i++) {
        var pos = pointOnHeart(Math.PI - 2 * Math.PI * Math.random());
        var dir = pos.clone().length(settings.particles.velocity);
        particles.add(canvas.width / 2 + pos.x, canvas.height / 2 - pos.y, dir.x, -dir.y);
      }
      // update and draw particles
      particles.update(deltaTime);
      particles.draw(context, image);
    }

    // handle (re-)sizing of the canvas
    function onResize() {
      canvas.width = canvas.clientWidth;
      canvas.height = canvas.clientHeight;
    }

    window.onresize = onResize;
    // delay rendering bootstrap
    setTimeout(function () {
      onResize();
      render();
    }, 10);
  })(document.getElementById('pinkboard'));
}


onMounted(() => {
})
</script>

<style scoped>
html,
body {
  height: 100%;
  padding: 0;
  margin: 0;
  background: #000;
}

canvas {
  position: absolute;
  width: 100%;
  height: 100%;
}

p {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: pink;
  animation: k 1.5s ease-in-out infinite;
}

@keyframes k {
  100% {
    font-size: 24px;
    opacity: 0;
  }
}

.main {
  text-align: center; /*让div内部文字居中*/
  border-radius: 20px;
  width: 300px;
  height: 350px;
  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: url("img.png") no-repeat -20px -30px;
  background-size: 340px;
}
</style>