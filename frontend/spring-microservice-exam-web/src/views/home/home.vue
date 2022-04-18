<template>
  <div>
    <el-row class="hero-area" type="flex" justify="center" align="middle">
      <el-col :span="24">
        <div class="hero-content">
          <h2>HelloTeam 教考分离系统</h2>
          <h4>基于Spring Cloud搭建的新一代微服务教学管理平台</h4>
          <h4>提供多租户、权限管理、考试、练习等功能</h4>
          <router-link to="/courses" class="btn clever-btn">开始使用</router-link>
        </div>
      </el-col>
    </el-row>
    <div class="cool-facts-area padding-80-0">
      <el-row type="flex" justify="center" :gutter="50">
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="counts.host" :duration="2600" class="counter"/>
              </h2>
              <h5>用户数</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="counts.exam" :duration="2600" class="counter"/>
              </h2>
              <h5>考试数</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="counts.question" :duration="2600" class="counter"/>
              </h2>
              <h5>题目数</h5>
            </div>
          </transition>
        </el-col>
        <el-col :span="4">
          <transition name="fade-transform" mode="out-in">
            <div class="single-cool-facts-area mb-80" v-show="showFacts">
              <div class="icon">
                <img src="static/img/core-img/star.png" alt="">
              </div>
              <h2>
                <count-to :start-val="0" :end-val="counts.course" :duration="2600" class="counter"/>
              </h2>
              <h5>课程数</h5>
            </div>
          </transition>
        </el-col>
      </el-row>
    </div>

    <div class="popular-courses-area padding-80-0">
      <el-row>
        <el-col :span="24">
          <div class="section-heading">
            <h3>热门租户</h3>
          </div>
        </el-col>
      </el-row>
      <el-row type="flex" justify="center" :gutter="50">
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course" v-show="showCourses">
              <img :src="hotHost.one.avatarUrl" alt="">
              <div class="course-content">
                <h4>{{ hotHost.one.name }}</h4>
                <p>{{`老师数：${hotHost.one.teacher}`}}</p>
                <p>{{`学生数：${hotHost.one.student}`}}</p>
                <p>{{`课程数：${hotHost.one.course}`}}</p>
              </div>
            </div>
          </transition>
        </el-col>
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course mb-80" v-show="showCourses">
              <img :src="hotHost.two.avatarUrl" alt="">
              <div class="course-content">
                <h4>{{hotHost.two.name}}</h4>
                <p>{{`老师数：${hotHost.two.teacher}`}}</p>
                <p>{{`学生数：${hotHost.two.student}`}}</p>
                <p>{{`课程数：${hotHost.two.course}`}}</p>
              </div>
            </div>
          </transition>
        </el-col>
        <el-col :span="6">
          <transition name="fade-transform" mode="out-in">
            <div class="single-popular-course mb-80" v-show="showCourses">
              <img :src="hotHost.three.avatarUrl" alt="">
              <div class="course-content">
                <h4>{{hotHost.three.name}}</h4>
                <p>{{`老师数：${hotHost.three.teacher}`}}</p>
                <p>{{`学生数：${hotHost.three.student}`}}</p>
                <p>{{`课程数：${hotHost.three.course}`}}</p>
              </div>
            </div>
          </transition>
        </el-col>
      </el-row>
    </div>

    <div v-if="isActive" class="go-top-box" @click="goTop(step)">
      <i class="top-icon el-icon-caret-top"></i>
    </div>
    <o-footer></o-footer>
  </div>
</template>

<script>
import OFooter from '../common/footer'
import CountTo from 'vue-count-to'

export default {
  props: {
    step: {
      type: Number,
      default: 50
    }
  },
  data () {
    return {
      isActive: false,
      showFacts: false,
      showCourses: false,
      showBlog: false,
      counts: {
        host: 200,
        exam: 300,
        course: 233,
        question: 600
      },
      hotHost: {
        one: {
          name: '北郊大',
          avatarUrl: 'static/img/bg-img/c1.jpeg',
          teacher: 100,
          student: 100,
          course: 200
        },
        two: {
          name: '北脚大',
          avatarUrl: 'static/img/bg-img/c2.jpeg',
          teacher: 100,
          student: 100,
          course: 200
        },
        three: {
          name: '北蕉大',
          avatarUrl: 'static/img/bg-img/c3.jpeg',
          teacher: 100,
          student: 100,
          course: 200
        }
      }
    }
  },
  components: {
    OFooter,
    CountTo
  },
  methods: {
    // 返回顶部
    goTop: function (i) {
      document.documentElement.scrollTop -= i
      if (document.documentElement.scrollTop > 0) {
        setTimeout(() => this.goTop(i), 16)
      }
    },
    // 请求总的数据
    fetchCounts: () => {
      console.log('请求了一次counts数据')
    },
    // 请求热门租户数据
    fetchHotHost: () => {
      console.log('请求了一次热门租户数据')
    }
  },
  created () {
    let vm = this
    this.fetchCounts()
    this.fetchHotHost()
    window.onscroll = function () {
      vm.isActive = document.documentElement.scrollTop > 60
      if (document.documentElement.scrollTop > 250) {
        setTimeout(() => {
          vm.showCourses = true
        }, 350)
      }

      if (document.documentElement.scrollTop > 650) {
        setTimeout(() => {
          vm.showBlog = true
        }, 350)
      }
    }
    setTimeout(() => {
      vm.showFacts = true
    }, 350)
  }
}
</script>
