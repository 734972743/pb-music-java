package com.pb.weixin.utils;


/**
 * 这是用来定义一个接口，输入E来输出T
 * @author ASUS
 *
 * @param <T>  输出类型
 * @param <E> 输入类型
 */
public interface Function<T,E> {


	public T callbakck(E e);
}
