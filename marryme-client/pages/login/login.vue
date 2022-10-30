<template>
	<div>
		<u--form :rules="rules" :model="loginForm" ref="loginForm" class="loginContainer">
			<h3 class="loginTitle">系统登录</h3>
			<u-form-item prop="username">
				<u-input type="text" v-model="loginForm.username" placeholder="请输入用户名" prefixIcon="account"
					auto-complete="off"></u-input>
			</u-form-item>
			<u-form-item prop="password">
				<u-input type="password" v-model="loginForm.password" placeholder="请输入密码" prefixIcon="lock"
					auto-complete="off" @keydown.enter.native="submitLogin"></u-input>
			</u-form-item>
			<!-- <u-form-item prop="captcha">
				<img :src="captchaImg" width="100px" height="30px" @click="initCaptcha" />
				<u--input type="text" v-model="loginForm.captcha" placeholder="请输入验证码" prefixIcon="photo"
					auto-complete="off" style="width: 130px;float: right"></u--input>
			</u-form-item> -->
			<!-- #ifndef APP-NVUE -->
			<u-input placeholder="请输入验证码">
			<!-- #endif -->
			<!-- #ifdef APP-NVUE -->
			<u--input placeholder="请输入验证码">
			<!-- #endif -->
				<template slot="suffix">
					<u-code
						ref="uCode"
						@change="codeChange"
						seconds="20"
						changeText="X秒重新获取哈哈哈"
					></u-code>
					<u-button
						@tap="getCode"
						:text="tips"
						type="success"
						size="mini"
					></u-button>
				</template>
			<!-- #ifndef APP-NVUE -->
			</u-input>
			<!-- #endif -->
			<!-- #ifdef APP-NVUE -->
			</u--input>
			<!-- #endif -->
		  <u-checkbox-group
					v-model="checkboxValue1"
					placement="column"
					@change="checkboxChange"
				>
			<u-checkbox class="loginRemember" label="记住密码"></u-checkbox>
			</u-checkbox-group>
			<u-button type="primary" style="width:100%" :loading="this.loading" @click="submitLogin">{{loginBtnText}}
			</u-button>
		</u--form>
	</div>
</template>

<script>
	export default {
		name: "Login",
		data() {
			return {
				loginForm: {
					username: 'admin',
					password: '123',
					captcha: '',
					captchaKey: ''
				},
				checked: true,
				rules: {
					username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}],
					captcha: [{
						required: true,
						message: '请输入验证码',
						trigger: 'blur'
					}]
				},
				loading: false,
				loginBtnText: '登录',
				captchaImg: "",
				tips: '',
				value: '',
				checkboxValue1:[],
				
			}
		},
		methods: {
			// submitLogin(){
			//     this.$refs.loginForm.validate((valid)=>{
			//         if(valid){
			//             this.loading = true;
			//             this.loginBtnText = '正在登录...';
			//             this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
			//                 if(resp){
			//                     this.$store.commit('INIT_CURRENT_HR',resp.result);
			//                     window.sessionStorage.setItem("user",JSON.stringify(resp.result));
			//                     let path = this.$route.query.redirect;
			//                     this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
			//                 }else{
			//                     this.loading = false;
			//                     this.loginBtnText = '登录';
			//                     this.loginForm.captcha = '';
			//                     this.initCaptcha();
			//                 }
			//             });
			//         }else{
			//             return false;
			//         }
			//     });
			// },
			// initCaptcha(){
			//     this.getRequest("/auth/captcha").then((resp)=>{
			//         if(resp){
			//             this.captchaImg = resp.result.img;
			//             this.loginForm.captchaKey = resp.result.captchaKey;
			//         }
			//     });
			// }
			codeChange(text) {
				this.tips = text;
			  },
			  getCode() {
				if (this.$refs.uCode.canGetCode) {
				  // 模拟向后端请求验证码
				  uni.showLoading({
					title: '正在获取验证码'
				  })
				  setTimeout(() => {
					uni.hideLoading();
					// 这里此提示会被this.start()方法中的提示覆盖
					uni.$u.toast('验证码已发送');
					// 通知验证码组件内部开始倒计时
					this.$refs.uCode.start();
				  }, 2000);
				} else {
				  uni.$u.toast('倒计时结束后再发送');
				}
			  },
			  change(e) {
				console.log('change', e);
			  },
			  checkboxChange(n) {
			              console.log('change', n);
			          }
		},
		created() {
			// this.initCaptcha();
		}
	}
</script>

<style>
	.loginContainer {
		border-radius: 15px;
		background-clip: padding-box;
		margin: 180px auto;
		width: 250px;
		padding: 25px 35px 15px 35px;
		background: #fff;
		border: 1px solid #eaeaea;
		box-shadow: 0 0 25px #cac6c6;
	}

	.loginTitle {
		margin: 0px auto 20px auto;
		text-align: center;
		color: #505458;
	}

	.loginRemember {
		text-align: left;
		margin: 0px 0px 15px 0px;
	}
</style>
