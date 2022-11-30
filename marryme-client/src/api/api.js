import service from './request.js'

export function accessToken(data) {
	return service({
		url: '/auth/oauth/token',
		method: 'post',
		params: {
			'client_id' : 'marry-app',
			'client_secret' : 'mizhuo123',
			'grant_type' : 'password',
			'password' : 'mizhuo123',
			'username' : 'mizhuo',
		},
		data
	})
}

export function getFriendsGroup(data) {
	return service({
		url: '/friends/group',
		method: 'get',
		params: {},
		data
	})
}