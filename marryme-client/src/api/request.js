import axios from 'axios'

const baseUrl = 'http://localhost:8929'  
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({                                                             
	// axios中请求配置有baseURL选项，表示请求URL公共部分
	// baseURL: process.env.VUE_APP_BASE_API,
	baseURL: baseUrl,
	// 超时（毫秒）
	timeout: 5000,
	crossDomain: true
})
 
 // request拦截器,在请求之前做一些处理
service.interceptors.request.use(
	config => {
		config.headers.Authorization = uni.getStorageSync('token');
		return config;
	},
	error => {
		 return Promise.reject(error);
	}
); 
 
 //配置成功后的拦截器
service.interceptors.response.use(res => {
	if (res.data.code== 200) {
		return res.data;
	} else {
		return Promise.reject(res.data.message);
	}
}, error => {
	if (error.data.code) {
		switch (error.data.code) {
			case 401:
				 //可能是token过期，清除它
				uni.removeStorage('token')
			default:
				break;
		}
	}
	return Promise.reject(error)
})
 
export default service