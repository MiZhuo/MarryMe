//get请求
const get = function(url, data = null, contentType = 'application/json') {
	return new Promise((resolve, reject) => {
		uni.showLoading({mask: true})
		uni.request({
			url: url,
			data,
			header: {
			// || '' 考虑到登录API没有token。
				'Authorization': uni.getStorageSync('token') || '',
				'Content-Type': contentType
			},
			success: (res) => {
				uni.hideLoading()
				// 成功时调用sesolve函数，至于参数传什么自行体会
				// 下面这个仅供参考
				resolve()
				/*if (res.data.success) {
					resolve(res.data.message)
				} else if (res.statusCode == '401') {
					uni.showToast({
						title: res.data.message,
						icon: "none",
						duration: 1500
					});
					uni.redirectTo({
						url: "/pages/home/login"
					});*/
				}
			},
			fail: (err) => {
				uni.hideLoading()
				return reject(err)
			}
		});

	})
}

//post请求
const post = function(url, data = null, contentType = 'application/json') {

	return new Promise((resolve, reject) => {
		uni.showLoading({mask: true})
		uni.request({
			url: url,
			data,
			method: 'POST',
			header: {
				'Authorization': uni.getStorageSync('token'),
				'Content-Type': contentType
			},
			success: (res) => {
				uni.hideLoading()
				// 成功时调用sesolve函数，至于参数传什么自行体会
				// 下面这个仅供参考
				resolve()
				/*if (res.data.success) {
					resolve(res.data.message)
				} else if (res.statusCode == '401') {
					uni.showToast({
						title: res.data.msg,
						icon: "success",
						duration: 1500
					});
					uni.redirectTo({
						url: "/pages/home/login"
					});
				}*/
			},
			fail: (err) => {
				uni.hideLoading()
				return reject(err)
			}
		});

	})

}

export default {
	get,
	post
}