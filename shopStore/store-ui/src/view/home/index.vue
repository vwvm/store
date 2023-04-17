<template>

  <link href="/src/assets/css/amazeui.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/admin.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/demo.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/hmstyle.css" rel="stylesheet" type="text/css"/>
  <link href="/src/assets/css/skin.css" rel="stylesheet" type="text/css"/>


  <!--悬浮搜索框-->
  <el-main>
    <el-header>
      <el-row>
        <el-col :span="6">
          <div class="menu-hd" v-if="isLogin">
            {{ username }},欢迎您
          </div>
          <div class="menu-hd" v-else>
            亲，请<a href="login.html" target="_top" class="h">登录</a>
            <a href="#" target="_top">免费注册</a>
          </div>
        </el-col>
        <el-col :span="12"></el-col>
        <el-col :span="6">

          <a href="/index.html" target="_top" class="h">商城首页</a>

          <a href="user-center.html" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a>

          <a id="mc-menu-hd" href="shopcart.html" target="_top">
            <i class="am-icon-shopping-cart  am-icon-fw"></i>

            <router-link to="/shopcart">购物车</router-link>

            <strong id="J_MiniCartNum" class="h"></strong>
          </a>

          <a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a>

        </el-col>
      </el-row>
    </el-header>
    <div class="nav white">

      <div class="logoBig">
        <img src="/src/assets/images/logo.png" style="margin-left: 0" alt="logo">
      </div>

      <div class="search-bar pr">
        <a name="index_none_header_sysc" href="#"></a>
        <form action="search.html">
          <input id="searchInput" v-model="keyword" name="index_none_header_sysc" type="text" placeholder="搜索"
                 autocomplete="off">
          <input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="button" @click="toSearch">
        </form>
      </div>
    </div>
    <div class="clear"></div>


    <div class="long-title"><span class="all-goods">全部分类</span></div>

    <el-container>
      <!--导航 -->
      <el-cascader-panel :options="categoryBeanList"
                         :props="{expandTrigger:'hover' , label:'categoryName',children:'categoryBeanList', }"
                         @change="selectView"
      />

      <!--轮播图 走马灯-->
      <el-carousel :interval="5000" arrow="always">
        <el-carousel-item v-if="indexImages.length>0" v-for="img in indexImages" :key="img">
          <img :src="'/src/assets/images/slideshow/'+img.imgUrl">
        </el-carousel-item>
      </el-carousel>
      <el-aside>
        <!--小导航 -->
        <div class="am-g am-g-fixed smallnav">
          <div class="am-u-sm-3">
            <a href="sort.html"><img src="/src/assets/images/navsmall.jpg"/>
              <div class="title">商品分类</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="/src/assets/images/huismall.jpg" alt=""/>
              <div class="title">大聚惠</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="/src/assets/images/mansmall.jpg" alt=""/>
              <div class="title">个人中心</div>
            </a>
          </div>
          <div class="am-u-sm-3">
            <a href="#"><img src="/src/assets/images/moneysmall.jpg" alt=""/>
              <div class="title">投资理财</div>
            </a>
          </div>
        </div>
      </el-aside>
    </el-container>


    <div class="marqueen">
      <div class="demo">
        <div class="advTip"><img src="/src/assets/images/advTip.jpg"/></div>
      </div>
    </div>
    <div class="clear"></div>

    <div class="shopMain" id="shopmain">

      <!--新品推荐 -->
      <div class="am-g am-g-fixed recommendation">
        <div class="clock am-u-sm-3">
          <img src="/src/assets/images/2016.png" style="border-radius: 50%;"/>
          <p>新品<br>推荐</p>
        </div>
        <a v-for="rp in recommendProducts" :href="'introduction?pid='+rp.productId">
          <div class="am-u-sm-4 am-u-lg-3 ">
            <div class="info ">
              <h3>{{ rp.productName }}</h3>
              <h4>销量：{{ rp.soldNum }}</h4>
            </div>
            <div class="recommendationMain one">
              <img v-if="rp.imgs.length>0" :src="'/src/static/pimgs/'+rp.imgs[0].url"/>
            </div>
          </div>
        </a>
      </div>

      <div class="clear "></div>
      <!--热门活动 -->

      <div class="am-container activity ">
        <div class="shopTitle ">
          <h4>活动</h4>
          <h3>每期活动 优惠享不停 </h3>
          <span class="more ">
                              <a href="# ">全部活动<i class="am-icon-angle-right" style="padding-left:10px ;"></i></a>
          </span>
        </div>
        <div class="am-g am-g-fixed ">
          <div class="am-u-sm-3 ">
            <div class="icon-sale one "></div>
            <h4>秒杀</h4>
            <div class="activityMain ">
              <img src="/src/assets/images/activity1.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 ">
            <div class="icon-sale two "></div>
            <h4>特惠</h4>
            <div class="activityMain ">
              <img src="/src/assets/images/activity2.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 ">
            <div class="icon-sale three "></div>
            <h4>团购</h4>
            <div class="activityMain ">
              <img src="/src/assets/images/activity3.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

          <div class="am-u-sm-3 last ">
            <div class="icon-sale "></div>
            <h4>超值</h4>
            <div class="activityMain ">
              <img src="/src/assets/images/activity.jpg "/>
            </div>
            <div class="info ">
              <h3>春节送礼优选</h3>
            </div>
          </div>

        </div>
      </div>
      <div class="clear "></div>


      <div v-for="(rc,index) in recommendCategory">
        <div style="width: 300px;height: 300px">hhhhhh</div>

        <div v-if="rc.productBeanList.length > 0" :id="'f'+(index+1)">
          <!--甜点-->
          <div class="am-container ">
            <div class="shopTitle ">
              <h4>{{ rc.categoryName }}</h4>
              <h3>{{ rc.categorySlogan }}</h3>
              <span class="more ">
		                    <a href="# ">更多美味<i class="am-icon-angle-right" style="padding-left:10px ;"></i></a>
		                        </span>
            </div>
          </div>
          <div class="am-g am-g-fixed floodFour">
            <div class="am-u-sm-5 am-u-md-4 text-one list ">
              <div class="word">
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
                <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>
              </div>
              <a href="# ">
                <div class="outer-con ">
                  <div class="title ">
                    开抢啦！
                  </div>
                  <div class="sub-title ">
                    零食大礼包
                  </div>
                </div>
                <img :src="'/src/assets/images/'+rc.categoryPic"/>
              </a>
              <div class="triangle-topright"></div>
            </div>

            <div class="am-u-sm-7 am-u-md-4 text-two sug" v-if="rc.productBeanLIst.length > 0">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[0].productName }}</div>
                <div class="sub-title ">
                  销量：{{ rc.productBeanLIst[0].soldNum }}
                </div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[0].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[0].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-7 am-u-md-4 text-two" v-if="rc.productBeanLIst.length > 1">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[1].productName }}</div>
                <div class="sub-title ">
                  销量：{{ rc.productBeanLIst[1].soldNum }}
                </div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[1].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[1].imgs[0].url"/></a>
            </div>


            <div class="am-u-sm-3 am-u-md-2 text-three big" v-if="rc.productBeanLIst.length > 2">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[2].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[2].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[2].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[2].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three sug" v-if="rc.productBeanLIst.length > 3">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[3].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[3].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[3].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[3].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three " v-if="rc.productBeanLIst.length > 4">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[4].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[4].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[4].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[4].imgs[0].url"/></a>
            </div>

            <div class="am-u-sm-3 am-u-md-2 text-three last big " v-if="rc.productBeanLIst.length > 5">
              <div class="outer-con ">
                <div class="title ">{{ rc.productBeanLIst[5].productName }}</div>
                <div class="sub-title ">销量：{{ rc.productBeanLIst[5].soldNum }}</div>
                <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
              </div>
              <a href="# "><img v-if="rc.productBeanLIst[5].imgs.length>0"
                                :src="'static/pimgs/'+rc.productBeanLIst[5].imgs[0].url"/></a>
            </div>

          </div>
          <div class="clear "></div>
        </div>
      </div>

    </div>
  </el-main>


  <div class="common-layout" v-for="(item,index) in categoryBeanList" :key="index">
    <div id="f2">
      <!--坚果-->
      <div class="am-container ">
        <div class="shopTitle ">
          <h4>{{categoryBeanList[index].categoryName}}</h4>
          <h3>酥酥脆脆，回味无穷</h3>
          <div class="today-brands" >
            <a href="# " v-for="(item2, index2) in item.categoryBeanList" :key="index2">{{ item2.categoryName }}</a>
          </div>
          <span class="more ">
                    <a href="# ">更多美味<i class="am-icon-angle-right" style="padding-left:10px ;"></i></a>
                        </span>
        </div>
      </div>
      <div class="am-g am-g-fixed floodThree ">
        <div class="am-u-sm-4 text-four list">
