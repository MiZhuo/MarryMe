
import request from '@/api/request'
 
/**
 * 授权登录
 * @param {*} data 
 */
export function wxLogin(data) {
	return request({
		url: '/wedding/hq-applets-user/wxLogin',
		method: 'post',
		params: {},
		data
	})
}
