<template>
  <title>注册</title>

  <div class="login-boxtitle" style="height: 100px;">
    <div class="logoBig">
      log
    </div>
  </div>

  <div class="login-banner" style="background: rgb(142,213,21);">
    <div class="login-main">
      <div class="login-banner-bg"><span></span><img width="400" src="../assets/images/act2.png"/></div>

      <div class="login-box" id="container" style="background-color: beige">
        <h3 class="title">用户注册</h3>
        <h5 :style="colorStyle">&nbsp;{{ tips }}</h5>
        <div class="clear"></div>
        <div class="login-form" style="background: none;">
          <form>
            <div class="user-name">
              <label for="user"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></label>
              <input type="text" v-model="user.username" placeholder="邮箱/手机/用户名" @keyup="chickRegisterInfo">
              <br/>
            </div>

            <div class="user-pass" style="margin-top: 15px;">
              <label for="password"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
              <input type="password" v-model="user.password" placeholder="请输入密码" @keyup="chickRegisterInfo">
            </div>
            <div class="user-pass" style="margin-top: 15px;">
              <label for="password"><span class="glyphicon glyphicon-lock" aria-hidden="true"></span></label>
              <input type="password" v-model="user.rePassword" placeholder="请再次输入密码" @keyup="chickRegisterInfo">
            </div>
          </form>
        </div>
        <div class="login-links"><br/></div>
        <div class="am-cf">
          <input type="button" @click="doRegister" value="提交注册" class="am-btn am-btn-primary am-btn-sm">
        </div>
        <div class="partner"></div>
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

</template>

<script>
import axios from "axios";

const baseUrl = "http://localhost:8080";
export default {
  name: "register",
  data() {
    return {
      colorStyle: "color:red",
      tips: "",
      isRight: false,
      user: {
        username: "",
        password: "",
        rePassword: "",
      }
    }
  },
  methods: {
    doRegister: function () {
      if (!this.isRight) {
        this.tips = "请输入完整注册信息";
      } else {
        const url = baseUrl + "/users/register"
        axios({
          method: "post",
          url,
          data: {
            username: this.user.username,
            password: this.user.password
          }
        }).then((res) => {
          if (res.data.code !== 0) {
            this.tips = res.data.msg
          } else {
            this.colorStyle = "color:green";
            this.tips = "恭喜你注册成功";
            setTimeout(function () {
              window.location.href = '/login'
            }, 3000);
            //恢复状态
            this.tips = "";
            this.colorStyle = "color:red"
          }

        })
      }
    },
    chickRegisterInfo: function () {
      //校验账号
      if (this.user.username === "") {
        this.tips = "请输入账号";
        this.isRight = false;
      } else if (this.user.username < 8 || this.user.username > 20) {
        this.tips = "账号长度为8-20";
        this.isRight = false;
      } else {
        if (this.user.username === "") {
          this.tips = "请输入密码";
          this.isRight = false;
        } else if (this.user.username < 8 || this.user.username > 20) {
          this.tips = "密码长度为8-20";
          this.isRight = false;
        } else {
          if (this.user.rePassword === "") {
            this.tips = " 请再次输入密码 ";
            this.isRight = false;
          }
          if (this.user.password !== this.user.rePassword) {
            this.tips = "两次密码输入不一致";
          } else {
            this.tips = "";
            this.isRight = true;
          }
        }
      }
      //校验密码
      //校验重复密码
    }
  }

}
</script>

<style scoped>

</style>