<!--          <div class="word">-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--            <a class="outer" href="#"><span class="inner"><b class="text">核桃</b></span></a>-->
<!--          </div>-->
          <a href="# ">
            <img :src="'/src/assets/images/' + item.categoryIcon"/>
            <div class="outer-con ">
              <div class="title" v-bind="hallo">
                {{ item.categorySlogan }}
              </div>
            </div>
          </a>
          <div class="triangle-topright" ></div>
        </div>
        <div class="am-u-sm-4 text-four" >
          <a href="# ">
            <img :src="'/src/assets/images/' + item.categoryBeanList[0].categoryBeanList.categoryIcon"/>
            <div class="outer-con ">
              <div class="title " v-for="item3 in item.categoryBeanList[0].categoryBeanList">
<!--                {{ item.categoryBeanList[0].categoryBeanList[0].categoryName }}-->
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>

        </div>
        <div class="am-u-sm-4 text-four sug">
          <a href="# ">
            <img src="static/images/7.jpg"/>
            <div class="outer-con ">
              <div class="title ">
                雪之恋和风大福
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>
        </div>

        <div class="am-u-sm-6 am-u-md-3 text-five big ">
          <a href="# ">
            <img src="static/images/10.jpg"/>
            <div class="outer-con ">
              <div class="title ">
                雪之恋和风大福
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>
        </div>
        <div class="am-u-sm-6 am-u-md-3 text-five ">
          <a href="# ">
            <img src="static/images/8.jpg"/>
            <div class="outer-con ">
              <div class="title ">
                雪之恋和风大福
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>
        </div>
        <div class="am-u-sm-6 am-u-md-3 text-five sug">
          <a href="# ">
            <img src="static/images/9.jpg"/>
            <div class="outer-con ">
              <div class="title ">
                雪之恋和风大福
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>
        </div>
        <div class="am-u-sm-6 am-u-md-3 text-five big">
          <a href="# ">
            <img src="static/images/10.jpg"/>
            <div class="outer-con ">
              <div class="title ">
                雪之恋和风大福
              </div>
              <div class="sub-title ">
                ¥13.8
              </div>
              <i class="am-icon-shopping-basket am-icon-md  seprate"></i>
            </div>
          </a>
        </div>

      </div>

      <div class="clear "></div>
    </div>
  </div>


  <el-footer>

    <div class="footer ">
      <div class="footer-hd ">
        <p>
          <a href="# ">商城</a>
          <b>|</b>
          <a href="# ">商城首页</a>
          <b>|</b>
          <a href="# ">支付宝</a>
          <b>|</b>
          <a href="# ">物流</a>
        </p>
      </div>
      <div class="footer-bd ">
        <p>
          <a href="# ">关于</a>
          <a href="# ">合作伙伴</a>
          <a href="# ">联系我们</a>
          <a href="# ">网站地图</a>
          <em>© .com 版权所有</em>
        </p>
      </div>
    </div>

  </el-footer>

  <!--引导 -->
  <div class="navCir">
    <li class="active"><a href="home.html"><i class="am-icon-home "></i>首页</a></li>
    <li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
    <li>
      <router-link to="/shopcart">购物车</router-link>
    </li>
    <li><a href="person/index.html"><i class="am-icon-user"></i>我的</a></li>
  </div>

  <a v-for="rp in recommendProducts">
    <div class="info ">
      <button>{{ rp }}</button>
      <h3>{{ rp.productName }}</h3>
      <h4>销量：{{ rp.soldNum }}</h4>
    </div>
  </a>


