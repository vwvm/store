<template>
  <meta charset="UTF-8">
  <title>登录</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">

  <link rel="stylesheet" href="../assets/css/amazeui.css"/>
  <link href="../assets/css/dlstyle.css" rel="stylesheet" type="text/css">
  <link rel="stylesheet" href="../assets/css/bootstrap.min.css"/>
  <body>

  <div class="login-boxtitle" style="height: 100px;">
    <div class="logoBig">
      商城logo
    </div>
  </div>

  <div class="login-banner">
    <div class="login-main">
      <div class="login-banner-bg"><span></span><img src="../assets/images/big.jpg"/></div>

      <div class="login-box" style="margin-top: 20px;" id="container">

        <h3 class="title">登录商城</h3>
        <h5 :style="colorStyle" id="tips">{{ tips }}&nbsp;</h5>
        <div class="clear"></div>

        <div class="login-form" style="background: none; margin-top: 15px;">
          <form>
            <div class="user-name" style="margin-top: 20px;">
              <label for="user"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></label>
              <input type="text" v-model="username" placeholder="邮箱/手机/用户名" @keyup="checkInfo">
            </div>
            <div class="user-pass" style="margin-top: 20px;">
              <label for="password"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
              <input type="password" v-model="password" placeholder="请输入密码" @keyup="checkInfo">
            </div>
          </form>
        </div>

        <div class="login-links">
          <label for="remember-me"><input id="remember-me" type="checkbox">记住密码</label>
          <a href="#" class="am-fr">忘记密码</a>
          <br/>
        </div>
        <div class="am-cf">
          <input type="button" @click="doSubmit" value="登 录" class="am-btn am-btn-primary am-btn-sm">
        </div>
        <div class="am-cf">
          <input type="button" onclick="window.location.href='register.html'" value="注 册"
                 class="am-btn am-btn-default am-btn-sm">
        </div>
        <div class="partner">

        </div>

      </div>
    </div>
  </div>

  <div class="footer ">
    <div class="footer-hd ">

    </div>
    <div class="footer-bd ">
      <p>
        <a href="# ">联系我们</a>
        <a href="# ">网站地图</a>
      </p>
    </div>
  </div>

  </body>
</template>

<script>
export default {
  name: "login",
  data() {
    return {
      username: '',
      password: '',
      tips: '',
      colorStyle: '',
    }
  },
  methods: {
    doSubmit: function () {
      if (this.username === "") {
        this.tips = "请输入账号";
        this.colorStyle = "color:red"
      }
    }
  }

}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>