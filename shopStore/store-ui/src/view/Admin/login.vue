<template>
  <div class="loginbody">
    <div class="logindata">
      <el-container>
        <el-header class="logoBig">
          商城logo
        </el-header>
        <el-main class="login-main">
          <el-form
              class="demo-ruleForm"
          >
            <div class="logintext">
              <h2>用户登录</h2>
            </div>
            <el-form-item label="用户">
              <el-input
                  size="large"
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
  </div>
</template>

<script>
import {getCookieValue, setCookieValue} from "../../assets/js/cookie_utils.js";
import axios from "axios";
import {getUrlParam} from "../../assets/js/url_utils.js";

const baseUrl = import.meta.env.VITE_API_DOMAIN;
export default {
  name: "login",
  data() {
    return {
      username: '',
      password: '',
      tips: '',
      colorStyle: 'color:red',
      isRight: false,
      returnUrl: null,
      pid: 0,
      sid: 0,
    }
  },
  created() {
    // 从商品详情页跳转到本页面时，获取提示信息
    this.tips = getUrlParam("tips")
    // 获取returnUrl\pid\sid
    this.returnUrl = getUrlParam("returnUrl")
    this.pid = getUrlParam("pid")
    this.sid = getUrlParam("sid")
  },
  methods: {
    doSubmit: function () {
      if (this.isRight) {
        this.tips = ""
        const url = baseUrl + "/users/login";
        axios.get(url, {
          params: {
            username: this.username,
            password: this.password,
          }
        }).then((res) => {
          const resData = res.data
          if (res.data.code === 0) {
            //登录验证成功后
            setCookieValue("token", resData.msg);
            setCookieValue("username", resData.data.username);
            setCookieValue("userImg", resData.data.userImg)
            setCookieValue("userId", resData.data.userId)
            // console.log(resData.msg())
            if (this.returnUrl !== null) {
              console.log("key")
              window.location.href = this.returnUrl + "?pid=" + this.pid + "&sid=" + this.sid;
            } else {
              window.location.href = "/index";
            }
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

<style scoped>
.loginbody {
  width: 100%;
  height: 100%;
  min-width: 1000px;
  background-image: url("../assets/login2.jpg");
  background-size: 100% 100%;
  background-position: center center;
  overflow: auto;
  background-repeat: no-repeat;
  position: fixed;
  line-height: 100%;
  padding-top: 150px;
}

.logintext {
  margin-bottom: 20px;
  line-height: 50px;
  text-align: center;
  font-size: 30px;
  font-weight: bolder;
  color: white;
  text-shadow: 2px 2px 4px #000000;
}

.logindata {
  width: 400px;
  height: 300px;
  transform: translate(-50%);
  margin-left: 50%;
}

.tool {
  display: flex;
  justify-content: space-between;
  color: #606266;
}

.butt {
  margin-top: 10px;
  text-align: center;
}

.shou {
  cursor: pointer;
  color: #606266;
}

/*ui*/
/* /deep/ .el-form-item__label {
  font-weight: bolder;
  font-size: 15px;
  text-align: left;
}

/deep/ .el-button {
  width: 100%;
  margin-bottom: 10px;

} */
</style>
