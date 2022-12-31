import service from './request.js'

const SERVER_URL = '/goods'

/**
 * 添加商品
 */
export function addGoods(data) {
	return service({
		url: SERVER_URL + '/goods',
		method: 'post',
		params: data
	})
}
 
/**
* 获取商品列表
*/
export function getGoodsList(data){
	return service({
		url: SERVER_URL + '/goodsList',
		method: 'get',
		params: data
	})
}

/**
* 获取商品详情
*/
export function getGoodsInfo(id){
	return service({
		url: SERVER_URL + '/goodsInfo/' + id,
		method: 'get'
	})
}

/**
* 修改商品信息
*/
export function updateGoodsInfo(data){
	return service({
		url: SERVER_URL + '/goods',
		method: 'put',
		params: data
	})
}

/**
* 删除商品
*/
export function deleteGoods(ids){
	return service({
		url: SERVER_URL + '/goods/' + ids,
		method: 'delete'
	})
}