</template>

<script>

import {getCookieValue} from "/src/assets/js/cookie_utils.js";
import axios from "axios";
import {getCurrentInstance, reactive} from "vue";
import Header from "/src/components/header/Header.vue";
import {th} from "element-plus/es/locale/index";

export default {
  name: "index",
  data() {
    return {
      username: "您未登录",
      userImg: "",
      isLogin: false,
      indexImages: [],
      categoryBeanList: [],   //商品列表
      a: {},
      b: {},
      recommendProducts: reactive(this.b),
      recommendCategory: reactive(this.a),
      keyword: "",
      number: 0,
    }
  },
  components: {
    Header,
  },
  computed: {
    recommendProduct: {
      get: function () {
        return this.recommendProducts
      },
      set: function (value) {
        this.recommendProducts = value
      }
    }
  },
  //渲染html前调用
  created() {

    const vm = this
    // 登录信息
    const token = getCookieValue("token");
    console.log(token)
    if (token !== null && token !== "") {
      this.isLogin = true;
      this.username = getCookieValue("username");
      this.userImg = '/src/assets/images/' + getCookieValue("userImg");
    }

    let baseUrl = import.meta.env.VITE_API_DOMAIN
    //轮播图
    const url = baseUrl + "/index/img-list"
    axios.get(url).then((res) => {
      const vo = res.data;
      this.indexImages = vo.data;
      console.log(this.indexImages)
    })
    //商品列表
    const categoryUrl = baseUrl + "/index/category-list";
    axios({
      method: "get",
      url: categoryUrl,
    }).then((res) => {
      const vo = res.data;
      this.categoryBeanList = vo.data
      console.log(this.categoryBeanList)
    })
    // 商品推荐
    const categoryRecommends = baseUrl + "/index/product-recommends"
    axios({
      method: "get",
      url: categoryRecommends,
    }).then((res) => {
      this.recommendProducts = res.data.data
    })
    //分类商品推荐
    const categoryProductsRecommend = baseUrl + "/index/category-recommends"
    axios({
      method: "get",
      url: categoryProductsRecommend,
    }).then((res) => {
      const vo = res.data;
      vm.recommendCategory = vo.data
    })
  },
  directives:{
    triggers:{
      inserted(el, binging){
        console.log("触发了")
        el.click()
      }
    }
  },
  mounted() {
    window.addEventListener("scroll", this.handleScroll, true);
  },
  watch:{
    scrollTopNum: function () {
      if (this.scrollTopNum > 45) {
        this.tabshow = true;
      } else {
        this.tabshow = false;
      }
    },
  },
  methods: {
    hallo: function () {
      console.log("hallo")
    },
    test: function () {


      console.log(this.categoryBeanList)
      this.number++
    },
    selectView: function (t) {
      // console.log()
      console.log(t)
    },
    handleScroll() {
      let top =
          document.documentElement.scrollTop ||
          document.body.scrollTop ||
          window.pageYOffset;
      this.scrollTopNum = top;

    }
  }
}
</script>

<style scoped src="/src/assets/css/index.css"></style>
<style scoped src="/src/assets/css/admin.css"></style>
<style scoped src="/src/assets/css/demo.css"></style>
<style scoped src="/src/assets/css/hmstyle.css"></style>
<style scoped src="/src/assets/css/skin.css"></style>