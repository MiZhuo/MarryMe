import service from './request.js'

const SERVER_URL = '/friends'

/**
 * 添加亲友分组
 */
export function addFriendGroup(data){
	return service({
		url:  SERVER_URL + '/group',
		method: 'post',
		params: data
	})
}

/**
 * 获取当前登陆用户的亲友分组
 */
export function getGroupList(){
	return service({
		url: SERVER_URL + '/group',
		method: 'get'
	})
}

/**
 * 更新分组名称
 */
export function updateFriendGroup(data){
	return service({
		url: SERVER_URL + '/group',
		method: 'put',
		params: data
	})
}

/**
 * 删除分组
 */
export function deleteFriendGroup(id){
	return service({
		url: SERVER_URL + '/group/' + id,
		method: 'delete'
	})
}

/**
 * 添加亲友信息
 */
export function addFriend(data){
	return service({
		url: SERVER_URL + '/friend',
		method: 'post',
		params: data
	})
}

/**
 * 根据分组ID获取亲友列表
 */
export function getFriendsList(id,keyword){
	return service({
		url: SERVER_URL + '/search/' + id + '/' + keyword,
		method: 'get'
	})
}

/**
 * 根据亲友ID获取亲友信息
 */
export function getFriendInfo(id){
	return service({
		url: SERVER_URL + '/friend/' + id,
		method: 'get'
	})
}

/**
 * 更新亲友信息
 */
export function updateFriendInfo(data){
	return service({
		url: SERVER_URL + '/friend',
		method: 'put',
		params: data
	})
}

/**
 * 删除亲友
 */
export function deleteFriend(id){
	return service({
		url: SERVER_URL + '/friend/' + id,
		method: 'delete'
	})
}
