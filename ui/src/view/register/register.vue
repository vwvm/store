<template>
  <div class="loginbody">
    <div class="logindata">
      <title>注册</title>

      <div class="login-boxtitle" style="height: 100px;">
        <div class="logoBig">
          log
        </div>
      </div>

      <div class="login-banner">
        <div class="login-main">

          <div class="login-box" id="container">
            <div class="logintext">
              <h2>用户注册</h2>
            </div>
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
                  <input type="password" v-model="user.rePassword" placeholder="请再次输入密码"
                         @keyup="chickRegisterInfo">
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

    </div>
  </div>

</template>

<script>
import axios from "axios";

const baseUrl = "import.meta.env.VITE_API_DOMAIN";
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