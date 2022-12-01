import service from './request.js'

/**
 * 获取token
 */
export function accessToken() {
	return service({
		url: '/auth/oauth/token',
		method: 'post',
		params: {
			'client_id' : 'marry-app',
			'client_secret' : 'mizhuo123',
			'grant_type' : 'password',
			'password' : 'mizhuo123',
			'username' : 'mizhuo',
		}
	})
}
 