<template>
  <div class="footer">
    <div class="top">
      <a href="https://github.com/vwvm/store">
        <img src="src/assets/github-mark-white.png" alt="https://github.com/vwvm/store">
      </a>
    </div>
    <div class="bottom">
      <a href="">关于我们</a>
      <a href="">营销中心</a>
      <a href="">廉正举报</a>
      <a href="">联系客服</a>
      <a href="">开放平台</a>
      <a href="">法律声明</a>
      <a href="">知识产权</a>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyFooter"
}
</script>

<style scoped lang="scss">
.footer {
  background-color: #2f2f2f;
  height: 120px;

  .top {
    width: 80vw;
    margin:  auto;
    padding: 20px;
    text-align: center;

    a {
      margin: 0 auto;
    }

    a > img {
      width: 40px;
    }
  }

  .bottom {
    width: 80vw;
    margin: 0 auto;
    text-align: center;

    .github-log > img {
      width: 40px;
    }

    a {
      color: #B0B0B0;
      margin: 4px;
    }

    a:hover {
      color: white;

    }
  }


}

</style>