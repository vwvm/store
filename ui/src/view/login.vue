<template>
  <div class="common-layout">
    <el-container>
      <el-header class="logoBig">
        商城logo
      </el-header>
      <el-main class="login-main">
        <el-row>
          <el-col :span="14">

          </el-col>
          <el-col :span="7">
            <el-form
                class="demo-ruleForm"
                style="background-color: #FD7A72"
            >
              <h3 class="title">登录商城</h3>
              <el-form-item label="用户">
                <el-input
                    v-model="username"
                    placeholder="请输入用户名"
                    @keyup="checkUserName"
                />
              </el-form-item>
              <el-form-item label="密码">
                <el-input
                    v-model="password"
                    type="password"
                    autocomplete="off"
                    placeholder="请输入密码"
                    show-password
                    @keyup="checkUserName"
                />
              </el-form-item>
              <h5 :style="colorStyle" id="tips">{{ tips }}</h5>
              <div class="login-links">
                <label for="remember-me"><input id="remember-me" type="checkbox">记住密码</label>
                <a href="#" class="am-fr">忘记密码</a>
                <br/>
              </div>
              <div class="am-cf">
                <el-button type="primary" @click="doSubmit" value="登 录">登 录</el-button>
                <el-button type="primary" value="注 册">
                  <router-link to="register">注 册</router-link>
                </el-button>
              </div>
            </el-form>
          </el-col>
          <el-col :span="4"/>
        </el-row>
      </el-main>
      <el-footer>
        <div class="footer-hd ">

        </div>
        <div class="footer-bd ">
          <p>
            <a href="# ">联系我们 </a>
            <a href="# ">网站地图 </a>
          </p>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import {setCookieValue} from "../../static/js/cookie_utils.js";
import axios from "axios";

const baseUrl = "http://localhost:8080";
export default {
  name: "login",
  data() {
    return {
      username: '',
      password: '',
      tips: '',
      colorStyle: 'color:red',
      isRight: false,
    }
  },
  methods: {
    register: function () {

    },
    doSubmit: function () {
      if (this.isRight) {
        this.tips = ""
        console.log("T")
        const url = baseUrl + "/users/login";
        axios.get(url, {
          params: {
            username: this.username,
            password: this.password,
          }
        }).then((res) => {
          if (res.data.code === 0) {
            //登录验证成功后
            setCookieValue("token", res.data.msg)
            window.location.href = "/index";
          } else {
            this.tips = "账号或者密码错误"
          }
        })
      } else {
        this.tips = "请正确输入账号密码"
      }
    },
    checkUserName: function () {
      if (this.username === "") {
        this.tips = "请输入账号";
        this.isRight = false
      } else if (this.username.length < 8 || this.username.length > 20) {
        this.tips = "账号长度不符合"
        this.isRight = false
      } else {
        //校验密码
        if (this.password === "") {
          this.tips = "请输入密码";
          this.isRight = false
        } else if (this.username.length < 8 || this.username.length > 20) {
          this.tips = "密码长度不符合"
          this.isRight = false
        } else {
          this.isRight = true;
          this.tips = ""
        }
      }
    }
  }
}

</script>

<style scoped lang="scss">
.login-main {
  background-image: url("../assets/images/big.jpg");
  background-repeat: no-repeat;
  background-size: cover;
}

.el-row {
  margin-bottom: 200px;
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