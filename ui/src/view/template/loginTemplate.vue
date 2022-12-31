<template>
  <div class="loginbody">
    <div class="logindata">
      <div class="logintext">
        <h2>Welcome</h2>
      </div>
      <div class="formdata">
        <el-form ref="form" :model="form" :rules="rules">
          <el-form-item prop="username">
            <el-input
                v-model="form.username"
                clearable
                placeholder="请输入账号"
            ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
                v-model="form.password"
                clearable
                placeholder="请输入密码"
                show-password
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="tool">
        <div>
          <el-checkbox v-model="checked" @change="remenber"
          >记住密码</el-checkbox
          >
        </div>
        <div>
          <span class="shou" @click="forgetpas">忘记密码？</span>
        </div>
      </div>
      <div class="butt">
        <el-button type="primary" @click.native.prevent="login('form')"
        >登录</el-button
        >
        <el-button class="shou" @click="register">注册</el-button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "login",
  data() {
    return {
      form: {
        password: "",
        username: "",
      },
      checked: false,
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { max: 10, message: "不能大于10个字符", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    if(localStorage.getItem("news")){
      this.form=JSON.parse(localStorage.getItem("news"))
      this.checked=true
    }
  },
  methods: {
    login(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          login(this.form)
              .then((res) => {
                if (res.code === 200) {
                  setToken(res.data.token);
                  localStorage.setItem("USERNAME", res.data.username);
                  this.$message({
                    message: "登录成功啦",
                    type: "success",
                    showClose: true,
                  });
                  this.$router.replace("/");
                } else {
                  this.$message({
                    message: "账户名或密码错误",
                    type: "error",
                    showClose: true,
                  });
                }
              })
              .catch((err) => {
                this.$message({
                  message: "账户名或密码错误",
                  type: "error",
                  showClose: true,
                });
              });
        } else {
          return false;
        }
      });
    },
    remenber(data){
      this.checked=data
      if(this.checked){
        localStorage.setItem("news",JSON.stringify(this.form))
      }else{
        localStorage.removeItem("news")
      }
    },
    forgetpas() {
      this.$message({
        type:"info",
        message:"功能尚未开发额😥",
        showClose:true
      })
    },
    register() {},
  },
};
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

